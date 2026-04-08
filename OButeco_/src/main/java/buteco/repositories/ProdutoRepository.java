package buteco.repositories;

import buteco.model.produto.Produto;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ProdutoRepository {
    private EntityManager em;

    public ProdutoRepository(EntityManager em){
        this.em = em;
    }

    public Produto findById(Long id){
        return em.find(Produto.class, id);
    }

    public void create(Produto produto){
        em.getTransaction().begin();
        em.persist(produto);
        em.getTransaction().commit();
    }

    public void deletar(long id){
        var pessoa = findById(id);
        if(pessoa != null){
            em.remove(pessoa);
        }
    }

    public List<Produto> findAll() {
        return em.createQuery("select p from Produto p", Produto.class).getResultList();
    }
}
