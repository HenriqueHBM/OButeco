package buteco.repositories;

import buteco.model.produto.Produto;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

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
        return em.createQuery("select p from Produto p order by p.id asc", Produto.class).getResultList();
    }

    @Transactional
    public void excluirProduto(Produto prod){
        em.getTransaction().begin();
        em.merge(prod);
        em.getTransaction().commit();
    }

    public void atualizarProduto(Produto produto) {
        em.getTransaction().begin();
        em.merge(produto);
        em.getTransaction().commit();
    }

    public Produto findByIdComInsumos(Long id) {
        return em.createQuery(
                        "SELECT p FROM Produto p LEFT JOIN FETCH p.insumos i LEFT JOIN FETCH i.insumo WHERE p.id = :id",
                        Produto.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
