package buteco.view;

import buteco.repositories.ConversoesRepository;
import buteco.repositories.EstoqueRepository;
import buteco.repositories.ProdutoRepository;
import buteco.service.EstoqueService;
import buteco.service.ProdutoService;
import buteco.service.entradas.ErroEntrada;

import java.util.Scanner;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class EstoqueView {
    private Scanner sc;
    private EstoqueRepository estoqueRepository;
    private ProdutoRepository produtoRepository;
    private ConversoesRepository conversoesRepository;
    private ErroEntrada erroEntrada;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(ZoneId.of("UTC"));

    public EstoqueView(Scanner sc, EstoqueRepository estoqueRepository, ProdutoRepository
            produtoRepository, ConversoesRepository conversoesRepository, ErroEntrada erroEntrada) {
        this.sc = sc;
        this.estoqueRepository = estoqueRepository;
        this.produtoRepository = produtoRepository;
        this.conversoesRepository = conversoesRepository;
        this.erroEntrada = erroEntrada;
    }

    public EstoqueView(Scanner sc) {
        this.sc = sc;
    }

    public int exibirMenu(){
        System.out.println("[1] CADASTRAR ENTRADA; [2] - CADASTRAR SAÍDA;  [3] - LISTAR ESTOQUE; [4] - MOVIMENTACOES; [0] - SAIR");
        return Integer.parseInt(sc.nextLine());
    }

    public static void exibirMensagem(String mensagem) {System.out.println(mensagem);}

    public void exibirEstoques(){

        exibirMensagem("============== ESTOQUE ==============");
        System.out.printf("%-6s | %-25s | %-15s | %-15s | %-20s\n",
                "COD", "PRODUTO", "QTDE", "CONVERSAO", "DATA DE CRIACAO");

        try {
            var service = new EstoqueService(estoqueRepository, produtoRepository, conversoesRepository, erroEntrada);
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

    public void exibirEstoqueProduto(){
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
            });
            System.out.println();
        }catch (Exception er){
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
