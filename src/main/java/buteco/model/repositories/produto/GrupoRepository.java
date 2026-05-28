package buteco.model.repositories.produto;

import buteco.model.entity.produto.GrupoEntity;
import jakarta.persistence.EntityManager;

import java.util.List;

public class GrupoRepository {
    private EntityManager em;

    public GrupoRepository(EntityManager em){this.em = em;}

    public GrupoEntity findById(Long id){ return em.find(GrupoEntity.class, id); }

    public void create(GrupoEntity produto){
        em.getTransaction().begin();
        em.persist(produto);
        em.getTransaction().commit();
    }

    public List<GrupoEntity>findAll(){
        return em.createQuery("select c from Grupo c", GrupoEntity.class).getResultList();
    }
}
