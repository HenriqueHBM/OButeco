package buteco.controller.produtos.dto;

public record CadInsumosProdutoResponse(
        Long id_produto,
        double qtde,
        Long id_insumo
) {
}
