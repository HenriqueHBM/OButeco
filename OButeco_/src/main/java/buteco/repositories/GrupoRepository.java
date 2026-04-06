package buteco.repositories;

import buteco.model.produto.Grupo;
import jakarta.persistence.EntityManager;

public class GrupoRepository {
    private EntityManager em;

    public GrupoRepository(EntityManager em){this.em = em;}

    public void create(Grupo grupo){
        em.getTransaction().begin();
        em.persist(grupo);
        em.getTransaction().commit();
    }

    public Grupo findById(Long id){
        return em.find(Grupo.class, id);
    }
}
