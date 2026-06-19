package buteco.model.service;

import buteco.model.entity.estoque.EstoqueEntity;
import buteco.model.repositories.estoque.ConversoesRepository;
import buteco.model.repositories.estoque.EstoqueRepository;
import buteco.model.repositories.produto.ProdutoRepository;
import buteco.model.service.entradas.ErroEntrada;

import java.util.List;

public class EstoqueService {
    private final EstoqueRepository estoqueRepository;
    private final ProdutoRepository produtoRepository;
    private final ConversoesRepository conversoesRepository;
    private ErroEntrada erroEntrada;

    public EstoqueService(EstoqueRepository estoqueRepository, ProdutoRepository produtoRepository, ConversoesRepository conversoesRepository) {
        this.estoqueRepository = estoqueRepository;
        this.produtoRepository = produtoRepository;
        this.conversoesRepository = conversoesRepository;
    }

    public List<EstoqueEntity> findAllEstoques(){
        var estoques = estoqueRepository.findAll(); //lista todos os estoques
        if(estoques.isEmpty()){
            throw new RuntimeException("Estoque ausente!");
        }
        return estoques;
    }

    public String getUnidadeEstoquePorProduto(Long idProduto) {
        EstoqueEntity estoqueEntity = estoqueRepository.findByProdutoId(idProduto);
        if (estoqueEntity == null) return null;
        return estoqueEntity.getConversoes().getNomenclatura();
    }
}
