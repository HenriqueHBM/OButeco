package buteco.view;

import buteco.enums.ETipoProduto;
import buteco.model.produto.IngredientesProduto;
import buteco.model.produto.Produto;

import java.util.List;
import java.util.Scanner;

public class ProdutosView {
    private Scanner sc;

    public ProdutosView(Scanner sc){
        this.sc = sc;
    }

    public int exibirMenu(){
        System.out.println("[1] - CADASTRAR PRODUTO; [2] - LISTAR PRODUTOS; [0] - SAIR");
        return sc.nextInt();
    }

    public void exibirProdutos(List<Produto> produto){
        System.out.println("Produtos Cadastrados");

        exibirMensagem("===============PRODUTOS CADASTRADOS===============");
        System.out.printf("%-6s | %-15s | %-15s | %-15s | %-15s\n",
                "CODIGO",  "NOME", "TIPO PRODUTO", "VALOR UNITARIO","QTDE CONVERSAO");
        for (Produto p : produto){
            System.out.printf("%-6d | %-15s | %-15s | %-15.2f | %-15.2f\n",
                    p.getCodigo(), p.getNome(), p.getTipoProduto().toString(), p.getValorUnitario(), p.getQtdeConversao());

            if(p.getIngredientesProdutos().size() > 0){
                exibirMensagem("\t===================COMPLEMENTO PRODUTO===================");
                for (IngredientesProduto ip : p.getIngredientesProdutos()){
                    System.out.printf("\t |- %-6d | %-15s | %-15s | %-15.2f | %-15.2f\n",
                            ip.getIngredienteProduto().getCodigo(), ip.getIngredienteProduto().getNome(), ip.getIngredienteProduto().getTipoProduto().toString(), ip.getIngredienteProduto().getValorUnitario(), ip.getIngredienteProduto().getQtdeConversao());
                }
            }
        }
    }

    public void exibirIngredientes(List<Produto> produto){
        System.out.println("Produtos Cadastrados");

        for (Produto p : produto){
            if(p.getTipoProduto() == ETipoProduto.INGREDIENTE){
                System.out.println("Nome: " + p.getNome()+ " aa "+p.getTipoProduto());
            }
        }
    }

    public void exibirMensagem(String mensagem){
        System.out.println(mensagem);
    }
}
