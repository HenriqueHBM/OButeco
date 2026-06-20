package buteco.model.service;

import buteco.model.entity.pessoa.CargoEntity;
import buteco.model.repositories.pessoa.CargoRepository;

import java.util.List;

public class CargoService {

    private final CargoRepository cargoRepository;

    public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public void create(CargoEntity cargo) {

        if (cargo == null) {
            throw new IllegalArgumentException("Cargo não pode ser nulo.");
        }

        if (cargo.getNome() == null || cargo.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do cargo é obrigatório.");
        }

        cargoRepository.create(cargo);
    }

    public void update(CargoEntity cargo) {

        if (cargo == null || cargo.getId() == null) {
            throw new IllegalArgumentException("Cargo inválido.");
        }

        cargoRepository.update(cargo);
    }

    public void delete(Long id) {

        CargoEntity cargo = cargoRepository.findById(id);

        if (cargo == null) {
            throw new IllegalArgumentException("Cargo não encontrado.");
        }

        cargoRepository.delete(cargo);
    }

    public CargoEntity findById(Long id) {
        return cargoRepository.findById(id);
    }

    public List<CargoEntity> findAll() {
        return cargoRepository.findAll();
    }
}