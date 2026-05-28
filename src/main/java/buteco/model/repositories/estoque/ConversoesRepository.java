package buteco.model.repositories.estoque;

import buteco.model.entity.conversao.ConversoesEntity;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ConversoesRepository {
    private EntityManager em;
    public ConversoesRepository(EntityManager em){
        this.em = em;
    }

    public ConversoesEntity findById(Long id){
        return em.find(ConversoesEntity.class, id);
    }

    public List<ConversoesEntity> findAllConversoes() {
        return em.createQuery("select c from Conversoes c", ConversoesEntity.class).getResultList();
    }

    public void create(ConversoesEntity conversoesEntity){
        em.getTransaction().begin();
        em.persist(conversoesEntity);
        em.getTransaction().commit();
    }
}
