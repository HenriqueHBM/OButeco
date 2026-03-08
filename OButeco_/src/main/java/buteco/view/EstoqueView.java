package buteco.view;

import buteco.controller.estoque.EstoqueController;
import buteco.model.estoque.Estoque;
import buteco.model.produto.Produto;
import buteco.model.movimentacoes.Entrada;
import buteco.model.movimentacoes.Saida;

import java.util.List;
import java.util.Scanner;

public class EstoqueView {
    private Scanner sc;

    public EstoqueView(Scanner sc) {
        this.sc = sc;
    }

    public int exibirMenu(){
        System.out.println("[1] CADASTRAR ENTRADA; [2] - CADASTRAR SAÍDA;  [3] - LISTAR ESTOQUE; [4] - MOVIMENTACOES ENTRADAS; [5] - MOVIMENTACOES SAIDAS;  [0] - SAIR");
        return sc.nextInt();
    }

    public void exibirMargemLucro(Produto produto, double margem){
        System.out.println(produto.getNome());
        System.out.println("Preco compra: ");
        System.out.printf("Preco venda: %.2f", produto.getValorUnitario());
        System.out.printf("Margem: %.2f", margem);
    }

    public void exibirEstoque(List<Estoque> estoques){

        System.out.println("============== ESTOQUE ==============");

        System.out.printf("%-6s | %-25s | %-15s | %-15s\n",
                "COD", "PRODUTO", "QTDE", "VALOR TOTAL");

        for (Estoque e : estoques){

            System.out.printf("%-6d | %-25s | %-15.2f | %-15.2f\n",
                    e.getCodEstoque(),
                    e.getProduto().getNome(),
                    e.getQtdeEstoque(),
                    e.getValorTotal()
            );
        }
    }


}
