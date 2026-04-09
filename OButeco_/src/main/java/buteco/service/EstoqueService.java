package buteco.service;

import buteco.model.estoque.Estoque;
import buteco.repositories.EstoqueRepository;
import buteco.service.entradas.ErroEntrada;

import java.util.List;

public class EstoqueService {
    private final EstoqueRepository estoqueRepository;

    public EstoqueService(EstoqueRepository estoqueRepository) { this.estoqueRepository = estoqueRepository; }

    public List<Estoque> findAllEstoques(){
        var estoques = estoqueRepository.findAll();
        if(estoques.isEmpty()){
            throw new RuntimeException("Estoque ausente!");
        }
        return estoques;
    }
}
