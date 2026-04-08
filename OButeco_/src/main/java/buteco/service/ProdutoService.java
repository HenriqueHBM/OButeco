package buteco.service;

import buteco.model.produto.Produto;
import buteco.repositories.ProdutoRepository;

import java.util.List;

public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> findAllProdutos()
    {
        var produtos = produtoRepository.findAll();
        if(produtos.isEmpty()){
            throw new RuntimeException("Lista ausente");
        }
        return produtos;
    }
}
