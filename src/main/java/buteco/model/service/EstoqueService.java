package buteco.model.service;

import buteco.model.entity.conversao.ConversoesEntity;
import buteco.model.entity.estoque.EstoqueEntity;
import buteco.model.entity.produto.ProdutoEntity;
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

    public EstoqueService(EstoqueRepository estoqueRepository, ProdutoRepository produtoRepository, ConversoesRepository conversoesRepository, ErroEntrada erroEntrada) {
        this.estoqueRepository = estoqueRepository;
        this.produtoRepository = produtoRepository;
        this.conversoesRepository = conversoesRepository;
        this.erroEntrada = erroEntrada;
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

    public void criarNovoEstoque(Long idProduto){
        var medidas = conversoesRepository.findAllConversoes();
        medidas.stream().forEach(System.out::println);  //lista todos as unidades de conversao

        EstoqueEntity estoqueEntity = new EstoqueEntity();

        ProdutoEntity produtoEntity = produtoRepository.findById(idProduto);

        Long idConversao = erroEntrada.trataEntradaLong("Insira o codigo da unidade de medida: ");
        ConversoesEntity conversoesEntity = conversoesRepository.findById(idConversao);

        String localizacao = erroEntrada.trataEntradaString("Insira a localizacao do produto no estoque: ");

        estoqueEntity.setProduto(produtoEntity);
        estoqueEntity.setQntdEstoque(0);
        estoqueEntity.setConversoes(conversoesEntity);
        estoqueEntity.setLocal(localizacao);

        estoqueRepository.create(estoqueEntity);
    }

    public void createSimpleEstoque(ProdutoEntity prod){
        EstoqueEntity estoqueEntity = new EstoqueEntity();
        estoqueEntity.setProduto(prod);
        ConversoesEntity conversoesEntity = conversoesRepository.findById(3L); // 3 = (unidade)
        estoqueEntity.setConversoes(conversoesEntity);

        estoqueRepository.create(estoqueEntity);
    }
}
