package buteco.repositories;

import buteco.model.estoque.Estoque;
import buteco.model.estoque.MovimentacoesEstoque;
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

    public List<Estoque> findAll() {
        return em.createQuery("select e from estoque p", Estoque.class).getResultList();
    }

    public void create(Estoque estoque){
        em.getTransaction().begin();
        em.persist(estoque);
        em.getTransaction().commit();
        em.close();
    }

    public void update(Estoque estoque){
        em.getTransaction().begin();
        em.persist(estoque);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Estoque estoque){
        em.remove(em.contains(estoque) ? estoque : em.merge(estoque));
    }
}
