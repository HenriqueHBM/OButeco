package buteco.controller.produtos.dto;

public record EditProdutoResponse(
        Long id,
        String nome,
        double preco_venda,
        String status,
        Long fk_id_categoria,
        Long fk_id_grupo,
        String obsevacao
) {
}
