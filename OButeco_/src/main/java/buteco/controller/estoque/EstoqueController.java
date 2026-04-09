package buteco.controller.estoque;

import buteco.repositories.EstoqueRepository;
import buteco.repositories.ProdutoRepository;
import buteco.service.EstoqueService;
import buteco.service.entradas.ErroEntrada;
import buteco.view.EstoqueView;
import buteco.view.ProdutosView;

import java.util.Scanner;

public class EstoqueController {
    private EstoqueView estoqueView;
    private Scanner sc;
    private ErroEntrada errorEntrada;
    private ProdutosView produtosView;
    private EstoqueService estoqueService;

    //    static List<Estoque> estoques;
//    static List<Produto> produtos;
//    List<Saida> saidas;
//    VerificaEntradaProduto verificaEntradaProduto;
//
    public EstoqueController(Scanner sc, ErroEntrada errorEntrada, EstoqueRepository estoqueRepository, ProdutoRepository produtoRepository, EstoqueService estoqueService){
        this.sc = sc;
        this.errorEntrada = errorEntrada;
        this.estoqueView = new EstoqueView(sc, estoqueRepository, produtoRepository);
        this.produtosView = new ProdutosView(sc, errorEntrada, produtoRepository);
        this.estoqueService = new EstoqueService(estoqueRepository);
    }
//    public EstoqueController(Scanner sc, ErroEntrada errorEntrada, List<Produto> produtos, List<Estoque> estoques, List<Saida> saidas) {
//        this.sc = sc;
//        this.produtos = produtos;
//        this.estoques = estoques;
//        this.saidas = saidas;
//        this.view = new EstoqueView(sc);
//        this.errorEntrada = errorEntrada;
//        this.viewProd = new ProdutosView(sc, errorEntrada);
//        this.verificaEntradaProduto = new VerificaEntradaProduto(errorEntrada, this.produtos);
//    }
//
//
    public void index(){
        int opcao = 0;
            do{
                opcao = estoqueView.exibirMenu();
                switch (opcao){
                    case 1 -> cadastrarMovimentacao(1);
                    case 2 -> cadastrarMovimentacao(2);
                    case 3 -> estoqueView.exibirEstoques();
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("VALOR INVALIDO");
                }

            }while(opcao != 0 );
    }

    public void cadastrarMovimentacao(int tipo) {
        produtosView.exibirProdutos();
        int tentativas = 0;

        do {
            try {

                Long idProduto = errorEntrada.trataEntradaLong("Insira o codigo do Produto: ");
                double qtde = errorEntrada.trataEntradaDouble("Insira a quantidade: ");

                switch (tipo) {
                    case 1 -> estoqueService.cadastrarEntrada(idProduto, qtde);
                    case 2 -> estoqueService.cadastrarSaida(idProduto, qtde);
                }
                System.out.println("Cadastro realizado com sucesso!");
                tentativas = 0;
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
                tentativas++;
            }
        }while(tentativas > 0);
    }
//
//    //produtos que nao tem complemento/ingrediente na montagem
//    public double saidaNormal(Produto produto, Estoque estoque, double qtdeSaida){
//        //ajustando o estoque
//        estoque.setQtdeEstoque(estoque.getQtdeEstoque() - qtdeSaida);
//        //atualizando o valor no estoque
//        estoque.atualizaValorTotalEstoque();
//        //salvando o custo de producao
//        double custoProducao = produto.getValorUnitario() * qtdeSaida;
//        //criando a movimentacao de saida e add no estoque
//        Saida saida = new Saida(produto, estoque, custoProducao, qtdeSaida);
//        estoque.addSaida(saida);
//
//        //salvando na variavel global a saida
//        this.saidas.add(saida);
//        return custoProducao;
//    }
//
//    public double saidaComplemento(Produto produto, double qtdeSaida){
//        double custoProducao = 0;
//        //passando pelos ingredientes do produto
//        for (IngredientesProduto ip : produto.getIngredientesProdutos()){
//            Estoque estoque = ip.getIngredienteProduto().getEstoque();
//
//            //caso o complemento nao seja do tipo servivo, realiza as saidas
//            if (ip.getIngredienteProduto().getTipoProduto() != ETipoProduto.SERVICO_Hr){
//                custoProducao += saidaNormal(ip.getIngredienteProduto(), estoque, qtdeSaida * ip.getQtde());
//            }else{
//                //caso for um servico apenas pegar o custo de mao de obra
//                custoProducao += ip.getIngredienteProduto().getValorUnitario() * qtdeSaida;
//            }
//        }
//        return  custoProducao;
//    }
//
//    public double calcularMargem(double custo, double precoVenda){
//        return ((precoVenda - custo) / precoVenda) * 100;
//    }

}
