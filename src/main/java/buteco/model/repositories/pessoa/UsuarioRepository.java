package buteco.model.repositories.pessoa;

import buteco.model.entity.pessoa.Usuario;
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

    public Usuario findById(Long id) {
        return em.find(Usuario.class, id);
    }

    public java.util.List<Usuario> findAll() {
        return em.createQuery("Select u From Usuario u", Usuario.class).getResultList();
    }

    public void update(Usuario usuario) {
        em.getTransaction().begin();
        em.merge(usuario);
        em.getTransaction().commit();
    }

    public void delete(Usuario usuario) {
        em.getTransaction().begin();
        em.remove(em.contains(usuario) ? usuario : em.merge(usuario));
        em.getTransaction().commit();
    }

}
