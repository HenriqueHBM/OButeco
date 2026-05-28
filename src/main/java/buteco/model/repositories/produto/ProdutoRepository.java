package buteco.model.repositories.produto;

import buteco.model.entity.produto.ProdutoEntity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

public class ProdutoRepository {
    private EntityManager em;

    public ProdutoRepository(EntityManager em){
        this.em = em;
    }

    public ProdutoEntity findById(Long id){
        return em.find(ProdutoEntity.class, id);
    }

    public void create(ProdutoEntity produtoEntity){
        em.getTransaction().begin();
        em.persist(produtoEntity);
        em.getTransaction().commit();
    }

    public void deletar(long id){
        var pessoa = findById(id);
        if(pessoa != null){
            em.remove(pessoa);
        }
    }

    public List<ProdutoEntity> findAll() {
        return em.createQuery("select p from Produto p order by p.id asc", ProdutoEntity.class).getResultList();
    }

    @Transactional
    public void excluirProduto(ProdutoEntity prod){
        em.getTransaction().begin();
        em.merge(prod);
        em.getTransaction().commit();
    }

    public void atualizarProduto(ProdutoEntity produtoEntity) {
        em.getTransaction().begin();
        em.merge(produtoEntity);
        em.getTransaction().commit();
    }

    public ProdutoEntity findByIdComInsumos(Long id) {
        return em.createQuery(
                        "SELECT p FROM Produto p LEFT JOIN FETCH p.insumos i LEFT JOIN FETCH i.insumo WHERE p.id = :id",
                        ProdutoEntity.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
