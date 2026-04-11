package buteco.repositories;

import buteco.model.produto.InsumosProduto;
import buteco.model.produto.Produto;
import jakarta.persistence.EntityManager;

public class InsumosProdutoRepository {
    private EntityManager em;

    public InsumosProdutoRepository(EntityManager em){this.em = em;}

    public void create(InsumosProduto insumosProduto){
        em.getTransaction().begin();
        em.persist(insumosProduto);
        em.getTransaction().commit();
    }

    public InsumosProduto findById(Long id){
        return em.find(InsumosProduto.class, id);
    }

    public void deletar(long id){
        var insumo = findById(id);
        if(insumo != null){
            em.remove(insumo);
        }
    }
}
