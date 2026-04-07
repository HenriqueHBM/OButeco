package buteco.repositories;

import buteco.model.movimentacoes.MovimentacoesEstoque;
import jakarta.persistence.EntityManager;

public class MovimentacoesEstoqueRepository {
    private EntityManager em;

    public MovimentacoesEstoqueRepository(EntityManager em) {
        this.em = em;
    }

    public MovimentacoesEstoque findById(Long id){
        return em.find(MovimentacoesEstoque.class, id);
    }

    public void create(MovimentacoesEstoque movimentacoesEstoque){
        em.getTransaction().begin();
        em.persist(movimentacoesEstoque);
        em.getTransaction().commit();
    }

    public void update(MovimentacoesEstoque movimentacoesEstoque){
        em.getTransaction().begin();
        em.persist(movimentacoesEstoque);
        em.getTransaction().commit();
    }

    public void delete(MovimentacoesEstoque movimentacoesEstoque){
        em.remove(em.contains(movimentacoesEstoque) ? movimentacoesEstoque : em.merge(movimentacoesEstoque));
    }

}
