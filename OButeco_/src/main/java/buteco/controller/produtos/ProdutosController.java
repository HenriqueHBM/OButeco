package buteco.controller.produtos;

import buteco.model.produto.Produto;
import buteco.view.ProdutosView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProdutosController {
    private ProdutosView view;
    static List<Produto> produtos;

    // constructor da classe,
    public ProdutosController(Scanner sc, List<Produto> produtos){
        this.view = new ProdutosView(sc);
        this.produtos = produtos;
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
        Produto produto = new Produto("Pera", 123456789, 20.50, 100);
        System.out.println("Produto Cadastrado!!");
        this.produtos.add(produto);
    }
}
