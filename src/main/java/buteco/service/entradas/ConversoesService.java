package buteco.service.entradas;

import buteco.model.conversao.Conversoes;
import buteco.repositories.ConversoesRepository;

import java.util.List;

public class ConversoesService {
    private final ConversoesRepository conversoesRepository;

    public ConversoesService(ConversoesRepository conversoesRepository) { this.conversoesRepository = conversoesRepository; }

    public List<Conversoes> findAllConversoes () {
        var conversoes = conversoesRepository.findAllConversoes();
        if(conversoes.isEmpty()){
            throw new RuntimeException("Lista ausente");
        }
        return conversoes;
    }

}
