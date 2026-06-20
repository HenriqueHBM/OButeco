package buteco.controller.usuarios.dto;

public record EditUsuarioResponse(
        Long id,
        String nome,
        String login,
        Long fk_id_cargo
) {
}