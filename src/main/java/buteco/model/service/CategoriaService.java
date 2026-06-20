package buteco.model.service;

import buteco.model.entity.produto.CategoriaEntity;
import buteco.model.repositories.produto.CategoriaRepository;

import java.util.List;

public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) { this.categoriaRepository = categoriaRepository; }

    public List<CategoriaEntity> findAllCategoria(){
        var categoria = categoriaRepository.findAll();
        if(categoria.isEmpty()){
            throw new RuntimeException("Lista ausente");
        }
        return categoria;
    }

    public CategoriaEntity findById(Long id){

        if(categoriaRepository.findById(id) == null ){
             throw new IllegalArgumentException("Valor Nao encontrado");
        }
        return categoriaRepository.findById(id);

    }
}
