package buteco.controller.produtos;

import buteco.enums.ETipoProduto;
import buteco.model.produto.Produto;
import buteco.view.ProdutosView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProdutosController {
    private ProdutosView view;
    static List<Produto> produtos;
    private Scanner sc;


    // constructor da classe,
    public ProdutosController(Scanner sc, List<Produto> produtos){
        this.view = new ProdutosView(sc);
        this.produtos = produtos;
        this.sc = sc;
    }
    public void index(){

        int opcao = 0;

        do{
            opcao = view.exibirMenu();
            switch (opcao){
                case 1 -> cadastrarProduto();
                case 2 -> view.exibirProdutos(this.produtos);
                case 0 -> view.exibirMensagem("VOLTANDO..");
                default -> view.exibirMensagem("VALOR INVALIDO!!!");
            }

        }while(opcao != 0 );
    }

    public void cadastrarProduto(){
        view.exibirMensagem("Insira o nome do Produto:");
        String nome = sc.next();
        view.exibirMensagem("Insira o valor unitario:");
        double valUnit = sc.nextDouble();
        view.exibirMensagem("Quantidade de conversao:");
        double qtdeConversao = sc.nextDouble();
        view.exibirMensagem("Tipo de produto: [1] - NORMAL; [2] - INGREDIENTE; [3] - SERVICO(NAO DESCONTA DO ESTOQUE);");
        int opcao = sc.nextInt();
        int codigo = produtos.size() + 1;

        Produto produto = new Produto(nome, codigo, valUnit, qtdeConversao, ETipoProduto.NORMAL);
        System.out.println("Produto Cadastrado!!");
        this.produtos.add(produto);
    }
}
