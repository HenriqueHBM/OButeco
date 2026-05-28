package buteco.controller.estoque;

import buteco.model.entity.conversao.ConversoesEntity;
import buteco.model.entity.estoque.EstoqueEntity;
import buteco.model.entity.estoque.MovimentacoesEstoqueEntity;
import buteco.model.entity.pessoa.UsuarioEntity;
import buteco.model.entity.produto.ProdutoEntity;
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

    public List<ProdutoEntity> getProdutos(){
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

    public void cadastrarEntrada(ProdutoEntity produtoEntity, double qtde, Long idConversaoEntrada,
                                 double fatorConversao, String local, UsuarioEntity usuarioEntity, String observacao) {
        if (produtoEntity.getCategoria().getCategoria().equals("SERVICO")) {
            throw new RuntimeException("Produto do tipo SERVICO nao tem estoque!");
        }

        if (produtoEntity.getCategoria().getCategoria().equals("PRODUTO COM INSUMOS")) {
            throw new RuntimeException("Produto com insumos nao cadastram entrada!");
        }

        movimentacoesEstoqueService.cadastrarEntradaSwing(
                produtoEntity.getId(), qtde, idConversaoEntrada, fatorConversao, local, usuarioEntity, observacao);
    }

    public void cadastarSaida(ProdutoEntity produtoEntity, double qtde, Long idConversaoSaida, double fatorConversao, UsuarioEntity usuarioEntity, String observacao) {
        if (produtoEntity.getCategoria().getCategoria().equals("SERVICO")) {
            throw new RuntimeException("Produto do tipo SERVIÇO não tem estoque!");
        }

        if (produtoEntity.getCategoria().getCategoria().equals("PRODUTO COM INSUMOS")) {
            movimentacoesEstoqueService.cadastrarSaidaComInsumosSwing(produtoEntity, qtde, usuarioEntity, observacao);
            return;
        }

        movimentacoesEstoqueService.cadastrarSaidaSwing(produtoEntity.getId(), qtde, idConversaoSaida, fatorConversao, usuarioEntity, observacao);
    }

    public void excluirMovimentacao(Long idMovimentacao) {
        movimentacoesEstoqueService.excluirMovimentacao(idMovimentacao);
    }

    public void editarMovimentacao(Long idMovimentacao, ProdutoEntity produtoEntity, double qtde, Long idConversao, double fatorConversao, String observacao) {
        movimentacoesEstoqueService.editarMovimentacao(idMovimentacao, produtoEntity, qtde, idConversao, fatorConversao, observacao);
    }

}
