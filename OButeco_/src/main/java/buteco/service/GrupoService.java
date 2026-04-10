package buteco.service;

import buteco.model.produto.Grupo;
import buteco.repositories.GrupoRepository;

import java.util.List;

public class GrupoService {
    private final GrupoRepository grupoRepository;

    public GrupoService(GrupoRepository grupoRepository) {this.grupoRepository = grupoRepository; }

    public List<Grupo> findAllGrupo(){
        var grupo = grupoRepository.findAll();
        if(grupo.isEmpty()){
            throw new RuntimeException("Lista ausente");
        }
        return grupo;
    }

    public Grupo findById (Long id){
        return grupoRepository.findById(id);
    }
}
