package buteco.controller.estoque;

import buteco.controller.estoque.dto.*;

import java.util.List;

public interface EstoquesControllerInterface {

    List<ProdutoSelectResponse> getProdutos();
    List<ConversaoResponse> getConversoes();
    List<EstoquesResponse> getEstoque();
    List<MovimentacoesResponse> getMovimentacoes();
    String getObservacaoMovimentacao(Long idMovimentacao);
    String getUnidadeEstoquePorProduto(Long idProduto);

    void cadastrarEntrada(
       Long idProduto,
       String categoriaProduto,
       double qtde,
       Long idConversaoEntrada,
       double fatorConversao,
       String local,
       Long idUsuario,
       String observacao
    );

    void cadastrarSaida(
            Long idProduto,
            String categoriaProduto,
            double qtde,
            Long idConversaoSaida,
            double fatorConversao,
            Long idUsuario,
            String obversacao
    );

    void excluirMovimentacao(
            Long idMovimentacao
    );

    void editarMovimentacao(
            Long idMovimentacao,
            Long idProduto,
            double qtde,
            Long idConversao,
            double fatorConversao,
            String observacao
    );

}
