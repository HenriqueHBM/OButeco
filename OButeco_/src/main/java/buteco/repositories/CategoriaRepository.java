package buteco.repositories;

import buteco.model.produto.Categoria;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CategoriaRepository {
    private EntityManager em;

    public CategoriaRepository(EntityManager em){
        this.em = em;
    }

    public Categoria findById(Long id){
        return em.find(Categoria.class, id);
    }

    public void create(Categoria produto){
        em.getTransaction().begin();
        em.persist(produto);
        em.getTransaction().commit();
    }

    public List<Categoria> findAll(){
        return em.createQuery("select c from categorias c", Categoria.class).getResultList();
    }
}
