package buteco.model.service;

import buteco.model.entity.produto.Produto;
import buteco.model.repositories.produto.ProdutoRepository;

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

    public void excluirProduto(Produto prod){
        produtoRepository.excluirProduto(prod);
    }

    public void atualizarProduto(Produto produto) {
        produtoRepository.atualizarProduto(produto);
    }
}
