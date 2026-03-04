package buteco;

import buteco.controller.estoque.EstoqueController;
import buteco.controller.produtos.ProdutosController;
import buteco.service.entradas.ErroEntrada;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("--O BUTECO--");

        int entradaMenu = 0;
        ProdutosController produtosController = new ProdutosController();
        EstoqueController estoqueController = new EstoqueController();
        ErroEntrada errorEntrada = new ErroEntrada();

        do{
//            Funcao para tentar tratar caso usuario passe um caracter
            entradaMenu = errorEntrada.trataEntradaInt("[1] - PRODUTOS; [2] - ESTOQUE;  [0] - SAIR");
            switch (entradaMenu){
                case 1:
                    produtosController.index(); //funcao principal do controller
                    break;
                case 2:
                    estoqueController.index(); //funcao principal do controller
                    break;
                case 0:
                    System.out.println("ATE MAIS!!!");
                    break;
                default:
                    System.out.println("VALOR INVALIDO!!!");
                    break;
            }
        }while(entradaMenu != 0 );
    }
}