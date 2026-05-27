package buteco.model.service;

import buteco.model.entity.conversao.ConversoesEntity;
import buteco.model.entity.estoque.Estoque;
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
        Estoque estoque = estoqueRepository.findByProdutoId(idProduto);

        //estoque nao encontrado? Cria um novo e atualiza o estoque com novo id
        if(estoque == null) {
            estoque = new Estoque();
            Produto produto = produtoRepository.findById(idProduto);
            ConversoesEntity conversoesEntity = conversoesRepository.findById(idConversaoEntrada);

            estoque.setProduto(produto);
            estoque.setConversoes(conversoesEntity);
            estoque.setLocal(local);
            estoque.setQntdEstoque(0);
            estoqueRepository.create(estoque);

            estoque = estoqueRepository.findByProdutoId(idProduto);
        }

        ConversoesEntity conversaoEntrada = conversoesRepository.findById(idConversaoEntrada);
        if (conversaoEntrada == null) {
            throw new RuntimeException("Unidade de conversao nao encontrada");
        }

        double qtdeNova;
        if (idConversaoEntrada.equals(estoque.getConversoes().getId())){
            qtdeNova = qtde;
        } else {
            if (fatorConversao <= 0 ) {
                throw new RuntimeException("Fator de conversao invalido!");
            }
            qtdeNova = fatorConversao;
        }

        estoque.setQntdEstoque(estoque.getQntdEstoque() + qtdeNova);
        estoqueRepository.update(estoque);

        Produto produto = produtoRepository.findById(idProduto);

        MovimentacoesEstoque mov = new MovimentacoesEstoque();
        mov.setProduto(produto);
        if (idConversaoEntrada.equals(estoque.getConversoes().getId())) {
            mov.setConversoes(conversaoEntrada);
        } else {
            mov.setConversoes(estoque.getConversoes());
        }
        mov.setQuantidade(qtdeNova);
        mov.setDataMovimentacao(Instant.now());

        mov.setUsuario(usuario);

        mov.setObservacao(observacao != null && !observacao.trim().isEmpty() ? observacao : null);
        mov.setTipo("ENTRADA");
        mov.setEstoque(estoque);

        movimentacoesEstoqueRepository.create(mov);
    }

    public void cadastrarSaidaSwing(Long idProduto, double qtde, Long idConversaoSaida, double fatorConversao, Usuario usuario,String observacao) {
        Estoque estoque = estoqueRepository.findByProdutoId(idProduto);

        if(estoque == null) {
            throw new RuntimeException("Estoque nao encontrado para esse produto!");
        }

        ConversoesEntity conversaoSaida = conversoesRepository.findById(idConversaoSaida);
        if (conversaoSaida == null) {
            throw new RuntimeException("Unidade de conversao nao encontrada!");
        }

        double qtdeNova;
        if(idConversaoSaida.equals(estoque.getConversoes().getId())) {
            qtdeNova = qtde;
        } else {
            if (fatorConversao <= 0) {
                throw new RuntimeException("Fator de conversao invalido!");
            }
            qtdeNova = fatorConversao;
        }

        if (qtdeNova > estoque.getQntdEstoque()) {
            throw new RuntimeException("Quantidade insuficiente no estoque! Disponivel: " + estoque.getQntdEstoque());
        }

        estoque.setQntdEstoque(estoque.getQntdEstoque() - qtdeNova);
        estoqueRepository.update(estoque);

        Produto produto = produtoRepository.findById(idProduto);

        MovimentacoesEstoque mov = new MovimentacoesEstoque();
        mov.setProduto(produto);
        if (idConversaoSaida.equals(estoque.getConversoes().getId())) {
            mov.setConversoes(conversaoSaida);
        } else {
            mov.setConversoes(estoque.getConversoes());
        }
        mov.setQuantidade(qtdeNova);
        mov.setDataMovimentacao(Instant.now());

        mov.setUsuario(usuario);

        mov.setObservacao(observacao != null && !observacao.trim().isEmpty() ? observacao : null);

        mov.setTipo("SAIDA");
        mov.setEstoque(estoque);
        movimentacoesEstoqueRepository.create(mov);
    }

    public void excluirMovimentacao(Long idMovimentacao) {
        MovimentacoesEstoque mov = movimentacoesEstoqueRepository.findById(idMovimentacao);
        if (mov == null) {
            throw new RuntimeException("Movimentacao nao encontrada!");
        }

        Estoque estoque = mov.getEstoque();
        if (estoque == null) {
            throw new RuntimeException("Estoque da movimentacao nao encontrado!");
        }

        //reverter o estoque
        if(mov.getTipo().equals("ENTRADA")) {
            estoque.setQntdEstoque(estoque.getQntdEstoque() - mov.getQuantidade());
        } else if (mov.getTipo().equals("SAIDA")) {
            estoque.setQntdEstoque(estoque.getQntdEstoque() + mov.getQuantidade());
        }

        estoqueRepository.update(estoque);
        movimentacoesEstoqueRepository.delete(mov);
    }

    public void editarMovimentacao(Long idMovimentacao, Produto novoProduto, double novaQtde, Long idNovaConversao, double fatorConversao, String observacao) {
        MovimentacoesEstoque mov = movimentacoesEstoqueRepository.findById(idMovimentacao);
        if (mov == null) throw new RuntimeException("Movimentação não encontrada!");

        Estoque estoqueAntigo = mov.getEstoque();
        if (estoqueAntigo == null) throw new RuntimeException("Estoque da movimentação não encontrado!");

        // reverte no estoque antigo
        if (mov.getTipo().equals("ENTRADA")) {
            estoqueAntigo.setQntdEstoque(estoqueAntigo.getQntdEstoque() - mov.getQuantidade());
        } else if (mov.getTipo().equals("SAIDA")) {
            estoqueAntigo.setQntdEstoque(estoqueAntigo.getQntdEstoque() + mov.getQuantidade());
        }
        estoqueRepository.update(estoqueAntigo);

        // busca o estoque do novo produto
        Estoque estoqueNovo = estoqueRepository.findByProdutoId(novoProduto.getId());
        if (estoqueNovo == null) throw new RuntimeException("Estoque nao encontrado para o novo produto!");

        // calcula nova quantidade
        double qtdeNova;
        if (idNovaConversao.equals(estoqueNovo.getConversoes().getId())) {
            qtdeNova = novaQtde;
        } else {
            if (fatorConversao <= 0) throw new RuntimeException("Fator de conversão inválido!");
            qtdeNova = fatorConversao;
        }

        // aplica no estoque novo
        if (mov.getTipo().equals("ENTRADA")) {
            estoqueNovo.setQntdEstoque(estoqueNovo.getQntdEstoque() + qtdeNova);
        } else if (mov.getTipo().equals("SAIDA")) {
            if (qtdeNova > estoqueNovo.getQntdEstoque()) {
                throw new RuntimeException("Quantidade insuficiente no estoque! Disponível: " + estoqueNovo.getQntdEstoque());
            }
            estoqueNovo.setQntdEstoque(estoqueNovo.getQntdEstoque() - qtdeNova);
        }
        estoqueRepository.update(estoqueNovo);

        // unidade salva sempre é a do estoque novo
        ConversoesEntity novaConversao = idNovaConversao.equals(estoqueNovo.getConversoes().getId())
                ? conversoesRepository.findById(idNovaConversao)
                : estoqueNovo.getConversoes();

        mov.setProduto(novoProduto);
        mov.setEstoque(estoqueNovo);
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
                    Estoque estoque = estoqueRepository.findByProdutoId(element.getInsumo().getId());
                    if (estoque == null) {
                        throw new RuntimeException("Estoque nao encontrado para: " + element.getInsumo().getNome());
                    }
                    if (element.getQtde() > estoque.getQntdEstoque()) {
                        throw new RuntimeException("Quantidade insuficiente para: " + element.getInsumo().getNome()
                                + " | Disponivel: " + estoque.getQntdEstoque());
                    }
                }
            });

            // da baixa nos insumos
            produto.getInsumos().forEach(element -> {
                if (!element.getInsumo().getCategoria().getCategoria().equals("SERVICO")) {
                    Estoque estoque = estoqueRepository.findByProdutoId(element.getInsumo().getId());
                    estoque.setQntdEstoque(estoque.getQntdEstoque() - element.getQtde());
                    estoqueRepository.update(estoque);
                }
            });

            // registra movimentacao do produto final
            Estoque estoqueProdfinal = estoqueRepository.findByProdutoId(produto.getId());
            if (estoqueProdfinal == null) {
                throw new RuntimeException("Estoque nao encontrado para o produto final: " + produto.getNome());
            }

            MovimentacoesEstoque mov = new MovimentacoesEstoque();
            mov.setProduto(produto);
            mov.setQuantidade(1);
            mov.setTipo("SAIDA");
            mov.setConversoes(estoqueProdfinal.getConversoes());
            mov.setEstoque(estoqueProdfinal);
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
        Estoque estoque = estoqueRepository.findByProdutoId(idProduto);
        if (estoque == null) {
            System.out.println("Estoque inexistente para esse produto, cadastre um novo:");
            estoqueService.criarNovoEstoque(idProduto); //caso o estoque para o produto nao exista ele deve criar um novo
        }
    }

    public void cadastrarEntrada(Long idProduto, double qtde){
        Estoque estoque = estoqueRepository.findByProdutoId(idProduto);
        if (estoque == null){
            throw new RuntimeException("Estoque nao encontrado para esse COD.");
        }

        System.out.println("Unidade no estoque: " + estoque.getConversoes().getNome()); //mostra para o usuario qual a unidade cadastrada no estoque
        var medidas = conversoesRepository.findAllConversoes();
        medidas.stream().forEach(System.out::println); //lista unidades de conversao
        Long idConversoes = erroEntrada.trataEntradaLong("Insira o codigo de unidade que voce esta usando: ");

        ConversoesEntity conversaoEntrada = conversoesRepository.findById(idConversoes);
        if(conversaoEntrada == null){
            throw new RuntimeException("Unidade de conversao nao encontrada");
        }

        double qtdeNova;
        if(idConversoes.equals(estoque.getConversoes().getId())){ //.equals necessario para comparar os valores, == nao funciona para Long por ser objeto
            qtdeNova = qtde; //caso medidas sejam iguais nao faz conversao
        } else {
            System.out.println("Unidade diferente do estoque!");
            double conversaoEquivalente = erroEntrada.trataEntradaDouble //pede referencia da conversao de medida para o usuario
                            ("Quanto vale 1 " + conversoesRepository.findById(idConversoes).getNome() //ex: 1un de queijo valem 500g
                                    + " em " + estoque.getConversoes().getNome() + "?\n");
            qtdeNova = qtde * conversaoEquivalente;
            System.out.printf("Convertido: %.2f %s\n", qtdeNova, estoque.getConversoes().getNomenclatura());
        }

        estoque.setQntdEstoque(estoque.getQntdEstoque() + qtdeNova);
        estoqueRepository.update(estoque);

        Produto produto = produtoRepository.findById(idProduto);

        MovimentacoesEstoque mov = new MovimentacoesEstoque();
        mov.setProduto(produto);
        mov.setQuantidade(qtde);
        mov.setTipo("ENTRADA");
        mov.setConversoes(conversaoEntrada);
        mov.setEstoque(estoque);
        mov.setDataMovimentacao(Instant.now());

        movimentacoesEstoqueRepository.create(mov);
    }

    public void cadastrarSaida(Long idProduto, double qtde, boolean cria_mov){
        Estoque estoque = estoqueRepository.findByProdutoId(idProduto);
        if (estoque == null){
            throw new RuntimeException("Estoque nao encontrado para esse COD.");
        }
        if (qtde > estoque.getQntdEstoque()){
            throw new RuntimeException("Quantidade insuficiente no estoque, tente novamente!");
        }

        estoque.setQntdEstoque(estoque.getQntdEstoque() - qtde);
        estoqueRepository.update(estoque);

        Produto produto = produtoRepository.findById(idProduto);

        if(cria_mov == true){
            this.movimentacaoEstoque(produto, estoque, qtde);
        }

    }

    public void movimentacaoEstoque(Produto produto, Estoque estoque, double qtde){
        MovimentacoesEstoque mov = new MovimentacoesEstoque();
        mov.setProduto(produto);
        mov.setQuantidade(qtde);
        mov.setTipo("SAIDA");
        mov.setConversoes(estoque.getConversoes());
        mov.setEstoque(estoque);
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
                    Estoque estoque = estoqueRepository.findByProdutoId(element.getInsumo().getId());
                    if (estoque == null){
                        throw new RuntimeException("Estoque nao encontrado para esse produto: "+ element.getInsumo().getNome());
                    }
                    if (element.getQtde() > estoque.getQntdEstoque()){
                        throw new RuntimeException("Quantidade insuficiente no estoque: "+ element.getInsumo().getNome()+", Quantidade: " + estoque.getQntdEstoque());
                    }
                }
            });

            produto.getInsumos().forEach(element -> {
                if(!element.getInsumo().getCategoria().getCategoria().equals("SERVICO")){
                    this.cadastrarSaida(element.getInsumo().getId(), element.getQtde(), false);
                }
            });


            Estoque estoque = produto.getEstoques().get(0);
            this.movimentacaoEstoque(produto, estoque, 1);
        }
    }

}