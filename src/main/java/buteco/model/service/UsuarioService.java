package buteco.model.service;

import buteco.model.entity.pessoa.CargoEntity;
import buteco.model.entity.pessoa.UsuarioEntity;
import buteco.model.repositories.pessoa.UsuarioRepository;

import java.util.List;

public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioEntity> findAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public void salvarUsuario(UsuarioEntity usuario) {

        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo.");
        }
        if (usuario.getNome() == null || usuario.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }
        if (usuario.getLogin() == null || usuario.getLogin().isBlank()) {
            throw new IllegalArgumentException("Login é obrigatório.");
        }
        if (usuario.getSenha() == null || usuario.getSenha().isBlank()) {
            throw new IllegalArgumentException("Senha é obrigatória.");
        }
        usuarioRepository.create(usuario);
    }


    public void atualizarUsuario(UsuarioEntity usuario) {
        usuarioRepository.update(usuario);
    }

    public void deletarUsuario(UsuarioEntity usuario) {
        usuarioRepository.delete(usuario);
    }

    public UsuarioEntity login(String login, String senha) {
        UsuarioEntity usuarioEntity = usuarioRepository.findByLogin(login);

        if (usuarioEntity == null) {
            return null;
        }
        if(!usuarioEntity.getSenha().equals(senha)) {
            return null;
        }
        return usuarioEntity;
    }


    public UsuarioEntity findById(Long id) {
        return usuarioRepository.findById(id);
    }
}
