package buteco.controller.estoque;

import java.util.Scanner;

public class EstoqueController {
    Scanner sc = new Scanner(System.in);

    public void index(){

        int opcao = 0;

        do{
            System.out.println("[1] - LISTAR ESTOQUE; [2] - LISTAR ENTRADAS; [3] - LISTAR SAIDAS;  [0] - SAIR");
            opcao = sc.nextInt();

        }while(opcao != 0 );
//        sc.close();
    }
}
