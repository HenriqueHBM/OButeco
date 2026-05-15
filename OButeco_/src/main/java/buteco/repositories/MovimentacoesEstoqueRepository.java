package buteco.repositories;

import buteco.model.estoque.MovimentacoesEstoque;
import jakarta.persistence.EntityManager;

import java.util.List;

public class MovimentacoesEstoqueRepository {
    private EntityManager em;

    public MovimentacoesEstoqueRepository(EntityManager em) {
        this.em = em;
    }

    public MovimentacoesEstoque findById(Long id){
        return em.find(MovimentacoesEstoque.class, id);
    }

    public List<MovimentacoesEstoque> findAll() {
        return em.createQuery("select m from MovimentacoesEstoque m", MovimentacoesEstoque.class).getResultList();
    }

    public void create(MovimentacoesEstoque movimentacoesEstoque){
        em.getTransaction().begin();
        em.persist(movimentacoesEstoque);
        em.getTransaction().commit();
    }

    public void update(MovimentacoesEstoque movimentacoesEstoque){
        em.getTransaction().begin();
        em.merge(movimentacoesEstoque);
        em.getTransaction().commit();
    }

    public void delete(MovimentacoesEstoque movimentacoesEstoque){
        em.getTransaction().begin();
        em.remove(em.contains(movimentacoesEstoque) ? movimentacoesEstoque : em.merge(movimentacoesEstoque));
        em.getTransaction().commit();
    }

}
