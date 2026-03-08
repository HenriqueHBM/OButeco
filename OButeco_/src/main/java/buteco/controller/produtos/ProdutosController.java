package buteco.controller.produtos;

import buteco.enums.ETipoProduto;
import buteco.model.estoque.Estoque;
import buteco.model.produto.IngredientesProduto;
import buteco.model.produto.Produto;
import buteco.service.entradas.ErroEntrada;
import buteco.view.ProdutosView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProdutosController {
    private ProdutosView view;
    static List<Produto> produtos;
    static List<Estoque> estoques;
    private Scanner sc;
    private ErroEntrada errorEntrada;


    // constructor da classe,
    public ProdutosController(Scanner sc, ErroEntrada errorEntrada, List<Produto> produtos, List<Estoque> estoques){
        this.view = new ProdutosView(sc, errorEntrada);
        this.sc = sc;
        this.produtos = produtos;
        this.estoques = estoques;
        this.errorEntrada = errorEntrada;
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
        String nome = errorEntrada.trataEntradaString("Insira o nome do Produto:");
        double valUnit = errorEntrada.trataEntradaDouble("Insira o valor unitario:");
        int opcao = errorEntrada.trataEntradaInt("Tipo de produto: [1] - NORMAL; [2] - PRODUTO COM COMPLEMENTOS; [3] - INGREDIENTE; [4] - SERVICO(NAO DESCONTA DO ESTOQUE);");

        ETipoProduto tipoProduto = escolheTipoProduto(opcao);

        int codigo = produtos.size() + 1;
        //cadastrando um novo produto
        Produto produto = new Produto(nome, codigo, valUnit, tipoProduto);

        if(opcao == 2){
            double maisIngredientes = 0;
            do {
                if(maisIngredientes == 0 || maisIngredientes == 1){
                    cadastrarIngredienteProduto(produto, listaIngredientesProdutos);
                }else{
                    view.exibirMensagem("VALOR INVALIDO");
                }

                view.exibirMensagem("Deseja cadastrar mais Ingredientes para esse produto?");
                maisIngredientes = errorEntrada.trataEntradaInt("[1] - SIM; [0] - NAO");

            }while(maisIngredientes != 0);
        }

        //setando a lista de ingredientes no produto
        produto.setIngredientesProdutos(listaIngredientesProdutos);

        //caso queira add observacao no produto
        //sc.nextLine(); //esse sc server pois as vezes vem um "enter" a mais
        view.exibirMensagem("Observacao produto(opcional)");
        String obs = sc.nextLine();

        produto.setObservacao(obs);
        // funcao para setar o estoque no produto;
        cadastrarEstoque(produto);

        //adicionando na lista de produtos cadastrados
        this.produtos.add(produto);

        view.exibirMensagem("Produto Cadastrado!!");
    }

    public ETipoProduto escolheTipoProduto(int tipo){
        ETipoProduto tipoProduto = ETipoProduto.NORMAL;

        switch (tipo){
            case 2 -> tipoProduto = ETipoProduto.PRODUTOCOMCOMPLEMENTO;
            case 3 -> tipoProduto = ETipoProduto.INGREDIENTE;
            case 4 -> tipoProduto = ETipoProduto.SERVICO_Hr;
        }

        return tipoProduto;
    }

    public void cadastrarIngredienteProduto(Produto produto, List<IngredientesProduto> listaIngredientesProdutos){
        view.exibirMensagem("---PRODUTOS---");
        view.exibirProdutos(this.produtos);
        int codigoIngrediente = errorEntrada.trataEntradaInt("SELECIONE UM COMPLEMENTO PELO CODIGO");
        //int codigoIngrediente = sc.nextInt();
        //view.exibirMensagem("ESCOLHA A QUANTIDADE A SER USADA PARA MONTAGEM:");
        double qtdeMontagem = errorEntrada.trataEntradaDouble("ESCOLHA A QUANTIDADE A SER USADA PARA MONTAGEM:");

        Produto ingrediente = this.produtos.get(codigoIngrediente - 1);

        IngredientesProduto ingr = new IngredientesProduto(produto, ingrediente, qtdeMontagem);

        listaIngredientesProdutos.add(ingr);
    }

    public void cadastrarEstoque(Produto produto){
        //pegando o tamanho da lista do estoque para criar um codigo de estoque
        int listEstoque = this.estoques.size();
        //inicializando em "0" o estoque
        Estoque estoque = new Estoque(listEstoque + 1, produto, 0);
        //setando no produto a qual estoque deve se referenciar
        produto.setEstoque(estoque);
        //add na lista de estoques o estoque criado
        this.estoques.add(estoque);
    }
}
