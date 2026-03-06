package buteco.view;

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

}
