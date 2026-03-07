package buteco.controller.estoque;

import buteco.enums.ETipoProduto;
import buteco.model.estoque.Estoque;
import buteco.model.movimentacoes.Saida;
import buteco.model.produto.IngredientesProduto;
import buteco.model.produto.Produto;
import buteco.view.EstoqueView;
import buteco.view.ProdutosView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EstoqueController {
    private EstoqueView view;
    private ProdutosView viewProduto;
    static List<Estoque> estoques;
    static List<Produto> produtos;
    List<Saida> saidas;
    private Scanner sc;

    public EstoqueController(Scanner sc, List<Produto> produtos, List<Estoque> estoques, List<Saida> saidas) {
        this.sc = sc;
        this.produtos = produtos;
        this.estoques = estoques;
        this.saidas = saidas;
        this.view = new EstoqueView(sc);
        this.viewProduto = new ProdutosView(sc);
    }


    public void index(){

        int opcao = 0;

        do{
            opcao = view.exibirMenu();
            switch (opcao){
                case 1 -> cadastrarEntrada();
                case 2 -> cadastrarSaida();
                case 3 -> exibirEstoques(this.estoques);
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
        viewProduto.exibirMensagem("Selecione um produto(pelo codigo) para realizar a Saida");
        viewProduto.exibirProdutos(this.produtos);
        int opcao = sc.nextInt();
        //- 1 pois a lista comeca em "0"
        Produto produto = this.produtos.get(opcao - 1);

        viewProduto.exibirMensagem("Insira a quantidade de saida");
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

    public double saidaNormal(Produto produto, Estoque estoque, double qtdeSaida){
        estoque.setQtdeEstoque(estoque.getQtdeEstoque() - qtdeSaida);
        estoque.atualizaValorTotalEstoque();
        double custoProducao = produto.getValorUnitario() * qtdeSaida;
        Saida saida = new Saida(produto, estoque, custoProducao, qtdeSaida);
        estoque.addSaida(saida);

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
                custoProducao += ip.getIngredienteProduto().getValorUnitario() * qtdeSaida;
            }
        }
        return  custoProducao;
    }

    public void converteSaida(Produto produto){

    }

    public void exibirEstoques(List<Estoque> estoques){
        for(Estoque e : estoques){
            System.out.println("Produto | Qtde Estoque | ");
            System.out.println(e.getProduto().getNome() + " | " + e.getQtdeEstoque());
        }
    }

    public double calcularMargem(double custo, double precoVenda){
        return ((precoVenda - custo) / precoVenda) * 100;
    }
}
