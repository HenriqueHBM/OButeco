package buteco.model.service;

import buteco.model.entity.produto.InsumosProdutoEntity;
import buteco.model.repositories.produto.InsumosProdutoRepository;
import jakarta.transaction.Transactional;

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
//    public void salvarProduto(Produto produto){
//        produtoRepository.create(produto);
//    }
}
