package buteco.service;

import buteco.model.estoque.Estoque;
import buteco.repositories.EstoqueRepository;
import buteco.service.entradas.ErroEntrada;

import java.util.List;

public class EstoqueService {
    private final EstoqueRepository estoqueRepository;

    public EstoqueService(EstoqueRepository estoqueRepository) { this.estoqueRepository = estoqueRepository; }

    public List<Estoque> findAllEstoques(){
        var estoques = estoqueRepository.findAll();
        if(estoques.isEmpty()){
            throw new RuntimeException("Estoque ausente!");
        }
        return estoques;
    }

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
