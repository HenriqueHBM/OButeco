package buteco.controller.produtos.dto;

public record ProdutosResponse(
        Long id,
        String nome,
        Double precoVenda,
        String status,
        String categoria,
        String grupo,
        String observacao
) {
}
