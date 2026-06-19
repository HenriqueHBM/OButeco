package buteco.controller.estoque.impl;


import buteco.controller.estoque.EstoquesControllerInterface;
import buteco.controller.estoque.dto.ConversaoResponse;
import buteco.controller.estoque.dto.EstoquesResponse;
import buteco.controller.estoque.dto.MovimentacoesResponse;
import buteco.controller.estoque.dto.ProdutoSelectResponse;
import buteco.model.entity.pessoa.UsuarioEntity;
import buteco.model.entity.produto.ProdutoEntity;
import buteco.model.service.EstoqueService;
import buteco.model.service.MovimentacoesEstoqueService;
import buteco.model.service.ProdutoService;
import buteco.model.service.UsuarioService;
import buteco.model.service.entradas.ConversoesService;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EstoquesControllerImpl implements EstoquesControllerInterface {
    private final ProdutoService produtoService;
    private final ConversoesService conversoesService;
    private final EstoqueService estoqueService;
    private final MovimentacoesEstoqueService movimentacoesEstoqueService;
    private final UsuarioService usuarioService;

    public EstoquesControllerImpl(ProdutoService produtoService,
                                  ConversoesService conversoesService,
                                  EstoqueService estoqueService,
                                  MovimentacoesEstoqueService movimentacoesEstoqueService,
                                  UsuarioService usuarioService) {
        this.produtoService = produtoService;
        this.conversoesService = conversoesService;
        this.estoqueService = estoqueService;
        this.movimentacoesEstoqueService = movimentacoesEstoqueService;
        this.usuarioService = usuarioService;
    }

    //Carregar Selects e Tabelas

    @Override
    public List<ProdutoSelectResponse> getProdutos() {
        return produtoService.findAllProdutos()
                .stream().
                map(p->new ProdutoSelectResponse(
                        p.getId(),
                        p.getNome(),
                        p.getCategoria().getCategoria()
                )).toList();
    }

    @Override
    public List<ConversaoResponse> getConversoes() {
        return conversoesService.findAllConversoes()
                .stream()
                .map(c -> new ConversaoResponse(
                        c.getId(),
                        c.getNomenclatura()
                )).toList();
    }

    @Override
    public List<EstoquesResponse> getEstoque() {
        return estoqueService.findAllEstoques()
                .stream()
                .map(e -> new EstoquesResponse(
                        e.getId(),
                        e.getProduto() != null ? e.getProduto().getNome() : "-",
                        e.getQntdEstoque(),
                        e.getConversoes() != null ? e.getConversoes().getNomenclatura() : "-",
                        e.getLocal() != null ? e.getLocal() : "-"
                )).toList();
    }

    @Override
    public List<MovimentacoesResponse> getMovimentacoes() {
        DateTimeFormatter fmt = DateTimeFormatter
                .ofPattern("dd/MM/yyyy HH:mm")
                .withZone(ZoneId.systemDefault());

        return movimentacoesEstoqueService.findAllMovimentacoes()
                .stream()
                .filter(m -> m.getProduto() != null)
                .map(m -> new MovimentacoesResponse(
                        m.getId(),
                        m.getProduto().getNome(),
                        m.getConversoes().getNomenclatura(),
                        m.getQuantidade(),
                        m.getDataMovimentacao() != null ? fmt.format(m.getDataMovimentacao()) : "-",
                        m.getUsuario() != null ? m.getUsuario().getNome() : "-",
                        m.getTipo()
                )).toList();
    }

    @Override
    public String getObservacaoMovimentacao(Long idMovimentacao) {
        return movimentacoesEstoqueService.getObservacao(idMovimentacao);
    }

    @Override
    public String getUnidadeEstoquePorProduto(Long idProduto) {
        return estoqueService.getUnidadeEstoquePorProduto(idProduto);
    }

    //Metodos
    @Override
    public void cadastrarEntrada(
            Long idProduto,
            String categoriaProduto,
            double qtde,
            Long idConversaoEntrada,
            double fatorConversao,
            String local,
            Long idUsuario,
            String observacao
    ) {
        if (categoriaProduto.equals("SERVICO")) {
            throw new RuntimeException("Produto do tipo SERVICO nao tem estoque!");
        }

        if (categoriaProduto.equals("PRODUTO COM INSUMOS")) {
            throw new RuntimeException("Produto com insumos nao cadastram entrada!");
        }

        UsuarioEntity usuario = usuarioService.findById(idUsuario);

        movimentacoesEstoqueService.cadastrarEntradaSwing(
                idProduto, qtde, idConversaoEntrada, fatorConversao, local, usuario, observacao
        );
    }

    @Override
    public void cadastrarSaida(
            Long idProduto,
            String categoriaProduto,
            double qtde,
            Long idConversaoSaida,
            double fatorConversao,
            Long idUsuario,
            String observacao
    ) {

        if (categoriaProduto.equals("SERVICO")) {
            throw new RuntimeException("Produto do tipo SERVICO nao tem estoque!");
        }

        UsuarioEntity usuario = usuarioService.findById(idUsuario);

        if (categoriaProduto.equals("PRODUTO COM INSUMOS")) {
            ProdutoEntity produto = produtoService.findById(idProduto);
            movimentacoesEstoqueService.cadastrarSaidaComInsumosSwing(produto, qtde, usuario, observacao);
            return;
        }

        movimentacoesEstoqueService.cadastrarSaidaSwing(
                idProduto, qtde, idConversaoSaida, fatorConversao, usuario, observacao
        );
    }

    @Override
    public void excluirMovimentacao(Long idMovimentacao) {
        movimentacoesEstoqueService.excluirMovimentacao(idMovimentacao);
    }

    @Override
    public void editarMovimentacao(
            Long idMovimentacao,
            Long idProduto,
            double qtde,
            Long idConversao,
            double fatorConversao,
            String observacao
    ) {
        ProdutoEntity produto = produtoService.findById(idProduto);
        movimentacoesEstoqueService.editarMovimentacao(
                idMovimentacao, produto, qtde, idConversao, fatorConversao, observacao
        );
    }

}
