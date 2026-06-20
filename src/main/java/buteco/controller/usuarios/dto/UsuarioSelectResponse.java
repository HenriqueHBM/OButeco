package buteco.controller.usuarios.dto;

public record UsuarioSelectResponse(
        Long id,
        String nome
) {
    @Override
    public String toString() {
        return nome;
    }
}
