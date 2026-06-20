package buteco.controller.usuarios.dto;

public record UsuarioResponse(
        Long id,
        String nome,
        String login,
        String cargo
) {
}