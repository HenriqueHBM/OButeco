package buteco.service;

import buteco.model.produto.Categoria;
import buteco.repositories.CategoriaRepository;

import java.util.List;

public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) { this.categoriaRepository = categoriaRepository; }

    public List<Categoria> findAllCategoria(){
        var categoria = categoriaRepository.findAll();
        if(categoria.isEmpty()){
            throw new RuntimeException("Lista ausente");
        }
        return categoria;
    }

    public Categoria findById(Long id){

        if(categoriaRepository.findById(id) == null ){
             throw new IllegalArgumentException("Valor Nao encontrado");
        }
        return categoriaRepository.findById(id);

    }
}
