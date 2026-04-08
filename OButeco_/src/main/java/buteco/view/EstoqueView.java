package buteco.view;

import buteco.repositories.EstoqueRepository;
import buteco.service.EstoqueService;
import java.util.Scanner;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class EstoqueView {
    private Scanner sc;
    private EstoqueRepository estoqueRepository;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(ZoneId.systemDefault());

    public EstoqueView(Scanner sc, EstoqueRepository estoqueRepository) {
        this.sc = sc;
        this.estoqueRepository = estoqueRepository;
    }

    public EstoqueView(Scanner sc) {
        this.sc = sc;
    }

    public int exibirMenu(){
        System.out.println("[1] CADASTRAR ENTRADA; [2] - CADASTRAR SAÍDA;  [3] - LISTAR ESTOQUE; [4] - MOVIMENTACOES; [0] - SAIR");
        return sc.nextInt();
    }

    public static void exibirMensagem(String mensagem) {System.out.println(mensagem);}

    public void exibirEstoques(){

        exibirMensagem("============== ESTOQUE ==============");
        System.out.printf("%-6s | %-25s | %-15s | %-15s | %-20s\n",
                "COD", "PRODUTO", "QTDE", "CONVERSAO", "DATA DE CRIACAO");

        try {
            var service = new EstoqueService(estoqueRepository);
            var estoques = service.findAllEstoques();

            estoques.stream().forEach(element -> {
                System.out.printf("%-6s | %-25s | %-15s | %-15s | %-20s\n",
                        element.getId(),
                        element.getProduto().getNome(),
                        element.getQntdEstoque(),
                        element.getConversoes().getNome(),
                        formatter.format(element.getDataCriacao())
                );
            });
            System.out.println();
        } catch (Exception er) {
            System.out.println(er.getMessage());
        }
    }

        //
//    public void exibirMargemLucro(Produto produto, double margem){
//        System.out.println(produto.getNome());
//        System.out.printf("Preco venda: %.2f \n", produto.getValorUnitario());
//        System.out.printf("Margem: %.2f \n", margem);
//    }
//
}
