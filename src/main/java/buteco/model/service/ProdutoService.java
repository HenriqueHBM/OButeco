package buteco.model.service;

import buteco.model.entity.produto.ProdutoEntity;
import buteco.model.repositories.produto.ProdutoRepository;

import java.util.List;

public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoEntity> findAllProdutos()
    {
        var produtos = produtoRepository.findAll();
        if(produtos.isEmpty()){
            throw new RuntimeException("Lista ausente");
        }
        return produtos;
    }

    public void salvarProduto(ProdutoEntity produtoEntity){
        produtoRepository.create(produtoEntity);
    }

    public ProdutoEntity findById(Long id){

        if(produtoRepository.findById(id) == null ){
            throw new IllegalArgumentException("Valor Nao encontrado");
        }
        return produtoRepository.findById(id);
    }

    public void excluirProduto(ProdutoEntity prod){
        produtoRepository.excluirProduto(prod);
    }

    public void atualizarProduto(ProdutoEntity produtoEntity) {
        produtoRepository.atualizarProduto(produtoEntity);
    }

    public ProdutoEntity findByIdComInsumos(Long id) {
        return produtoRepository.findByIdComInsumos(id);
    }
}
