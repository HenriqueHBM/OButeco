package buteco.service;

import buteco.model.pessoa.Cargo;
import buteco.model.pessoa.Usuario;
import buteco.repositories.UsuarioRepository;
import jakarta.persistence.EntityManager;

public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario login(String login, String senha) {
        Usuario usuario = usuarioRepository.findByLogin(login);

        if (usuario == null) {
            return null;
        }
        if(!usuario.getSenha().equals(senha)) {
            return null;
        }
        return usuario;
    }

    public void cadastrar(String nome, String login, String senha, Cargo cargo) {
        Usuario u = new Usuario(null, nome, login, senha, cargo);
        usuarioRepository.create(u);
    }
}
