package buteco.view;

import buteco.enums.EStatus;
import buteco.model.produto.InsumosProduto;
import buteco.repositories.ProdutoRepository;
import buteco.service.ProdutoService;
import buteco.service.entradas.ErroEntrada;

import java.util.List;
import java.util.Scanner;

public class ProdutosView {
    private Scanner sc;
    private ErroEntrada errorEntrada;
    private ProdutoRepository produtoRepository;

    public ProdutosView(Scanner sc, ErroEntrada errorEntrada, ProdutoRepository produtoRepository){
        this.sc = sc;
        this.errorEntrada = errorEntrada;
        this.produtoRepository = produtoRepository;
    }

    public int exibirMenu(){
        return errorEntrada.trataEntradaInt("[1] - CADASTRAR PRODUTO; [2] - LISTAR PRODUTOS; [3] - EDITAR PRODUTO; [4] - EXCLUIR PRODUTO; [0] - SAIR");

    }

    public static void exibirMensagem(String mensagem){
        System.out.println(mensagem);
    }

    private void linha(int tamanho){
        System.out.println("-".repeat(tamanho));
    }

    public void exibirProdutos(){
        linha(130);
        System.out.println("PRODUTOS CADASTRADOS");
        linha(130);

        System.out.printf("%-6s | %-25s | %-20s | %-12s | %-15s | %-10s | %-25s\n",
                "COD", "NOME", "TIPO", "VALOR UNITARIO", "GRUPO", "STATUS", "OBS");

        linha(130);

        var prod = new ProdutoService(produtoRepository).findAllProdutos();

        for (var element : prod) {
            System.out.printf("%-6d | %-25s | %-20s | %-12.2f | %-15s | %-10s | %-25s\n",
                    element.getId(),
                    element.getNome(),
                    element.getCategoria().getCategoria(),
                    element.getPrecoVenda(),
                    element.getGrupo().getGrupo(),
                    element.getStatus(),
                    element.getObservacao() == null ? "" : element.getObservacao()
            );


            if (!element.getInsumos().isEmpty()) {
                exibirIngredienteProduto(element.getInsumos());
            }

            linha(130);
        }
    }

    public void exibirIngredientes(){
        linha(130);
        System.out.println("INSUMOS CADASTRADOS");
        linha(130);

        System.out.printf("%-6s | %-25s | %-25s | %-15s | %-25s\n",
                "COD", "NOME", "TIPO PRODUTO",  "GRUPO", "OBS");
        linha(130);

        try {
            var service = new ProdutoService(produtoRepository);
            var prod = service.findAllProdutos();

            prod.stream().forEach(element -> {
                String cat = element.getCategoria().getCategoria();
                if((cat.equals("INSUMO") || cat.equals("SERVICO")) && element.getStatus().equals(EStatus.ATIVO)){
                    System.out.printf("%-6s | %-25s | %-25s | %-15s | %-25s\n",
                            element.getId(),
                            element.getNome(),
                            element.getCategoria().getCategoria(),
                            element.getGrupo().getGrupo(),
                            element.getObservacao() == null ? "" : element.getObservacao()
                    );
                    linha(130);
                }
            });
            System.out.println();
        }catch (Exception er){
            System.out.println(er.getMessage());
        }
    }

    public void exibirIngredienteProduto(List<InsumosProduto> insumos){

        System.out.println("   |-> INSUMOS:");

        System.out.printf("   %-6s | %-20s | %-15s | %-15s | %-10s\n",
                "COD", "NOME", "TIPO", "GRUPO", "QTDE");

        for (var element : insumos) {
            System.out.printf("   %-6d | %-20s | %-15s | %-15s | %-10.2f\n",
                    element.getInsumo().getId(),
                    element.getInsumo().getNome(),
                    element.getInsumo().getCategoria().getCategoria(),
                    element.getInsumo().getGrupo().getGrupo(),
                    element.getQtde()
            );
        }
    }
}
