package buteco.model.service;

import buteco.model.entity.pessoa.Cargo;
import buteco.model.entity.pessoa.Usuario;
import buteco.model.repositories.pessoa.UsuarioRepository;

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
