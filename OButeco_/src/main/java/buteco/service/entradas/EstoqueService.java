package buteco.service.entradas;

import buteco.model.estoque.Estoque;
import buteco.repositories.EstoqueRepository;

public class EstoqueService {
    EstoqueRepository estoqueRepository;

    public void salvarEstoque(Estoque estoque){
        if(estoque != null) {
            estoqueRepository.create(estoque);
        }
    }
}
