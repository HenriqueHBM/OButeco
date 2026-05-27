package buteco.model.repositories.estoque;

import buteco.model.entity.estoque.Estoque;
import jakarta.persistence.EntityManager;

import java.util.List;

public class EstoqueRepository {
    private EntityManager em;

    public EstoqueRepository(EntityManager em) {
        this.em = em;
    }

    public Estoque findById(Long id){
        return em.find(Estoque.class, id);
    }

    public Estoque findByProdutoId(Long idProduto) {
        var resultado = em.createQuery("select e from Estoque e where e.produto.id = :idProduto", Estoque.class)
                .setParameter("idProduto", idProduto)
                .getResultList();

        return resultado.isEmpty() ? null : resultado.get(0);
    }//getResultList no lugar de getSingleResult para poder aparecer a mensagem certa de erro na entrada

        public List<Estoque> findAll() {
            return em.createQuery("select e from Estoque e order by e.id asc", Estoque.class).getResultList();
        }

    public void create(Estoque estoque){
        em.getTransaction().begin();
        em.persist(estoque);
        em.getTransaction().commit();
    }

    public void update(Estoque estoque){
        em.getTransaction().begin();
        em.persist(estoque);
        em.getTransaction().commit();
    }

    public void delete(Estoque estoque){
        em.remove(em.contains(estoque) ? estoque : em.merge(estoque));
    }
}
