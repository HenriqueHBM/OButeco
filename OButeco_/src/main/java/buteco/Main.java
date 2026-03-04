package buteco;

import buteco.controller.estoque.EstoqueController;
import buteco.controller.produtos.ProdutosController;
import buteco.controller.usuarios.UsuariosController;
import buteco.service.entradas.ErroEntrada;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("--O BUTECO--");

        int entradaMenu = 0;
        ProdutosController produtosController = new ProdutosController();
        EstoqueController estoqueController = new EstoqueController();
        UsuariosController usuarioController = new UsuariosController();
        ErroEntrada errorEntrada = new ErroEntrada();

        do{
            // Funcao para tentar tratar caso usuario passe um caracter
            entradaMenu = errorEntrada.trataEntradaInt("[1] - PRODUTOS; [2] - ESTOQUE; [3] - USUARIOS;  [0] - SAIR");
            switch (entradaMenu){
                case 1 -> produtosController.index();
                case 2 -> estoqueController.index();
                case 3 -> usuarioController.index();
                case 0 -> System.out.println("ATE MAIS!!!");
                default -> System.out.println("VALOR INVALIDO!!!");
            }
        }while(entradaMenu != 0 );
    }
}