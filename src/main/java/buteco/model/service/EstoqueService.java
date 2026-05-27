package buteco.model.service;

import buteco.model.entity.conversao.Conversoes;
import buteco.model.entity.estoque.Estoque;
import buteco.model.entity.produto.Produto;
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

    public List<Estoque> findAllEstoques(){
        var estoques = estoqueRepository.findAll(); //lista todos os estoques
        if(estoques.isEmpty()){
            throw new RuntimeException("Estoque ausente!");
        }
        return estoques;
    }

    public String getUnidadeEstoquePorProduto(Long idProduto) {
        Estoque estoque = estoqueRepository.findByProdutoId(idProduto);
        if (estoque == null) return null;
        return estoque.getConversoes().getNomenclatura();
    }

    public void criarNovoEstoque(Long idProduto){
        var medidas = conversoesRepository.findAllConversoes();
        medidas.stream().forEach(System.out::println);  //lista todos as unidades de conversao

        Estoque estoque = new Estoque();

        Produto produto = produtoRepository.findById(idProduto);

        Long idConversao = erroEntrada.trataEntradaLong("Insira o codigo da unidade de medida: ");
        Conversoes conversoes = conversoesRepository.findById(idConversao);

        String localizacao = erroEntrada.trataEntradaString("Insira a localizacao do produto no estoque: ");

        estoque.setProduto(produto);
        estoque.setQntdEstoque(0);
        estoque.setConversoes(conversoes);
        estoque.setLocal(localizacao);

        estoqueRepository.create(estoque);
    }

    public void createSimpleEstoque(Produto prod){
        Estoque estoque = new Estoque();
        estoque.setProduto(prod);
        Conversoes conversoes = conversoesRepository.findById(3L); // 3 = (unidade)
        estoque.setConversoes(conversoes);

        estoqueRepository.create(estoque);
    }
}
