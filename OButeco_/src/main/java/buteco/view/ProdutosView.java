package buteco.view;

import buteco.enums.ETipoProduto;
import buteco.model.produto.IngredientesProduto;
import buteco.model.produto.Produto;
import buteco.service.entradas.ErroEntrada;

import java.util.List;
import java.util.Scanner;

public class ProdutosView {
    private Scanner sc;
    private ErroEntrada errorEntrada;

    public ProdutosView(Scanner sc, ErroEntrada errorEntrada){
        this.sc = sc;
        this.errorEntrada = errorEntrada;
    }

    public int exibirMenu(){
        return errorEntrada.trataEntradaInt("[1] - CADASTRAR PRODUTO; [2] - LISTAR PRODUTOS; [0] - SAIR");
    }

    public void exibirProdutos(List<Produto> produto){
        System.out.println("Produtos Cadastrados");

        exibirMensagem("===============PRODUTOS CADASTRADOS===============");
        System.out.printf("%-6s | %-25s | %-25s | %-15s | %-25s\n",
                "CODIGO",  "NOME", "TIPO PRODUTO", "VALOR UNIDADE", "OBS");
        for (Produto p : produto){
            System.out.printf("%-6d | %-25s | %-25s | %-15.2f \n",
                    p.getCodigo(),
                    p.getNome(),
                    p.getTipoProduto().toString(),
                    p.getValorUnitario()
            );

            if(p.getIngredientesProdutos().size() > 0){
                exibirMensagem("\t===================COMPLEMENTO PRODUTO===================");
                System.out.printf("%6s %-6s | %-25s | %-25s | %-15s | %-25s \n",
                        "|-", "CODIGO",  "NOME", "TIPO PRODUTO", "VALOR UNIDADE", "QTDE. USADA");
                for (IngredientesProduto ip : p.getIngredientesProdutos()){
                    System.out.printf("%6s %-6d | %-25s | %-25s | %-15.2f | %-15.2f\n",
                            "|-",
                            ip.getIngredienteProduto().getCodigo(),
                            ip.getIngredienteProduto().getNome(),
                            ip.getIngredienteProduto().getTipoProduto().toString(),
                            ip.getIngredienteProduto().getValorUnitario(),
                            ip.getQtde()
                    );
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
