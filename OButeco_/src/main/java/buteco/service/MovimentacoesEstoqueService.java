package buteco.service;

import buteco.model.estoque.Estoque;
import buteco.model.estoque.MovimentacoesEstoque;
import buteco.repositories.EstoqueRepository;
import buteco.repositories.MovimentacoesEstoqueRepository;

public class MovimentacoesEstoqueService {
    MovimentacoesEstoqueRepository movimentacoesEstoqueRepository;
    EstoqueRepository estoqueRepository;

    public MovimentacoesEstoqueService(MovimentacoesEstoqueRepository movimentacoesEstoqueRepository, EstoqueRepository estoqueRepository) {
        this.movimentacoesEstoqueRepository = movimentacoesEstoqueRepository;
        this.estoqueRepository = estoqueRepository;
    }

    //    public void salvarMovimentacao(MovimentacoesEstoque movimentacoesEstoque) {
//        movimentacoesEstoqueRepository.create(movimentacoesEstoque);
//    }

    public void cadastrarEntrada(Long idProduto, double qtde){
        Estoque estoque = estoqueRepository.findByProdutoId(idProduto);
        if (estoque == null){
            throw new RuntimeException("Estoque nao encontrado para esse COD.");
        }

        estoque.setQntdEstoque(estoque.getQntdEstoque() + qtde);
        estoqueRepository.update(estoque);
    }

    public void cadastrarSaida(Long idProduto, double qtde){
        Estoque estoque = estoqueRepository.findByProdutoId(idProduto);
        if (estoque == null){
            throw new RuntimeException("Estoque nao encontrado para esse COD.");
        }
        if (qtde > estoque.getQntdEstoque()){
            throw new RuntimeException("Quantidade insuficiente no estoque, tente novamente!");
        }

        estoque.setQntdEstoque(estoque.getQntdEstoque() - qtde);
        estoqueRepository.update(estoque);
    }

}