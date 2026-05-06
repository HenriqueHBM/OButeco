package buteco.repositories;

import buteco.model.pessoa.Usuario;
import buteco.model.pessoa.Cargo;
import jakarta.persistence.EntityManager;

public class UsuarioRepository {
    private EntityManager em;

    public UsuarioRepository(EntityManager em) { this.em = em; }

    public void create(Usuario usuario) {
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
    }

    public Usuario findByLogin(String login) {
        return em.createQuery(
                        "Select u From Usuario u Where u.login = :login",
                        Usuario.class
                )
                .setParameter("login", login)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

}
