package buteco.controller.estoque;

import buteco.enums.ETipoProduto;
import buteco.model.estoque.Estoque;
import buteco.model.movimentacoes.Saida;
import buteco.model.produto.IngredientesProduto;
import buteco.model.produto.Produto;
import buteco.service.entradas.ErroEntrada;
import buteco.view.EstoqueView;
import buteco.view.ProdutosView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EstoqueController {
    private EstoqueView view;
    static List<Estoque> estoques;
    static List<Produto> produtos;
    List<Saida> saidas;
    private Scanner sc;
    private ErroEntrada errorEntrada;

    public EstoqueController(Scanner sc, ErroEntrada errorEntrada, List<Produto> produtos, List<Estoque> estoques, List<Saida> saidas) {
        this.sc = sc;
        this.produtos = produtos;
        this.estoques = estoques;
        this.saidas = saidas;
        this.view = new EstoqueView(sc);
        this.errorEntrada = errorEntrada;
    }


    public void index(){

        int opcao = 0;

        do{
            opcao = view.exibirMenu();
            switch (opcao){
                case 1 -> cadastrarEntrada();
                case 2 -> cadastrarSaida();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("asdfasdf");
            }

        }while(opcao != 0 );
//        sc.close();

    }

    public void cadastrarEntrada(){
        System.out.println("asdf");
    }

    public void cadastrarSaida(){
        System.out.println("Selecione um produto(pelo codigo) para realizar a Saida");
        System.out.println(this.produtos);
        int opcao = sc.nextInt();
        //- 1 pois a lista comeca em "0"
        Produto produto = this.produtos.get(opcao - 1);

        System.out.println("Insira a quantidade de saida");
        double qtdeSaida = sc.nextDouble();

        Estoque estoque = produto.getEstoque();
        double custoProducaoTotal = 0;
        switch (produto.getTipoProduto()){
            case PRODUTOCOMCOMPLEMENTO -> custoProducaoTotal += saidaComplemento(produto, qtdeSaida);
            case SERVICO_Hr -> System.out.println("NAO E POSSIVEL REALIZAR A SAIDA DO TIPO SERVICO");
            default -> custoProducaoTotal += saidaNormal(produto, estoque, qtdeSaida);
        }
        System.out.println(custoProducaoTotal);
        view.exibirMargemLucro(produto, calcularMargem(custoProducaoTotal, produto.getValorUnitario()));
        System.out.println("Saida Realizada!");
    }

    //produtos que nao tem complemento/ingrediente na montagem
    public double saidaNormal(Produto produto, Estoque estoque, double qtdeSaida){
        //ajustando o estoque
        estoque.setQtdeEstoque(estoque.getQtdeEstoque() - qtdeSaida);
        //atualizando o valor no estoque
        estoque.atualizaValorTotalEstoque();
        //salvando o custo de producao
        double custoProducao = produto.getValorUnitario() * qtdeSaida;
        //criando a movimentacao de saida e add no estoque
        Saida saida = new Saida(produto, estoque, custoProducao, qtdeSaida);
        estoque.addSaida(saida);

        //salvando na variavel global a saida
        this.saidas.add(saida);
        return custoProducao;
    }

    public double saidaComplemento(Produto produto, double qtdeSaida){
        double custoProducao = 0;
        //passando pelos ingredientes do produto
        for (IngredientesProduto ip : produto.getIngredientesProdutos()){
            Estoque estoque = ip.getIngredienteProduto().getEstoque();

            //caso o complemento nao seja do tipo servivo, realiza as saidas
            if (ip.getIngredienteProduto().getTipoProduto() != ETipoProduto.SERVICO_Hr){
                custoProducao += saidaNormal(ip.getIngredienteProduto(), estoque, qtdeSaida * ip.getQtde());
            }else{
                //caso for um servico apenas pegar o custo de mao de obra
                custoProducao += ip.getIngredienteProduto().getValorUnitario() * qtdeSaida;
            }
        }
        return  custoProducao;
    }

    public double calcularMargem(double custo, double precoVenda){
        return ((precoVenda - custo) / precoVenda) * 100;
    }
}
