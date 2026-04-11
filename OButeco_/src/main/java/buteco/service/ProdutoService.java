package buteco.service;

import buteco.model.produto.Categoria;
import buteco.model.produto.Produto;
import buteco.repositories.InsumosProdutoRepository;
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

    public void salvarProduto(Produto produto){
        produtoRepository.create(produto);
    }

    public Produto findById(Long id){

        if(produtoRepository.findById(id) == null ){
            throw new IllegalArgumentException("Valor Nao encontrado");
        }
        return produtoRepository.findById(id);

    }
}
