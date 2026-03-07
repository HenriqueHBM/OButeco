package buteco.view;

import buteco.model.produto.Produto;

import java.util.Scanner;

public class EstoqueView {
    private Scanner sc;

    public EstoqueView(Scanner sc) {
        this.sc = sc;
    }

    public int exibirMenu(){
        System.out.println("[1] CADASTRAR ENTRADA; [2] - LISTAR ESTOQUE; [3] - MOVIMENTACOES ENTRADAS; [4] - MOVIMENTACOES SAIDAS;  [0] - SAIR");
        return sc.nextInt();
    }

    public void exibirMargemLucro(Produto produto, double margem){
        System.out.println(produto.getNome());
        System.out.println("Preco compra: ");
        System.out.printf("Preco venda: %.2f", produto.getValorUnitario());
        System.out.printf("Margem: %.2f", margem);
    }

}
