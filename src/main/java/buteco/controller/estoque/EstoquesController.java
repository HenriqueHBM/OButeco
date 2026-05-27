package buteco.controller.estoque;

import buteco.model.entity.conversao.ConversoesEntity;
import buteco.model.entity.estoque.EstoqueEntity;
import buteco.model.entity.estoque.MovimentacoesEstoqueEntity;
import buteco.model.entity.pessoa.UsuarioEntity;
import buteco.model.entity.produto.Produto;
import buteco.model.service.EstoqueService;
import buteco.model.service.MovimentacoesEstoqueService;
import buteco.model.service.ProdutoService;
import buteco.model.service.entradas.ConversoesService;

import java.util.List;

public class EstoquesController {
    private final ProdutoService produtoService;
    private final ConversoesService conversoesService;
    private final EstoqueService estoqueService;
    private final MovimentacoesEstoqueService movimentacoesEstoqueService;

    public EstoquesController(ProdutoService produtoService, ConversoesService conversoesService,
                              EstoqueService estoqueService, MovimentacoesEstoqueService movimentacoesEstoqueService) {
        this.produtoService = produtoService;
        this.conversoesService = conversoesService;
        this.estoqueService = estoqueService;
        this.movimentacoesEstoqueService = movimentacoesEstoqueService;
    }

// Metodos para carregar as tabelas e comboBox da view de Estoque

    public List<Produto> getProdutos(){
        return produtoService.findAllProdutos();
    }

    public List<ConversoesEntity> getConversoes(){ return conversoesService.findAllConversoes(); }

    public List<EstoqueEntity> getEstoque(){ return estoqueService.findAllEstoques();}

    public List<MovimentacoesEstoqueEntity> getMovimentacoes(){ return movimentacoesEstoqueService.findAllMovimentacoes();}

    public String getObservacaoMovimentacao(Long idMovimentacao) {
        return movimentacoesEstoqueService.getObservacao(idMovimentacao);
    }

    public String getUnidadeEstoquePorProduto(Long idProduto) {
        return estoqueService.getUnidadeEstoquePorProduto(idProduto);
    }

// Metodos para os botoes

    public void cadastrarEntrada(Produto produto, double qtde, Long idConversaoEntrada,
                                 double fatorConversao, String local, UsuarioEntity usuarioEntity, String observacao) {
        if (produto.getCategoria().getCategoria().equals("SERVICO")) {
            throw new RuntimeException("Produto do tipo SERVICO nao tem estoque!");
        }

        if (produto.getCategoria().getCategoria().equals("PRODUTO COM INSUMOS")) {
            throw new RuntimeException("Produto com insumos nao cadastram entrada!");
        }

        movimentacoesEstoqueService.cadastrarEntradaSwing(
                produto.getId(), qtde, idConversaoEntrada, fatorConversao, local, usuarioEntity, observacao);
    }

    public void cadastarSaida(Produto produto, double qtde, Long idConversaoSaida, double fatorConversao, UsuarioEntity usuarioEntity, String observacao) {
        if (produto.getCategoria().getCategoria().equals("SERVICO")) {
            throw new RuntimeException("Produto do tipo SERVIÇO não tem estoque!");
        }

        if (produto.getCategoria().getCategoria().equals("PRODUTO COM INSUMOS")) {
            movimentacoesEstoqueService.cadastrarSaidaComInsumosSwing(produto, qtde, usuarioEntity, observacao);
            return;
        }

        movimentacoesEstoqueService.cadastrarSaidaSwing(produto.getId(), qtde, idConversaoSaida, fatorConversao, usuarioEntity, observacao);
    }

    public void excluirMovimentacao(Long idMovimentacao) {
        movimentacoesEstoqueService.excluirMovimentacao(idMovimentacao);
    }

    public void editarMovimentacao(Long idMovimentacao, Produto produto, double qtde, Long idConversao, double fatorConversao, String observacao) {
        movimentacoesEstoqueService.editarMovimentacao(idMovimentacao, produto, qtde, idConversao, fatorConversao, observacao);
    }

}
