package buteco.view;

import buteco.model.produto.Produto;

import java.util.List;
import java.util.Scanner;

public class ProdutosView {
    private Scanner sc;

    public ProdutosView(Scanner sc){
        this.sc = sc;
    }

    public int exibirMenu(){
        System.out.println("[1] - CADASTRAR PRODUTO; [2] - LISTAR PRODUTOS; [3] - CADASTRAR SAIDA; [0] - SAIR");
        return sc.nextInt();
    }

    public void exibirProdutos(List<Produto> produto){
        System.out.println("Produtos Cadastrados");

        for (Produto p : produto){
            System.out.println("Nome: " + p.getNome());
        }
    }

    public void exibirMensagem(String mensagem){
        System.out.println(mensagem);
    }
}
