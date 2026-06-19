package buteco.controller.estoque.dto;

public record ProdutoSelectResponse (
        Long id,
        String nome,
        String categoria
){
}
