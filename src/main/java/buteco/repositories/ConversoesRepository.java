package buteco.repositories;

import buteco.model.conversao.Conversoes;
import buteco.model.estoque.Estoque;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ConversoesRepository {
    private EntityManager em;
    public ConversoesRepository(EntityManager em){
        this.em = em;
    }

    public Conversoes findById(Long id){
        return em.find(Conversoes.class, id);
    }

    public List<Conversoes> findAllConversoes() {
        return em.createQuery("select c from Conversoes c", Conversoes.class).getResultList();
    }

    public void create(Conversoes conversoes){
        em.getTransaction().begin();
        em.persist(conversoes);
        em.getTransaction().commit();
    }
}
