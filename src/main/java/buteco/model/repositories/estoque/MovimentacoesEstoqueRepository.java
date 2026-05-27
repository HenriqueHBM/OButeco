package buteco.model.repositories.estoque;

import buteco.model.entity.estoque.MovimentacoesEstoqueEntity;
import jakarta.persistence.EntityManager;

import java.util.List;

public class MovimentacoesEstoqueRepository {
    private EntityManager em;

    public MovimentacoesEstoqueRepository(EntityManager em) {
        this.em = em;
    }

    public MovimentacoesEstoqueEntity findById(Long id){
        return em.find(MovimentacoesEstoqueEntity.class, id);
    }

    public List<MovimentacoesEstoqueEntity> findAll() {
        return em.createQuery("select m from MovimentacoesEstoque m order by m.id asc", MovimentacoesEstoqueEntity.class).getResultList();
    }

    public void create(MovimentacoesEstoqueEntity movimentacoesEstoqueEntity){
        em.getTransaction().begin();
        em.persist(movimentacoesEstoqueEntity);
        em.getTransaction().commit();
    }

    public void update(MovimentacoesEstoqueEntity movimentacoesEstoqueEntity){
        em.getTransaction().begin();
        em.merge(movimentacoesEstoqueEntity);
        em.getTransaction().commit();
    }

    public void delete(MovimentacoesEstoqueEntity movimentacoesEstoqueEntity){
        em.getTransaction().begin();
        em.remove(em.contains(movimentacoesEstoqueEntity) ? movimentacoesEstoqueEntity : em.merge(movimentacoesEstoqueEntity));
        em.getTransaction().commit();
    }

}
