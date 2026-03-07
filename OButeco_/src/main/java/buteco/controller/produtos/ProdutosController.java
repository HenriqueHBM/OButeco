package buteco.controller.produtos;

import buteco.enums.ETipoProduto;
import buteco.model.produto.IngredientesProduto;
import buteco.model.produto.Produto;
import buteco.view.ProdutosView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProdutosController {
    private ProdutosView view;
    static List<Produto> produtos;
    private Scanner sc;


    // constructor da classe,
    public ProdutosController(Scanner sc, List<Produto> produtos){
        this.view = new ProdutosView(sc);
        this.produtos = produtos;
        this.sc = sc;
    }
    public void index(){

        int opcao = 0;

        do{
            opcao = view.exibirMenu();
            switch (opcao){
                case 1 -> cadastrarProduto();
                case 2 -> view.exibirProdutos(this.produtos);
                case 0 -> view.exibirMensagem("VOLTANDO..");
                default -> view.exibirMensagem("VALOR INVALIDO!!!");
            }

        }while(opcao != 0 );
    }

    public void cadastrarProduto(){
        List<IngredientesProduto> listaIngredientesProdutos = new ArrayList<>();
        view.exibirMensagem("Insira o nome do Produto:");
        String nome = sc.next();
        view.exibirMensagem("Insira o valor unitario:");
        double valUnit = sc.nextDouble();
        view.exibirMensagem("Quantidade de conversao:");
        double qtdeConversao = sc.nextDouble();
        view.exibirMensagem("Tipo de produto: [1] - NORMAL; [2] - PRODUTO COM COMPLEMENTOS; [3] - INGREDIENTE; [4] - SERVICO(NAO DESCONTA DO ESTOQUE);");
        int opcao = sc.nextInt();

        ETipoProduto tipoProduto = escolheTipoProduto(opcao);

        view.exibirMensagem("Observacao produto(opcional)");
        String obs = sc.next();
        int codigo = produtos.size() + 1;
        //cadastrando um novo produto
        Produto produto = new Produto(nome, codigo, valUnit, qtdeConversao, tipoProduto, obs);

        if(opcao == 2){
            double maisIngredientes = 0;
            do {
                cadastrarIngredienteProduto(produto, listaIngredientesProdutos);
                view.exibirMensagem("Deseja cadastrar mais Ingredientes para esse produto?");
                view.exibirMensagem("[1] - SIM; [0] - NAO");
                maisIngredientes = sc.nextDouble();

            }while(maisIngredientes != 0);
        }
        //setando a lista de ingredientes no produto
        produto.setIngredientesProdutos(listaIngredientesProdutos);


        //adicionando na lista de produtos cadastrados
        this.produtos.add(produto);

        System.out.println("Produto Cadastrado!!");
    }

    public ETipoProduto escolheTipoProduto(int tipo){
        ETipoProduto tipoProduto = ETipoProduto.NORMAL;

        switch (tipo){
            case 2 -> tipoProduto = ETipoProduto.PRODUTOCOMCOMPLEMENTO;
            case 3 -> tipoProduto = ETipoProduto.INGREDIENTE;
            case 4 -> tipoProduto = ETipoProduto.SERVICO;
        }

        return tipoProduto;
    }

    public void cadastrarIngredienteProduto(Produto produto, List<IngredientesProduto> listaIngredientesProdutos){
        view.exibirMensagem("SELECIONE UM INGREDIENTE PELO CODIGO ----");
        view.exibirProdutos(this.produtos);
        int codigoIngrediente = sc.nextInt();
        view.exibirMensagem("ESCOLHA A QUANTIDADE A SER USADA PARA MONTAGEM:");
        double qtdeMontagem = sc.nextDouble();

        Produto ingrediente = this.produtos.get(codigoIngrediente - 1);

        IngredientesProduto ingr = new IngredientesProduto(produto, ingrediente, qtdeMontagem);

        listaIngredientesProdutos.add(ingr);
    }

    public void cadastrarEstoque(Produto produto){

    }
}
