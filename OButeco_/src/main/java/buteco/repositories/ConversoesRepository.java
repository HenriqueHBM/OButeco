package buteco.repositories;

import buteco.model.produto.Conversoes;
import jakarta.persistence.EntityManager;

public class ConversoesRepository {
    private EntityManager em;
    public ConversoesRepository(EntityManager em){
        this.em = em;
    }

    public Conversoes findById(Long id){
        return em.find(Conversoes.class, id);
    }

    public void create(Conversoes conversoes){
        em.getTransaction().begin();
        em.persist(conversoes);
        em.getTransaction().commit();
    }
}
