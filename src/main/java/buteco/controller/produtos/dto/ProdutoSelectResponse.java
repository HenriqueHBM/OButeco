package buteco.controller.produtos.dto;

public record ProdutoSelectResponse(
        Long id,
        String nome
) {
    @Override
    public String toString() {
        return nome;
    }
}