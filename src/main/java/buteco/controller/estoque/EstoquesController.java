package buteco.controller.estoque;

import buteco.model.conversao.Conversoes;
import buteco.model.estoque.Estoque;
import buteco.model.estoque.MovimentacoesEstoque;
import buteco.model.pessoa.Usuario;
import buteco.model.produto.Produto;
import buteco.service.EstoqueService;
import buteco.service.MovimentacoesEstoqueService;
import buteco.service.ProdutoService;
import buteco.service.entradas.ConversoesService;

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

    public List<Conversoes> getConversoes(){ return conversoesService.findAllConversoes(); }

    public List<Estoque> getEstoque(){ return estoqueService.findAllEstoques();}

    public List<MovimentacoesEstoque> getMovimentacoes(){ return movimentacoesEstoqueService.findAllMovimentacoes();}

// Metodos para os botoes

    public void cadastrarEntrada(Produto produto, double qtde, Long idConversaoEntrada,
                                 double fatorConversao, String local, Usuario usuario) {
        if (produto.getCategoria().getCategoria().equals("SERVICO")) {
            throw new RuntimeException("Produto do tipo SERVICO nao tem estoque!");
        }

        if (produto.getCategoria().getCategoria().equals("PRODUTO COM INSUMOS")) {
            throw new RuntimeException("Produto com insumos nao cadastram entrada!");
        }

        movimentacoesEstoqueService.cadastrarEntradaSwing(
                produto.getId(), qtde, idConversaoEntrada, fatorConversao, local, usuario);
    }

    public void cadastarSaida(Produto produto, double qtde, Long idConversaoSaida, double fatorConversao, Usuario usuario){
        if (produto.getCategoria().getCategoria().equals("SERVICO")) {
            throw new RuntimeException("Produto do tipo SERVICO nao tem estoque!");
        }
        movimentacoesEstoqueService.cadastrarSaidaSwing(produto.getId(), qtde, idConversaoSaida, fatorConversao, usuario);
    }

    public void excluirMovimentacao(Long idMovimentacao) {
        movimentacoesEstoqueService.excluirMovimentacao(idMovimentacao);
    }

    public void editarMovimentacao(Long idMovimentacao, Produto produto, double qtde, Long idConversao) {
        movimentacoesEstoqueService.editarMovimentacao(idMovimentacao, produto, qtde, idConversao);
    }

}
