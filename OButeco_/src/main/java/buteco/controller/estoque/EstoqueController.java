package buteco.controller.estoque;

import buteco.enums.ETipoProduto;
import buteco.model.estoque.Estoque;
import buteco.model.movimentacoes.Entrada;
import buteco.model.movimentacoes.Saida;
import buteco.model.produto.IngredientesProduto;
import buteco.model.produto.Produto;
import buteco.service.entradas.ErroEntrada;
import buteco.view.EstoqueView;
import buteco.view.ProdutosView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EstoqueController {
    private EstoqueView view;
    static List<Estoque> estoques;
    static List<Produto> produtos;
    List<Saida> saidas;
    private Scanner sc;
    private ErroEntrada errorEntrada;
    private ProdutosView viewProd;

    public EstoqueController(Scanner sc, ErroEntrada errorEntrada, List<Produto> produtos, List<Estoque> estoques, List<Saida> saidas) {
        this.sc = sc;
        this.produtos = produtos;
        this.estoques = estoques;
        this.saidas = saidas;
        this.view = new EstoqueView(sc);
        this.errorEntrada = errorEntrada;
        this.viewProd = new ProdutosView(sc, errorEntrada);
    }


    public void index(){

        int opcao = 0;

        do{
            opcao = view.exibirMenu();
            switch (opcao){
                case 1 -> cadastrarEntrada();
                case 2 -> cadastrarSaida();
                case 3 -> view.exibirEstoque(this.estoques);
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("asdfasdf");
            }

        }while(opcao != 0 );
//        sc.close();

    }

    public void cadastrarEntrada(){

        System.out.println("Produtos para realizar a entrada");

        viewProd.exibirProdutos(this.produtos);

        Produto produto = this.produtos.get(verificaEntradaCodProduto());

        double qtdeEntrada = errorEntrada.trataEntradaDouble("Insira a quantidade de entrada");

        Estoque estoque = produto.getEstoque();


        if(estoque == null){

            estoque = new Estoque(
                    estoques.size() + 1,
                    produto,
                    qtdeEntrada
            );

            produto.setEstoque(estoque);
            estoques.add(estoque);

        }else{


            estoque.setQtdeEstoque(
                    estoque.getQtdeEstoque() + qtdeEntrada
            );
        }

        estoque.atualizaValorTotalEstoque();

        double custo = produto.getValorUnitario() * qtdeEntrada;

        Entrada entrada = new Entrada(
                produto,
                estoque,
                custo,
                (int) qtdeEntrada,
                LocalDateTime.now()
        );

        estoque.getEntradas().add(entrada);

        System.out.println("Entrada realizada!");
    }

    public void cadastrarSaida(){
        System.out.println("produtos para realizar a Saida");
        viewProd.exibirProdutos(this.produtos);
        //int opcao = errorEntrada.trataEntradaInt("Insira o codigo");
        //- 1 pois a lista comeca em "0"
        Produto produto = this.produtos.get(verificaEntradaCodProduto());

        double qtdeSaida = errorEntrada.trataEntradaDouble("Insira a quantidade de saida");

        Estoque estoque = produto.getEstoque();
        double custoProducaoTotal = 0;
        switch (produto.getTipoProduto()){
            case PRODUTOCOMCOMPLEMENTO -> custoProducaoTotal += saidaComplemento(produto, qtdeSaida);
            case SERVICO_Hr -> System.out.println("NAO E POSSIVEL REALIZAR A SAIDA DO TIPO SERVICO");
            default -> custoProducaoTotal += saidaNormal(produto, estoque, qtdeSaida);
        }
        System.out.println(custoProducaoTotal);
        view.exibirMargemLucro(produto, calcularMargem(custoProducaoTotal, produto.getValorUnitario()));
        System.out.println("Saida Realizada!");
    }

    //produtos que nao tem complemento/ingrediente na montagem
    public double saidaNormal(Produto produto, Estoque estoque, double qtdeSaida){
        //ajustando o estoque
        estoque.setQtdeEstoque(estoque.getQtdeEstoque() - qtdeSaida);
        //atualizando o valor no estoque
        estoque.atualizaValorTotalEstoque();
        //salvando o custo de producao
        double custoProducao = produto.getValorUnitario() * qtdeSaida;
        //criando a movimentacao de saida e add no estoque
        Saida saida = new Saida(produto, estoque, custoProducao, qtdeSaida);
        estoque.addSaida(saida);

        //salvando na variavel global a saida
        this.saidas.add(saida);
        return custoProducao;
    }

    public double saidaComplemento(Produto produto, double qtdeSaida){
        double custoProducao = 0;
        //passando pelos ingredientes do produto
        for (IngredientesProduto ip : produto.getIngredientesProdutos()){
            Estoque estoque = ip.getIngredienteProduto().getEstoque();

            //caso o complemento nao seja do tipo servivo, realiza as saidas
            if (ip.getIngredienteProduto().getTipoProduto() != ETipoProduto.SERVICO_Hr){
                custoProducao += saidaNormal(ip.getIngredienteProduto(), estoque, qtdeSaida * ip.getQtde());
            }else{
                //caso for um servico apenas pegar o custo de mao de obra
                custoProducao += ip.getIngredienteProduto().getValorUnitario() * qtdeSaida;
            }
        }
        return  custoProducao;
    }

    public double calcularMargem(double custo, double precoVenda){
        return ((precoVenda - custo) / precoVenda) * 100;
    }

    public int verificaEntradaCodProduto(){
        int opcao;
        while (true){
            opcao = errorEntrada.trataEntradaInt("Insira o codigo");
            if (opcao > 0 && opcao <= this.produtos.size()){
                return opcao - 1;
            }
            System.out.println("CODIGO INVALIDO!!");
        }
    }



}
