package buteco.controller.produtos.dto.categoria;

public record CategoriasResponse(
        Long id,
        String categoria
) {
    @Override
    public String toString() {
        return categoria;
    }
}
