package buteco.controller.usuarios;

import buteco.controller.usuarios.dto.CargoResponse;
import buteco.controller.usuarios.dto.EditUsuarioResponse;
import buteco.controller.usuarios.dto.UsuarioResponse;
import buteco.controller.usuarios.dto.UsuarioSelectResponse;

import java.util.List;

public interface UsuarioControllerInterface {

    UsuarioSelectResponse cadastrarUsuario(
            String nome,
            String login,
            String senha,
            Long fk_id_cargo
    );

    UsuarioResponse usuariosResponse(
            Long id,
            String nome,
            String login,
            String cargo
    );

    UsuarioSelectResponse selectUsuario(Long id, String nome);

    CargoResponse cargoResponse(
            Long id,
            String nome
    );

    UsuarioSelectResponse editarUsuario(
            Long id_usuario,
            String nome,
            String login,
            String senha,
            Long fk_id_cargo
    );

    EditUsuarioResponse findUsuario(Long id);
    CargoResponse findCargo(Long id);

    List<UsuarioResponse> listarUsuarios();
    List<CargoResponse> listarCargos();

    void deletarUsuario(Long id_usuario);
}

