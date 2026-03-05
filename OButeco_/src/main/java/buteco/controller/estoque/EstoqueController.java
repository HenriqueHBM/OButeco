package buteco.controller.estoque;

import buteco.model.produto.Produto;

import java.util.List;
import java.util.Scanner;

public class EstoqueController {
    Scanner sc = new Scanner(System.in);
    public EstoqueController(List<Produto> produtos){

    }
    public void index(){

        int opcao = 0;

        do{
            System.out.println("[1] CADASTRAR ENTRADA; [2] - LISTAR ESTOQUE; [3] - MOVIMENTACOES ENTRADAS; [4] - MOVIMENTACOES SAIDAS;  [0] - SAIR");
            opcao = sc.nextInt();

        }while(opcao != 0 );
//        sc.close();
    }
}
