package buteco.model.repositories.produto;

import buteco.model.entity.produto.InsumosProdutoEntity;
import jakarta.persistence.EntityManager;

public class InsumosProdutoRepository {
    private EntityManager em;

    public InsumosProdutoRepository(EntityManager em){this.em = em;}

    public void create(InsumosProdutoEntity insumosProdutoEntity){
        em.getTransaction().begin();
        em.persist(insumosProdutoEntity);
        em.getTransaction().commit();
    }

    public InsumosProdutoEntity findById(Long id){
        return em.find(InsumosProdutoEntity.class, id);
    }

    public void deletar(long id){
        var insumo = findById(id);
        if(insumo != null){
            em.remove(insumo);
        }
    }
}
