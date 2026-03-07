package buteco;

import buteco.controller.estoque.EstoqueController;
import buteco.controller.produtos.ProdutosController;
import buteco.controller.usuarios.UsuariosController;
import buteco.enums.ETipoProduto;
import buteco.model.estoque.Estoque;
import buteco.model.movimentacoes.Saida;
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
        List<Estoque> estoques = new ArrayList<>();
        List<Saida> saidas = new ArrayList<>();

        //funcao apenas para nao comecar vazio os dados
        cadastraProdutoInicial(produtos, estoques);
//      Declarando os controllers
        ProdutosController produtosController = new ProdutosController(sc, produtos, estoques);
        EstoqueController estoqueController = new EstoqueController(sc, produtos, estoques, saidas);
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

    static void cadastraProdutoInicial(List<Produto> produtos, List<Estoque> estoques){
        Produto prod = new Produto("Calabresa", 1, 14, ETipoProduto.INGREDIENTE);
        Estoque est = new Estoque(1, prod, 20);
        prod.setEstoque(est);

        produtos.add(prod);
        estoques.add(est);

        prod = new Produto("Hora Funcionario", 2, 15, ETipoProduto.SERVICO_Hr);

        produtos.add(prod);

    }
}