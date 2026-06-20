package buteco.model.service.entradas;

import buteco.model.entity.conversao.ConversoesEntity;
import buteco.model.repositories.estoque.ConversoesRepository;

import java.util.List;

public class ConversoesService {
    private final ConversoesRepository conversoesRepository;

    public ConversoesService(ConversoesRepository conversoesRepository) { this.conversoesRepository = conversoesRepository; }

    public List<ConversoesEntity> findAllConversoes () {
        var conversoes = conversoesRepository.findAllConversoes();
        if(conversoes.isEmpty()){
            throw new RuntimeException("Lista ausente");
        }
        return conversoes;
    }

}
