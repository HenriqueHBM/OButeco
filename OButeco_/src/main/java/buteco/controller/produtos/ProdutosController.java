package buteco.controller.produtos;

import java.util.Scanner;

public class ProdutosController {
    Scanner sc = new Scanner(System.in);

    public void index(){

        int opcao = 0;

        do{
            System.out.println("[1] - CADASTRAR PRODUTO; [2] - LISTAR PRODUTOS;  [0] - SAIR");
            opcao = sc.nextInt();

        }while(opcao != 0 );
//        sc.close();
    }

    public void cadastrarProduto(){

    }
}
