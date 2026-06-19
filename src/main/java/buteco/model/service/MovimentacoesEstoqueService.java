package buteco.model.service;

import buteco.model.entity.conversao.ConversoesEntity;
import buteco.model.entity.estoque.EstoqueEntity;
import buteco.model.entity.estoque.MovimentacoesEstoqueEntity;
import buteco.model.entity.pessoa.UsuarioEntity;
import buteco.model.entity.produto.ProdutoEntity;
import buteco.model.repositories.estoque.ConversoesRepository;
import buteco.model.repositories.estoque.EstoqueRepository;
import buteco.model.repositories.estoque.MovimentacoesEstoqueRepository;
import buteco.model.repositories.pessoa.UsuarioRepository;
import buteco.model.repositories.produto.ProdutoRepository;

import java.time.Instant;
import java.util.List;

public class MovimentacoesEstoqueService {
    private final MovimentacoesEstoqueRepository movimentacoesEstoqueRepository;
    private final EstoqueRepository estoqueRepository;
    private final EstoqueService estoqueService;
    private final ConversoesRepository conversoesRepository;
    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;

    public MovimentacoesEstoqueService(MovimentacoesEstoqueRepository movimentacoesEstoqueRepository, EstoqueRepository estoqueRepository,
                                       EstoqueService estoqueService, ConversoesRepository conversoesRepository,
                                       ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository) {
        this.movimentacoesEstoqueRepository = movimentacoesEstoqueRepository;
        this.estoqueRepository = estoqueRepository;
        this.estoqueService = estoqueService;
        this.conversoesRepository = conversoesRepository;
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<MovimentacoesEstoqueEntity> findAllMovimentacoes(){
        return movimentacoesEstoqueRepository.findAll();
    }

    public void cadastrarEntradaSwing(Long idProduto, double qtde, Long idConversaoEntrada, double fatorConversao, String local, UsuarioEntity usuarioEntity, String observacao) {
        EstoqueEntity estoqueEntity = estoqueRepository.findByProdutoId(idProduto);

        //estoque nao encontrado? Cria um novo e atualiza o estoque com novo id
        if(estoqueEntity == null) {
            estoqueEntity = new EstoqueEntity();
            ProdutoEntity produtoEntity = produtoRepository.findById(idProduto);
            ConversoesEntity conversoesEntity = conversoesRepository.findById(idConversaoEntrada);

            estoqueEntity.setProduto(produtoEntity);
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

        ProdutoEntity produtoEntity = produtoRepository.findById(idProduto);

        MovimentacoesEstoqueEntity mov = new MovimentacoesEstoqueEntity();
        mov.setProduto(produtoEntity);
        if (idConversaoEntrada.equals(estoqueEntity.getConversoes().getId())) {
            mov.setConversoes(conversaoEntrada);
        } else {
            mov.setConversoes(estoqueEntity.getConversoes());
        }
        mov.setQuantidade(qtdeNova);
        mov.setDataMovimentacao(Instant.now());

        mov.setUsuario(usuarioEntity);

        mov.setObservacao(observacao != null && !observacao.trim().isEmpty() ? observacao : null);
        mov.setTipo("ENTRADA");
        mov.setEstoque(estoqueEntity);

        movimentacoesEstoqueRepository.create(mov);
    }

    public void cadastrarSaidaSwing(Long idProduto, double qtde, Long idConversaoSaida, double fatorConversao, UsuarioEntity usuarioEntity, String observacao) {
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

        ProdutoEntity produtoEntity = produtoRepository.findById(idProduto);

        MovimentacoesEstoqueEntity mov = new MovimentacoesEstoqueEntity();
        mov.setProduto(produtoEntity);
        if (idConversaoSaida.equals(estoqueEntity.getConversoes().getId())) {
            mov.setConversoes(conversaoSaida);
        } else {
            mov.setConversoes(estoqueEntity.getConversoes());
        }
        mov.setQuantidade(qtdeNova);
        mov.setDataMovimentacao(Instant.now());

        mov.setUsuario(usuarioEntity);

        mov.setObservacao(observacao != null && !observacao.trim().isEmpty() ? observacao : null);

        mov.setTipo("SAIDA");
        mov.setEstoque(estoqueEntity);
        movimentacoesEstoqueRepository.create(mov);
    }

    public void excluirMovimentacao(Long idMovimentacao) {
        MovimentacoesEstoqueEntity mov = movimentacoesEstoqueRepository.findById(idMovimentacao);
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

    public void editarMovimentacao(Long idMovimentacao, ProdutoEntity novoProdutoEntity, double novaQtde, Long idNovaConversao, double fatorConversao, String observacao) {
        MovimentacoesEstoqueEntity mov = movimentacoesEstoqueRepository.findById(idMovimentacao);
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
        EstoqueEntity estoqueEntityNovo = estoqueRepository.findByProdutoId(novoProdutoEntity.getId());
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

        mov.setProduto(novoProdutoEntity);
        mov.setEstoque(estoqueEntityNovo);
        mov.setQuantidade(qtdeNova);
        mov.setConversoes(novaConversao);
        if (observacao != null && !observacao.trim().isEmpty()) {
            mov.setObservacao(observacao);
        }
        movimentacoesEstoqueRepository.update(mov);
    }

    public String getObservacao(Long idMovimentacao) {
        MovimentacoesEstoqueEntity mov = movimentacoesEstoqueRepository.findById(idMovimentacao);
        if (mov == null) return null;
        return mov.getObservacao();
    }

    public void cadastrarSaidaComInsumosSwing(ProdutoEntity produtoEntity, double qtde, UsuarioEntity usuarioEntity, String observacao) {
        // valida todos os insumos primeiro
        for (int index = 1; index <= qtde; index++) {
            produtoEntity.getInsumos().forEach(element -> {
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
            produtoEntity.getInsumos().forEach(element -> {
                if (!element.getInsumo().getCategoria().getCategoria().equals("SERVICO")) {
                    EstoqueEntity estoqueEntity = estoqueRepository.findByProdutoId(element.getInsumo().getId());
                    estoqueEntity.setQntdEstoque(estoqueEntity.getQntdEstoque() - element.getQtde());
                    estoqueRepository.update(estoqueEntity);

                    MovimentacoesEstoqueEntity mov = new MovimentacoesEstoqueEntity();
                    mov.setProduto(element.getInsumo());
                    mov.setQuantidade(element.getQtde());
                    mov.setTipo("SAIDA");
                    mov.setConversoes(estoqueEntity.getConversoes());
                    mov.setEstoque(estoqueEntity);
                    mov.setDataMovimentacao(Instant.now());
                    mov.setUsuario(usuarioEntity);
                    mov.setObservacao(observacao != null && !observacao.trim().isEmpty() ? observacao : null);
                    movimentacoesEstoqueRepository.create(mov);
                }
            });
        }
    }
}