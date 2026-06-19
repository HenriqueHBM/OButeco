package buteco.model.repositories.pessoa;

import buteco.model.entity.pessoa.UsuarioEntity;
import jakarta.persistence.EntityManager;

public class UsuarioRepository {
    private EntityManager em;

    public UsuarioRepository(EntityManager em) { this.em = em; }

    public void create(UsuarioEntity usuarioEntity) {
        em.getTransaction().begin();
        em.persist(usuarioEntity);
        em.getTransaction().commit();
    }

    public UsuarioEntity findByLogin(String login) {
        return em.createQuery(
                        "Select u From Usuario u Where u.login = :login",
                        UsuarioEntity.class
                )
                .setParameter("login", login)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    public UsuarioEntity findById(Long id) {
        return em.find(UsuarioEntity.class, id);
    }

    public java.util.List<UsuarioEntity> findAll() {
        return em.createQuery("Select u From UsuarioEntity u", UsuarioEntity.class).getResultList();
    }

    public void update(UsuarioEntity usuarioEntity) {
        em.getTransaction().begin();
        em.merge(usuarioEntity);
        em.getTransaction().commit();
    }

    public void delete(UsuarioEntity usuarioEntity) {
        em.getTransaction().begin();
        em.remove(em.contains(usuarioEntity) ? usuarioEntity : em.merge(usuarioEntity));
        em.getTransaction().commit();
    }

}
