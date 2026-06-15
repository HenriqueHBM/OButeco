package buteco.controller.produtos.dto;

public record InsumosProdutoResponse(
        Long id_produto,
        Long id,
        String nome,
        double qtde
) {
}
