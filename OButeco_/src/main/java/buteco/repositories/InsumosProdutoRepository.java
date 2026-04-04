package buteco.repositories;

import buteco.model.produto.InsumosProduto;
import jakarta.persistence.EntityManager;

public class InsumosProdutoRepository {
    private EntityManager em;

    public InsumosProdutoRepository(EntityManager em){this.em = em;}

    public void create(InsumosProduto insumosProduto){
        em.getTransaction().begin();
        em.persist(insumosProduto);
        em.getTransaction().commit();
    }
}
