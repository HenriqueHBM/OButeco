package buteco.model.service;

import buteco.model.entity.produto.InsumosProduto;
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

    public void salvarInsumo(InsumosProduto insumosProduto)
    {
        insumosProdutoRepository.create(insumosProduto);
    }
//    public void salvarProduto(Produto produto){
//        produtoRepository.create(produto);
//    }
}
