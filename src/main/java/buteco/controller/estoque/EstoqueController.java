package buteco.controller.estoque;

import buteco.enums.EStatus;
import buteco.model.produto.Produto;
import buteco.model.pessoa.Usuario;
import buteco.repositories.ConversoesRepository;
import buteco.repositories.EstoqueRepository;
import buteco.repositories.ProdutoRepository;
import buteco.service.EstoqueService;
import buteco.service.MovimentacoesEstoqueService;
import buteco.service.ProdutoService;
import buteco.service.entradas.ErroEntrada;
import buteco.view.EstoqueView;
import buteco.view.ProdutosView;

import java.util.Scanner;

public class EstoqueController {
    private EstoqueView estoqueView;
    private Scanner sc;
    private ErroEntrada errorEntrada;
    private ProdutosView produtosView;
    private EstoqueService estoqueService;
    private MovimentacoesEstoqueService movimentacoesEstoqueService;
    private ConversoesRepository conversoesRepository;
    private ProdutoService produtoService;
    private Usuario usuarioLogado;

    public EstoqueController(Scanner sc, ErroEntrada errorEntrada, EstoqueRepository estoqueRepository,
                             ProdutoRepository produtoRepository, EstoqueService estoqueService,
                             MovimentacoesEstoqueService movimentacoesEstoqueService,
                             ConversoesRepository conversoesRepository,
                             ProdutoService produtoService
    ){

        this.sc = sc;
        this.errorEntrada = errorEntrada;
        this.estoqueView = new EstoqueView(sc, estoqueRepository, produtoRepository,conversoesRepository ,errorEntrada);
        this.produtosView = new ProdutosView(sc, errorEntrada, produtoRepository);
        this.estoqueService = estoqueService;
        this.movimentacoesEstoqueService = movimentacoesEstoqueService;
        this.produtoService = produtoService;
    }

    public void index(){
        int opcao = 0;
            do{
                opcao = estoqueView.exibirMenu();
                switch (opcao){
                    case 1 -> cadastrarMovimentacao(1);
                    case 2 -> cadastrarMovimentacao(2);
                    case 3 -> estoqueView.exibirEstoques();
                    case 4 -> movimentacoesEstoqueService.exibirMovimentacoesEstoque();
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("VALOR INVALIDO");
                }

            }while(opcao != 0 );
    }

    public void cadastrarMovimentacao(int tipo) {
        produtosView.exibirProdutos();
        int tentativas = 0; //mantem o do-while funcionando ate finalizar a operacao

        do {
            try {

                Produto produto = this.solicitaEntradaProduto();
                Long idProduto = produto.getId();

                if(produto.getCategoria().getCategoria().equals("SERVICO")){
                    System.out.println("Produto como servico nao tem estoque!!");
                    tentativas++;
                    break;
                }

                if(produto.getCategoria().getCategoria().equals("PRODUTO COM INSUMOS") && tipo == 1){
                    System.out.println("PRODUTO COM O TIPO INSUMO, NAO CADASTRA ENTRADA!!");
                    tentativas++;
                    break;
                }

                if (idProduto == 0) {
                    System.out.println("Cadastro cancelado.");
                    break;
                } else if (tipo == 1) {
                    movimentacoesEstoqueService.confereEstoque(idProduto); //confere existencia de estoque do produto, se nao existir cria um novo
                }

                double qtde = errorEntrada.trataEntradaDouble("Insira a quantidade (0 para cancelar): ");
                if (qtde == 0) {
                    System.out.println("Cadastro cancelado.");
                    break;
                }

                if(produto.getCategoria().getCategoria().equals("PRODUTO COM INSUMOS")){
                    tipo = 3;
                }

                System.out.println(tipo);
//                switch (tipo) {
//                    case 1 -> movimentacoesEstoqueService.cadastrarEntrada(idProduto, qtde, usuarioLogado);
//                    case 2 -> movimentacoesEstoqueService.cadastrarSaida(idProduto, qtde, true, usuarioLogado);
//                    case 3 -> movimentacoesEstoqueService.cadastrarSaidacComInsumos(produto, qtde, usuarioLogado);
//                }
                System.out.println("Cadastro realizado com sucesso!");
                tentativas = 0;
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
                tentativas++;
            }
        }while(tentativas > 0);
    }

    public Produto solicitaEntradaProduto(){
        while(true){
            try{
                Long idProduto = errorEntrada.trataEntradaLong("Insira o produto: ");

                Produto prod = produtoService.findById(idProduto);
                if(prod.getStatus().equals(EStatus.ATIVO)){
                    return produtoService.findById(idProduto);
                }else{
                    System.out.println("PRODUTO INATIVO NAO PODE SER MEXIDO!");
                }
            }catch (IllegalArgumentException e){
                System.out.println("Produto não encontrada, tente novamente!");
            }
        }
    }
}
