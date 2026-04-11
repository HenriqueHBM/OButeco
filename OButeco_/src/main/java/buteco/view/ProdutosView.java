package buteco.view;

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

    public void exibirProdutos(){
        System.out.println("Produtos Cadastrados");

        exibirMensagem("===============PRODUTOS CADASTRADOS===============");
        System.out.printf("%-6s | %-25s | %-25s | %-15s | %-15s | %-15s | %-25s\n",
                "CODIGO",  "NOME", "TIPO PRODUTO", "VALOR UNIDADE", "GRUPO", "STATUS", "OBS");


        try {
            var service = new ProdutoService(produtoRepository);
            var prod = service.findAllProdutos();

            prod.stream().forEach(element -> {
                System.out.printf("%-6s | %-25s | %-25s | %-15s | %-15s | %-15s | %-25s\n",
                        element.getId(),
                        element.getNome(),
                        element.getCategoria().getCategoria(),
                        element.getPrecoVenda(),
                        element.getGrupo().getGrupo(),
                        element.getStatus(),
                        element.getObservacao()
                );
                if(!element.getInsumos().isEmpty()){
                    this.exibirIngredienteProduto(element.getInsumos());
                }
//                element.getInsumos().stream().forEach(val -> {
//                    System.out.println(val.getQtde());
//                });
            });
            System.out.println();
        }catch (Exception er){
            System.out.println(er.getMessage());
        }
    }

    public void exibirIngredientes(){
        exibirMensagem("===============INSUMOS CADASTRADOS===============");
        System.out.printf("%-6s | %-25s | %-25s | %-15s | %-25s\n",
                "CODIGO", "NOME", "TIPO PRODUTO",  "GRUPO", "OBS");


        try {
            var service = new ProdutoService(produtoRepository);
            var prod = service.findAllProdutos();

            prod.stream().forEach(element -> {
                String cat = element.getCategoria().getCategoria();
                if(cat.equals("INSUMO") || cat.equals("SERVICO")){
                    System.out.printf("%-6s | %-25s | %-25s | %-15s | %-25s\n",
                            element.getId(),
                            element.getNome(),
                            element.getCategoria().getCategoria(),
                            element.getGrupo().getGrupo(),
                            element.getObservacao()
                    );
                }
            });
            System.out.println();
        }catch (Exception er){
            System.out.println(er.getMessage());
        }
    }
//
//    public void exibirMensagem(String mensagem){
//        System.out.println(mensagem);
//    }
//
    public void exibirIngredienteProduto(List<InsumosProduto> insumo){
        exibirMensagem("\t===================COMPLEMENTO PRODUTO===================");
            System.out.printf("%6s %-6s | %-25s | %-25s | %-15s | %-25s \n",
                    "||-", "CODIGO",  "NOME", "TIPO PRODUTO",  "GRUPO", "QTDE. USADA");

        insumo.stream().forEach(element -> {
            System.out.printf("%6s %-6s | %-25s | %-25s | %-15s | %-25s\n",
                    "||-",
                    element.getInsumo().getId(),
                    element.getInsumo().getNome(),
                    element.getInsumo().getCategoria().getCategoria(),
                    element.getInsumo().getGrupo().getGrupo(),
                    element.getQtde()
            );
        });
        exibirMensagem("\t=========================================================");
    }
}
