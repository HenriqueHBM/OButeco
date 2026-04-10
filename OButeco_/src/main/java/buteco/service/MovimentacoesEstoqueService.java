package buteco.service;

import buteco.model.conversao.Conversoes;
import buteco.model.estoque.Estoque;
import buteco.model.estoque.MovimentacoesEstoque;
import buteco.repositories.ConversoesRepository;
import buteco.repositories.EstoqueRepository;
import buteco.repositories.MovimentacoesEstoqueRepository;
import buteco.service.entradas.ErroEntrada;

public class MovimentacoesEstoqueService {
    private final MovimentacoesEstoqueRepository movimentacoesEstoqueRepository;
    private final EstoqueRepository estoqueRepository;
    private final EstoqueService estoqueService;
    private final ConversoesRepository conversoesRepository;
    private ErroEntrada erroEntrada;

    public MovimentacoesEstoqueService(MovimentacoesEstoqueRepository movimentacoesEstoqueRepository, EstoqueRepository estoqueRepository,
                                       EstoqueService estoqueService, ConversoesRepository conversoesRepository, ErroEntrada erroEntrada) {
        this.movimentacoesEstoqueRepository = movimentacoesEstoqueRepository;
        this.estoqueRepository = estoqueRepository;
        this.estoqueService = estoqueService;
        this.conversoesRepository = conversoesRepository;
        this.erroEntrada = erroEntrada;
    }

    public void confereEstoque(Long idProduto){
        Estoque estoque = estoqueRepository.findByProdutoId(idProduto);
        if (estoque == null) {
            System.out.println("Estoque inexistente para esse produto, cadastre um novo:");
            estoqueService.criarNovoEstoque(idProduto); //caso o estoque para o produto nao exista ele deve criar um novo
        }
    }


    public void cadastrarEntrada(Long idProduto, double qtde){
        Estoque estoque = estoqueRepository.findByProdutoId(idProduto);
        if (estoque == null){
            throw new RuntimeException("Estoque nao encontrado para esse COD.");
        }

        System.out.println("Unidade no estoque " + estoque.getConversoes().getNome()); //mostra para o usuario qual a unidade cadastrada no estoque
        var medidas = conversoesRepository.findAllConversoes();
        medidas.stream().forEach(System.out::println); //lista unidades de conversao
        Long idConversoes = erroEntrada.trataEntradaLong("Insira o codigo de unidade que voce esta usando: ");

        Conversoes conversaoEntrada = conversoesRepository.findById(idConversoes);
        if(conversaoEntrada == null){
            throw new RuntimeException("Unidade de conversao nao encontrada");
        }

        double qtdeNova;
        if(idConversoes.equals(estoque.getConversoes().getId())){ //.equals necessario para comparar os valores, == nao funciona para Long por ser objeto
            qtdeNova = qtde; //caso medidas sejam iguais nao faz conversao
        } else {
            System.out.println("Unidade diferente do estoque!");
            double conversaoEquivalente = erroEntrada.trataEntradaDouble //pede referencia da conversao de medida para o usuario
                            ("Quanto vale 1 " + conversoesRepository.findById(idConversoes).getNome() //ex: 1un de queijo valem 500g
                                    + " em " + estoque.getConversoes().getNome() + "?\n");
            qtdeNova = qtde * conversaoEquivalente;
            System.out.printf("Convertido: %.2f %s\n", qtdeNova, estoque.getConversoes().getNomenclatura());
        }

        estoque.setQntdEstoque(estoque.getQntdEstoque() + qtdeNova);
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