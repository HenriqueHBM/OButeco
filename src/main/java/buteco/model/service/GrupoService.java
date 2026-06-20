package buteco.model.service;

import buteco.model.entity.produto.GrupoEntity;
import buteco.model.repositories.produto.GrupoRepository;

import java.util.List;

public class GrupoService {
    private final GrupoRepository grupoRepository;

    public GrupoService(GrupoRepository grupoRepository) {this.grupoRepository = grupoRepository; }

    public List<GrupoEntity> findAllGrupo(){
        var grupo = grupoRepository.findAll();
        if(grupo.isEmpty()){
            throw new RuntimeException("Lista ausente");
        }
        return grupo;
    }

    public GrupoEntity findById (Long id){
        return grupoRepository.findById(id);
    }
}
