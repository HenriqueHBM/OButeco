package buteco.model.service;

import buteco.model.entity.pessoa.CargoEntity;
import buteco.model.entity.pessoa.UsuarioEntity;
import buteco.model.repositories.pessoa.UsuarioRepository;

public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
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

    public void cadastrar(String nome, String login, String senha, CargoEntity cargoEntity) {
        UsuarioEntity u = new UsuarioEntity(null, nome, login, senha, cargoEntity);
        usuarioRepository.create(u);
    }

    public UsuarioEntity findById(Long id) {
        return usuarioRepository.findById(id);
    }
}
