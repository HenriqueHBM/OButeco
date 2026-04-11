package buteco.service;

import buteco.model.produto.Produto;
import buteco.repositories.InsumosProdutoRepository;
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
}
