package buteco.model.repositories.pessoa;

import buteco.model.entity.pessoa.CargoEntity;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CargoRepository {
    private EntityManager em;

    public CargoRepository(EntityManager em) { this.em = em; }

    public CargoEntity findById(Long id){
        return em.find(CargoEntity.class, id);
    }

    public void create(CargoEntity cargoEntity) {
        em.getTransaction().begin();
        em.persist(cargoEntity);
        em.getTransaction().commit();
    }

    public List<CargoEntity> findAll(){
        return em.createQuery("Select c From CargoEntity c", CargoEntity.class)
                .getResultList();
    }
}
