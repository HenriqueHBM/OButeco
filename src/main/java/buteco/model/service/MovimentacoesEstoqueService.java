package buteco.model.service;

import buteco.model.entity.conversao.ConversoesEntity;
import buteco.model.entity.estoque.EstoqueEntity;
import buteco.model.entity.estoque.MovimentacoesEstoque;
import buteco.model.entity.pessoa.Usuario;
import buteco.model.entity.produto.Produto;
import buteco.model.repositories.estoque.ConversoesRepository;
import buteco.model.repositories.estoque.EstoqueRepository;
import buteco.model.repositories.estoque.MovimentacoesEstoqueRepository;
import buteco.model.repositories.pessoa.UsuarioRepository;
import buteco.model.repositories.produto.ProdutoRepository;
import buteco.model.service.entradas.ErroEntrada;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MovimentacoesEstoqueService {
    private final MovimentacoesEstoqueRepository movimentacoesEstoqueRepository;
    private final EstoqueRepository estoqueRepository;
    private final EstoqueService estoqueService;
    private final ConversoesRepository conversoesRepository;
    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;
    private  ErroEntrada erroEntrada;

    public MovimentacoesEstoqueService(MovimentacoesEstoqueRepository movimentacoesEstoqueRepository, EstoqueRepository estoqueRepository,
                                       EstoqueService estoqueService, ConversoesRepository conversoesRepository,
                                       ProdutoRepository produtoRepository, ErroEntrada erroEntrada, UsuarioRepository usuarioRepository) {
        this.movimentacoesEstoqueRepository = movimentacoesEstoqueRepository;
        this.estoqueRepository = estoqueRepository;
        this.estoqueService = estoqueService;
        this.conversoesRepository = conversoesRepository;
        this.produtoRepository = produtoRepository;
        this.erroEntrada = erroEntrada;
        this.usuarioRepository = usuarioRepository;
    }

    public List<MovimentacoesEstoque> findAllMovimentacoes(){
        return movimentacoesEstoqueRepository.findAll();
    }


    //--------------Funcoes Sendo usadas --------------
    public void cadastrarEntradaSwing(Long idProduto, double qtde, Long idConversaoEntrada, double fatorConversao, String local, Usuario usuario, String observacao) {
        EstoqueEntity estoqueEntity = estoqueRepository.findByProdutoId(idProduto);

        //estoque nao encontrado? Cria um novo e atualiza o estoque com novo id
        if(estoqueEntity == null) {
            estoqueEntity = new EstoqueEntity();
            Produto produto = produtoRepository.findById(idProduto);
            ConversoesEntity conversoesEntity = conversoesRepository.findById(idConversaoEntrada);

            estoqueEntity.setProduto(produto);
            estoqueEntity.setConversoes(conversoesEntity);
            estoqueEntity.setLocal(local);
            estoqueEntity.setQntdEstoque(0);
            estoqueRepository.create(estoqueEntity);

            estoqueEntity = estoqueRepository.findByProdutoId(idProduto);
        }

        ConversoesEntity conversaoEntrada = conversoesRepository.findById(idConversaoEntrada);
        if (conversaoEntrada == null) {
            throw new RuntimeException("Unidade de conversao nao encontrada");
        }

        double qtdeNova;
        if (idConversaoEntrada.equals(estoqueEntity.getConversoes().getId())){
            qtdeNova = qtde;
        } else {
            if (fatorConversao <= 0 ) {
                throw new RuntimeException("Fator de conversao invalido!");
            }
            qtdeNova = fatorConversao;
        }

        estoqueEntity.setQntdEstoque(estoqueEntity.getQntdEstoque() + qtdeNova);
        estoqueRepository.update(estoqueEntity);

        Produto produto = produtoRepository.findById(idProduto);

        MovimentacoesEstoque mov = new MovimentacoesEstoque();
        mov.setProduto(produto);
        if (idConversaoEntrada.equals(estoqueEntity.getConversoes().getId())) {
            mov.setConversoes(conversaoEntrada);
        } else {
            mov.setConversoes(estoqueEntity.getConversoes());
        }
        mov.setQuantidade(qtdeNova);
        mov.setDataMovimentacao(Instant.now());

        mov.setUsuario(usuario);

        mov.setObservacao(observacao != null && !observacao.trim().isEmpty() ? observacao : null);
        mov.setTipo("ENTRADA");
        mov.setEstoque(estoqueEntity);

        movimentacoesEstoqueRepository.create(mov);
    }

    public void cadastrarSaidaSwing(Long idProduto, double qtde, Long idConversaoSaida, double fatorConversao, Usuario usuario,String observacao) {
        EstoqueEntity estoqueEntity = estoqueRepository.findByProdutoId(idProduto);

        if(estoqueEntity == null) {
            throw new RuntimeException("Estoque nao encontrado para esse produto!");
        }

        ConversoesEntity conversaoSaida = conversoesRepository.findById(idConversaoSaida);
        if (conversaoSaida == null) {
            throw new RuntimeException("Unidade de conversao nao encontrada!");
        }

        double qtdeNova;
        if(idConversaoSaida.equals(estoqueEntity.getConversoes().getId())) {
            qtdeNova = qtde;
        } else {
            if (fatorConversao <= 0) {
                throw new RuntimeException("Fator de conversao invalido!");
            }
            qtdeNova = fatorConversao;
        }

        if (qtdeNova > estoqueEntity.getQntdEstoque()) {
            throw new RuntimeException("Quantidade insuficiente no estoque! Disponivel: " + estoqueEntity.getQntdEstoque());
        }

        estoqueEntity.setQntdEstoque(estoqueEntity.getQntdEstoque() - qtdeNova);
        estoqueRepository.update(estoqueEntity);

        Produto produto = produtoRepository.findById(idProduto);

        MovimentacoesEstoque mov = new MovimentacoesEstoque();
        mov.setProduto(produto);
        if (idConversaoSaida.equals(estoqueEntity.getConversoes().getId())) {
            mov.setConversoes(conversaoSaida);
        } else {
            mov.setConversoes(estoqueEntity.getConversoes());
        }
        mov.setQuantidade(qtdeNova);
        mov.setDataMovimentacao(Instant.now());

        mov.setUsuario(usuario);

        mov.setObservacao(observacao != null && !observacao.trim().isEmpty() ? observacao : null);

        mov.setTipo("SAIDA");
        mov.setEstoque(estoqueEntity);
        movimentacoesEstoqueRepository.create(mov);
    }

    public void excluirMovimentacao(Long idMovimentacao) {
        MovimentacoesEstoque mov = movimentacoesEstoqueRepository.findById(idMovimentacao);
        if (mov == null) {
            throw new RuntimeException("Movimentacao nao encontrada!");
        }

        EstoqueEntity estoqueEntity = mov.getEstoque();
        if (estoqueEntity == null) {
            throw new RuntimeException("Estoque da movimentacao nao encontrado!");
        }

        //reverter o estoque
        if(mov.getTipo().equals("ENTRADA")) {
            estoqueEntity.setQntdEstoque(estoqueEntity.getQntdEstoque() - mov.getQuantidade());
        } else if (mov.getTipo().equals("SAIDA")) {
            estoqueEntity.setQntdEstoque(estoqueEntity.getQntdEstoque() + mov.getQuantidade());
        }

        estoqueRepository.update(estoqueEntity);
        movimentacoesEstoqueRepository.delete(mov);
    }

    public void editarMovimentacao(Long idMovimentacao, Produto novoProduto, double novaQtde, Long idNovaConversao, double fatorConversao, String observacao) {
        MovimentacoesEstoque mov = movimentacoesEstoqueRepository.findById(idMovimentacao);
        if (mov == null) throw new RuntimeException("Movimentação não encontrada!");

        EstoqueEntity estoqueEntityAntigo = mov.getEstoque();
        if (estoqueEntityAntigo == null) throw new RuntimeException("Estoque da movimentação não encontrado!");

        // reverte no estoque antigo
        if (mov.getTipo().equals("ENTRADA")) {
            estoqueEntityAntigo.setQntdEstoque(estoqueEntityAntigo.getQntdEstoque() - mov.getQuantidade());
        } else if (mov.getTipo().equals("SAIDA")) {
            estoqueEntityAntigo.setQntdEstoque(estoqueEntityAntigo.getQntdEstoque() + mov.getQuantidade());
        }
        estoqueRepository.update(estoqueEntityAntigo);

        // busca o estoque do novo produto
        EstoqueEntity estoqueEntityNovo = estoqueRepository.findByProdutoId(novoProduto.getId());
        if (estoqueEntityNovo == null) throw new RuntimeException("Estoque nao encontrado para o novo produto!");

        // calcula nova quantidade
        double qtdeNova;
        if (idNovaConversao.equals(estoqueEntityNovo.getConversoes().getId())) {
            qtdeNova = novaQtde;
        } else {
            if (fatorConversao <= 0) throw new RuntimeException("Fator de conversão inválido!");
            qtdeNova = fatorConversao;
        }

        // aplica no estoque novo
        if (mov.getTipo().equals("ENTRADA")) {
            estoqueEntityNovo.setQntdEstoque(estoqueEntityNovo.getQntdEstoque() + qtdeNova);
        } else if (mov.getTipo().equals("SAIDA")) {
            if (qtdeNova > estoqueEntityNovo.getQntdEstoque()) {
                throw new RuntimeException("Quantidade insuficiente no estoque! Disponível: " + estoqueEntityNovo.getQntdEstoque());
            }
            estoqueEntityNovo.setQntdEstoque(estoqueEntityNovo.getQntdEstoque() - qtdeNova);
        }
        estoqueRepository.update(estoqueEntityNovo);

        // unidade salva sempre é a do estoque novo
        ConversoesEntity novaConversao = idNovaConversao.equals(estoqueEntityNovo.getConversoes().getId())
                ? conversoesRepository.findById(idNovaConversao)
                : estoqueEntityNovo.getConversoes();

        mov.setProduto(novoProduto);
        mov.setEstoque(estoqueEntityNovo);
        mov.setQuantidade(qtdeNova);
        mov.setConversoes(novaConversao);
        if (observacao != null && !observacao.trim().isEmpty()) {
            mov.setObservacao(observacao);
        }
        movimentacoesEstoqueRepository.update(mov);
    }

    public String getObservacao(Long idMovimentacao) {
        MovimentacoesEstoque mov = movimentacoesEstoqueRepository.findById(idMovimentacao);
        if (mov == null) return null;
        return mov.getObservacao();
    }

    public void cadastrarSaidaComInsumosSwing(Produto produto, double qtde, Usuario usuario, String observacao) {
        // valida todos os insumos primeiro
        for (int index = 1; index <= qtde; index++) {
            produto.getInsumos().forEach(element -> {
                if (!element.getInsumo().getCategoria().getCategoria().equals("SERVICO")) {
                    EstoqueEntity estoqueEntity = estoqueRepository.findByProdutoId(element.getInsumo().getId());
                    if (estoqueEntity == null) {
                        throw new RuntimeException("Estoque nao encontrado para: " + element.getInsumo().getNome());
                    }
                    if (element.getQtde() > estoqueEntity.getQntdEstoque()) {
                        throw new RuntimeException("Quantidade insuficiente para: " + element.getInsumo().getNome()
                                + " | Disponivel: " + estoqueEntity.getQntdEstoque());
                    }
                }
            });

            // da baixa nos insumos
            produto.getInsumos().forEach(element -> {
                if (!element.getInsumo().getCategoria().getCategoria().equals("SERVICO")) {
                    EstoqueEntity estoqueEntity = estoqueRepository.findByProdutoId(element.getInsumo().getId());
                    estoqueEntity.setQntdEstoque(estoqueEntity.getQntdEstoque() - element.getQtde());
                    estoqueRepository.update(estoqueEntity);
                }
            });

            // registra movimentacao do produto final
            EstoqueEntity estoqueEntityProdfinal = estoqueRepository.findByProdutoId(produto.getId());
            if (estoqueEntityProdfinal == null) {
                throw new RuntimeException("Estoque nao encontrado para o produto final: " + produto.getNome());
            }

            MovimentacoesEstoque mov = new MovimentacoesEstoque();
            mov.setProduto(produto);
            mov.setQuantidade(1);
            mov.setTipo("SAIDA");
            mov.setConversoes(estoqueEntityProdfinal.getConversoes());
            mov.setEstoque(estoqueEntityProdfinal);
            mov.setDataMovimentacao(Instant.now());
            mov.setUsuario(usuario);
            mov.setObservacao(observacao != null && !observacao.trim().isEmpty() ? observacao : null);

            movimentacoesEstoqueRepository.create(mov);
        }
    }

    //----------------------------------------------

    /// proximos metodos sao antigos e podem ter se tornados obsoletos, revisar


    //metodo obsoleto
    public void confereEstoque(Long idProduto){
        EstoqueEntity estoqueEntity = estoqueRepository.findByProdutoId(idProduto);
        if (estoqueEntity == null) {
            System.out.println("Estoque inexistente para esse produto, cadastre um novo:");
            estoqueService.criarNovoEstoque(idProduto); //caso o estoque para o produto nao exista ele deve criar um novo
        }
    }

    public void cadastrarEntrada(Long idProduto, double qtde){
        EstoqueEntity estoqueEntity = estoqueRepository.findByProdutoId(idProduto);
        if (estoqueEntity == null){
            throw new RuntimeException("Estoque nao encontrado para esse COD.");
        }

        System.out.println("Unidade no estoque: " + estoqueEntity.getConversoes().getNome()); //mostra para o usuario qual a unidade cadastrada no estoque
        var medidas = conversoesRepository.findAllConversoes();
        medidas.stream().forEach(System.out::println); //lista unidades de conversao
        Long idConversoes = erroEntrada.trataEntradaLong("Insira o codigo de unidade que voce esta usando: ");

        ConversoesEntity conversaoEntrada = conversoesRepository.findById(idConversoes);
        if(conversaoEntrada == null){
            throw new RuntimeException("Unidade de conversao nao encontrada");
        }

        double qtdeNova;
        if(idConversoes.equals(estoqueEntity.getConversoes().getId())){ //.equals necessario para comparar os valores, == nao funciona para Long por ser objeto
            qtdeNova = qtde; //caso medidas sejam iguais nao faz conversao
        } else {
            System.out.println("Unidade diferente do estoque!");
            double conversaoEquivalente = erroEntrada.trataEntradaDouble //pede referencia da conversao de medida para o usuario
                            ("Quanto vale 1 " + conversoesRepository.findById(idConversoes).getNome() //ex: 1un de queijo valem 500g
                                    + " em " + estoqueEntity.getConversoes().getNome() + "?\n");
            qtdeNova = qtde * conversaoEquivalente;
            System.out.printf("Convertido: %.2f %s\n", qtdeNova, estoqueEntity.getConversoes().getNomenclatura());
        }

        estoqueEntity.setQntdEstoque(estoqueEntity.getQntdEstoque() + qtdeNova);
        estoqueRepository.update(estoqueEntity);

        Produto produto = produtoRepository.findById(idProduto);

        MovimentacoesEstoque mov = new MovimentacoesEstoque();
        mov.setProduto(produto);
        mov.setQuantidade(qtde);
        mov.setTipo("ENTRADA");
        mov.setConversoes(conversaoEntrada);
        mov.setEstoque(estoqueEntity);
        mov.setDataMovimentacao(Instant.now());

        movimentacoesEstoqueRepository.create(mov);
    }

    public void cadastrarSaida(Long idProduto, double qtde, boolean cria_mov){
        EstoqueEntity estoqueEntity = estoqueRepository.findByProdutoId(idProduto);
        if (estoqueEntity == null){
            throw new RuntimeException("Estoque nao encontrado para esse COD.");
        }
        if (qtde > estoqueEntity.getQntdEstoque()){
            throw new RuntimeException("Quantidade insuficiente no estoque, tente novamente!");
        }

        estoqueEntity.setQntdEstoque(estoqueEntity.getQntdEstoque() - qtde);
        estoqueRepository.update(estoqueEntity);

        Produto produto = produtoRepository.findById(idProduto);

        if(cria_mov == true){
            this.movimentacaoEstoque(produto, estoqueEntity, qtde);
        }

    }

    public void movimentacaoEstoque(Produto produto, EstoqueEntity estoqueEntity, double qtde){
        MovimentacoesEstoque mov = new MovimentacoesEstoque();
        mov.setProduto(produto);
        mov.setQuantidade(qtde);
        mov.setTipo("SAIDA");
        mov.setConversoes(estoqueEntity.getConversoes());
        mov.setEstoque(estoqueEntity);
        mov.setDataMovimentacao(Instant.now());

        movimentacoesEstoqueRepository.create(mov);
    }

    public void exibirMovimentacoesEstoque(){

        var lista = movimentacoesEstoqueRepository.findAll();

        if (lista.isEmpty()){
            System.out.println("Nenhuma Movimentação Encontrada!");
            return;
        }

        System.out.println("=== MOVIMENTAÇÕES DE ESTOQUE === ");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        for (MovimentacoesEstoque m : lista){

            if (m.getProduto() == null){
                continue;
            }

            String dataFormatada = m.getDataMovimentacao()
                    .atZone(ZoneId.systemDefault())
                    .format(formatter);

            System.out.println(
                    "Tipo: " + m.getTipo() +
                    " | Produto: " + m.getProduto().getNome() +
                    " | Quantidade: " + m.getQuantidade() +
                    " | Data: " + dataFormatada
            );
        }

    }

    public void cadastrarSaidacComInsumos(Produto produto, double qtde){
        //passa uma vez no foreach para validar os insumos
        for (int index = 1; index <= qtde;  index++){
            produto.getInsumos().forEach(element -> {
                if(!element.getInsumo().getCategoria().getCategoria().equals("SERVICO")){ // se nao for um servico
                    EstoqueEntity estoqueEntity = estoqueRepository.findByProdutoId(element.getInsumo().getId());
                    if (estoqueEntity == null){
                        throw new RuntimeException("Estoque nao encontrado para esse produto: "+ element.getInsumo().getNome());
                    }
                    if (element.getQtde() > estoqueEntity.getQntdEstoque()){
                        throw new RuntimeException("Quantidade insuficiente no estoque: "+ element.getInsumo().getNome()+", Quantidade: " + estoqueEntity.getQntdEstoque());
                    }
                }
            });

            produto.getInsumos().forEach(element -> {
                if(!element.getInsumo().getCategoria().getCategoria().equals("SERVICO")){
                    this.cadastrarSaida(element.getInsumo().getId(), element.getQtde(), false);
                }
            });


            EstoqueEntity estoqueEntity = produto.getEstoques().get(0);
            this.movimentacaoEstoque(produto, estoqueEntity, 1);
        }
    }

}