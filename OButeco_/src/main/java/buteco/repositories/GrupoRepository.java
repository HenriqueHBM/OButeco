package buteco.repositories;

import buteco.model.produto.Grupo;
import jakarta.persistence.EntityManager;

import java.util.List;

public class GrupoRepository {
    private EntityManager em;

    public GrupoRepository(EntityManager em){this.em = em;}

    public Grupo findById(Long id){ return em.find(Grupo.class, id); }

    public void create(Grupo produto){
        em.getTransaction().begin();
        em.persist(produto);
        em.getTransaction().commit();
    }

    public List<Grupo>findAll(){
        return em.createQuery("select c from Grupo c", Grupo.class).getResultList();
    }
}
