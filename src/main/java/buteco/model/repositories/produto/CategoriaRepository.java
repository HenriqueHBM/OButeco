package buteco.model.repositories.produto;

import buteco.model.entity.produto.CategoriaEntity;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CategoriaRepository {
    private EntityManager em;

    public CategoriaRepository(EntityManager em){
        this.em = em;
    }

    public CategoriaEntity findById(Long id){
        return em.find(CategoriaEntity.class, id);
    }

    public void create(CategoriaEntity produto){
        em.getTransaction().begin();
        em.persist(produto);
        em.getTransaction().commit();
    }

    public List<CategoriaEntity> findAll(){
        return em.createQuery("select c from Categoria c", CategoriaEntity.class).getResultList();
    }
}
