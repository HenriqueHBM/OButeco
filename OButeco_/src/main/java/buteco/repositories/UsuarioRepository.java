package buteco.repositories;

import buteco.model.pessoa.Usuario;
import buteco.model.pessoa.Cargo;
import jakarta.persistence.EntityManager;

public class UsuarioRepository {
    private EntityManager em;

    public UsuarioRepository(EntityManager em) { this.em = em; }

    public void create(Usuario usuario) {
        em.persist(usuario);
    }

}
