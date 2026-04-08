package buteco.view;

import java.util.Scanner;

public class EstoqueView {
    private Scanner sc;
//
    public EstoqueView(Scanner sc) {
        this.sc = sc;
    }

    public int exibirMenu(){
        System.out.println("[1] CADASTRAR ENTRADA; [2] - CADASTRAR SAÍDA;  [3] - LISTAR ESTOQUE; [4] - MOVIMENTACOES; [0] - SAIR");
        return sc.nextInt();
    }

    public void exibirEstoque(){

//        System.out.println("============== ESTOQUE ==============");
//
//        System.out.printf("%-6s | %-25s | %-15s | %-15s | %-15s | %-20s\n",
//                "COD", "PRODUTO", "QTDE", "VALOR TOTAL", "CONVERSAO", "DATA DE CRIACAO");
//
//
//
//    }

        //
//    public void exibirMargemLucro(Produto produto, double margem){
//        System.out.println(produto.getNome());
//        System.out.printf("Preco venda: %.2f \n", produto.getValorUnitario());
//        System.out.printf("Margem: %.2f \n", margem);
//    }
//
}
