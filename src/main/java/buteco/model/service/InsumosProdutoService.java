package buteco.model.service;

import buteco.model.entity.produto.InsumosProdutoEntity;
import buteco.model.repositories.produto.InsumosProdutoRepository;
import jakarta.transaction.Transactional;

import java.util.List;

public class InsumosProdutoService {
    private final InsumosProdutoRepository insumosProdutoRepository;

    public InsumosProdutoService(InsumosProdutoRepository insumosProdutoRepository){
        this.insumosProdutoRepository = insumosProdutoRepository;
    }

    @Transactional
    public void deletarInsumos(Long id){
        insumosProdutoRepository.deletar(id);
    }

    public void salvarInsumo(InsumosProdutoEntity insumosProdutoEntity)
    {
        insumosProdutoRepository.create(insumosProdutoEntity);
    }

    public List<InsumosProdutoEntity> findAllInsumosProduto(Long id_produto){
        return insumosProdutoRepository.findAllInsumosProdutos(id_produto);
//        if(insumos.isEmpty()){
//            throw new RuntimeException("Lista ausente");
//        }
//        return insumos;
    }
}
