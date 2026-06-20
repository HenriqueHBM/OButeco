package buteco.controller.usuarios.impl;

import buteco.controller.usuarios.UsuarioControllerInterface;
import buteco.controller.usuarios.dto.CargoResponse;
import buteco.controller.usuarios.dto.EditUsuarioResponse;
import buteco.controller.usuarios.dto.UsuarioResponse;
import buteco.controller.usuarios.dto.UsuarioSelectResponse;
import buteco.model.entity.pessoa.CargoEntity;
import buteco.model.entity.pessoa.UsuarioEntity;
import buteco.model.service.CargoService;
import buteco.model.service.UsuarioService;

import java.util.List;

public class UsuariosControllerImpl{

    private UsuarioService usuarioService;
    private CargoService cargoService;


    public UsuariosControllerImpl(
            UsuarioService usuarioService,
            CargoService cargoService
    ) {
        this.usuarioService = usuarioService;
        this.cargoService = cargoService;
    }

    public UsuarioResponse usuariosResponse(
            Long id,
            String nome,
            String login,
            String cargo
    ) {
        return new UsuarioResponse(id, nome, login, cargo);
    }

    public UsuarioSelectResponse selectUsuario(Long id, String nome) {
        return new UsuarioSelectResponse(id, nome);
    }

    public CargoResponse cargoResponse(Long id, String nome) {
        return new CargoResponse(id, nome);
    }

    public List<UsuarioResponse> listarUsuarios() {
        return usuarioService.findAllUsuarios()
                .stream()
                .map(usuario -> this.usuariosResponse(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getLogin(),
                        usuario.getCargo().getNome()
                ))
                .toList();
    }

    public List<CargoResponse> listarCargos() {
        return cargoService.findAll()
                .stream()
                .map(c -> this.cargoResponse(c.getId(), c.getNome()))
                .toList();
    }

    public EditUsuarioResponse findUsuario(Long id) {
        UsuarioEntity usuario = usuarioService.findById(id);
        return new EditUsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getLogin(),
                usuario.getCargo().getId()
        );
    }

    public CargoResponse findCargo(Long id) {
        CargoEntity cargo = cargoService.findById(id);
        return new CargoResponse(cargo.getId(), cargo.getNome());
    }

    public UsuarioSelectResponse cadastrarUsuario(
            String nome,
            String login,
            String senha,
            Long fk_id_cargo
    ) {

        CargoEntity cargoEntity = cargoService.findById(fk_id_cargo);


        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNome(nome);
        usuarioEntity.setLogin(login);
        usuarioEntity.setSenha(senha);
        usuarioEntity.setCargo(cargoEntity);

        usuarioService.salvarUsuario(usuarioEntity);

        return new UsuarioSelectResponse(usuarioEntity.getId(), usuarioEntity.getNome());
    }
    public UsuarioSelectResponse editarUsuario(
            Long id_usuario,
            String nome,
            String login,
            String senha,
            Long fk_id_cargo
    ) {
        UsuarioEntity usuario = usuarioService.findById(id_usuario);


        CargoEntity cargoEntity = cargoService.findById(fk_id_cargo);

        usuario.setNome(nome);
        usuario.setLogin(login);

        if (senha != null && !senha.isBlank()) {
            usuario.setSenha(senha);
        }
        usuario.setCargo(cargoEntity);

        usuarioService.atualizarUsuario(usuario);

        return new UsuarioSelectResponse(
                usuario.getId(),
                usuario.getNome()
        );
    }

    public void deletarUsuario(Long id_usuario) {
        UsuarioEntity usuario = usuarioService.findById(id_usuario);
        usuarioService.deletarUsuario(usuario);
    }
}

