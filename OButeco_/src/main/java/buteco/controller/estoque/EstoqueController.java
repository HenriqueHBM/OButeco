package buteco.controller.estoque;

import buteco.model.estoque.Estoque;
import buteco.model.produto.Produto;
import buteco.view.EstoqueView;
import buteco.view.ProdutosView;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class EstoqueController {
    private EstoqueView view;
    private ProdutosView viewProduto;
    static List<Estoque> estoques;
    static List<Produto> produtos;
    private Scanner sc;

    public EstoqueController(Scanner sc, List<Produto> produtos, List<Estoque> estoques) {
        this.sc = sc;
        this.produtos = produtos;
        this.estoques = estoques;
    }


    public void index(){

        int opcao = 0;

        do{
            opcao = view.exibirMenu();
            switch (opcao){
                case 1 -> cadastrarEntrada();
            }

        }while(opcao != 0 );
//        sc.close();

    }

    public void cadastrarEntrada(){

    }

    public void cadastrarSaida(){
        viewProduto.exibirMensagem("Selecione um produto(pelo codigo) para realizar a Saida");
        viewProduto.exibirProdutos(this.produtos);
        int opcao = sc.nextInt();
        //- 1 pois a lista comeca em "0"
        Produto produto = this.produtos.get(opcao - 1);

        switch (produto.getTipoProduto()){
            case PRODUTOCOMCOMPLEMENTO -> System.out.println("");
            case SERVICO -> System.out.println("NAO E POSSIVEL REALIZAR A SAIDA DO TIPO SERVICO");
            default -> saidaNormal(produto);
        }

    }

    public void saidaNormal(Produto produto){

    }

    public void saidaComplemento(Produto produto){

    }
}
