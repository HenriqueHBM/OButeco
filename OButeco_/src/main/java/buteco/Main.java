package buteco;

import buteco.controller.estoque.EstoqueController;
import buteco.controller.produtos.ProdutosController;
import buteco.controller.usuarios.UsuariosController;
import buteco.model.produto.Produto;
import buteco.service.entradas.ErroEntrada;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in); //passar isso para as classesControllers para nao ficar instanciando o tempo todo
        System.out.println("--O BUTECO--");

        int entradaMenu = 0;
        List<Produto> produtos = new ArrayList<>();

//      Declarando os controllers
        ProdutosController produtosController = new ProdutosController(sc, produtos);
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