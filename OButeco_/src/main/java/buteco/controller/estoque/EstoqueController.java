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

        switch (produto.getTipoProduto()){
            case PRODUTOCOMCOMPLEMENTO -> saidaComplemento(produto, qtdeSaida);
            case SERVICO -> System.out.println("NAO E POSSIVEL REALIZAR A SAIDA DO TIPO SERVICO");
            default -> saidaNormal(produto, estoque, qtdeSaida);
        }

    }

    public void saidaNormal(Produto produto, Estoque estoque, double qtdeSaida){
        estoque.setQtdeEstoque(estoque.getQtdeEstoque() - qtdeSaida);
        estoque.atualizaValorTotalEstoque();

        Saida saida = new Saida(produto, estoque, produto.getValorUnitario() * qtdeSaida, qtdeSaida);
        estoque.addSaida(saida);
    }

    public void saidaComplemento(Produto produto, double qtdeSaida){
        for (IngredientesProduto ip : produto.getIngredientesProdutos()){
            Estoque estoque = ip.getIngredienteProduto().getEstoque();

            if (ip.getIngredienteProduto().getTipoProduto() != ETipoProduto.SERVICO){
                saidaNormal(ip.getIngredienteProduto(), estoque, qtdeSaida * ip.getQtde());
            }
        }
    }

    public void converteSaida(Produto produto){

    }

    public void exibirEstoques(List<Estoque> estoques){
        for(Estoque e : estoques){
            System.out.println("Produto | Qtde Estoque | ");
            System.out.println(e.getProduto().getNome() + " | " + e.getQtdeEstoque());
        }
    }
}
