package buteco.repositories;

import buteco.model.estoque.Estoque;
import jakarta.persistence.EntityManager;

public class EstoqueRepository {
    private EntityManager em;

    public EstoqueRepository(EntityManager em) {
        this.em = em;
    }

    public Estoque findById(Long id){
        return em.find(Estoque.class, id);
    }

    public void create(Estoque estoque){
        em.getTransaction().begin();
        em.persist(estoque);
        em.getTransaction().commit();
    }

    public void update(Estoque estoque){
        em.getTransaction().begin();
        em.persist(estoque);
        em.getTransaction().commit();
    }

    public void delete(Estoque estoque){
        em.remove(em.contains(estoque) ? estoque : em.merge(estoque));
    }
}
