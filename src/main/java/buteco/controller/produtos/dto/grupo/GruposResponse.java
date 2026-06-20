package buteco.controller.produtos.dto.grupo;

public record GruposResponse(
        Long id,
        String grupo
) {
    @Override
    public String toString() {
        return grupo;
    }
}
