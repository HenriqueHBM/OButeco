package buteco.model.repositories.pessoa;

import buteco.model.entity.pessoa.Cargo;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CargoRepository {
    private EntityManager em;

    public CargoRepository(EntityManager em) { this.em = em; }

    public Cargo findById(Long id){
        return em.find(Cargo.class, id);
    }

    public void create(Cargo cargo) {
        em.getTransaction().begin();
        em.persist(cargo);
        em.getTransaction().commit();
    }

    public List<Cargo> findAll(){
        return em.createQuery("Select c From Cargo c", Cargo.class)
                .getResultList();
    }
}
