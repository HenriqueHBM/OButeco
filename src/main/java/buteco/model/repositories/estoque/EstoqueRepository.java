package buteco.model.repositories.estoque;

import buteco.model.entity.estoque.EstoqueEntity;
import jakarta.persistence.EntityManager;

import java.util.List;

public class EstoqueRepository {
    private EntityManager em;

    public EstoqueRepository(EntityManager em) {
        this.em = em;
    }

    public EstoqueEntity findById(Long id){
        return em.find(EstoqueEntity.class, id);
    }

    public EstoqueEntity findByProdutoId(Long idProduto) {
        var resultado = em.createQuery("select e from Estoque e where e.produto.id = :idProduto", EstoqueEntity.class)
                .setParameter("idProduto", idProduto)
                .getResultList();

        return resultado.isEmpty() ? null : resultado.get(0);
    }//getResultList no lugar de getSingleResult para poder aparecer a mensagem certa de erro na entrada

        public List<EstoqueEntity> findAll() {
            return em.createQuery("select e from Estoque e order by e.id asc", EstoqueEntity.class).getResultList();
        }

    public void create(EstoqueEntity estoqueEntity){
        em.getTransaction().begin();
        em.persist(estoqueEntity);
        em.getTransaction().commit();
    }

    public void update(EstoqueEntity estoqueEntity){
        em.getTransaction().begin();
        em.persist(estoqueEntity);
        em.getTransaction().commit();
    }

    public void delete(EstoqueEntity estoqueEntity){
        em.remove(em.contains(estoqueEntity) ? estoqueEntity : em.merge(estoqueEntity));
    }
}
