package buteco.controller.produtos;

import buteco.enums.EStatus;
import buteco.model.conversao.Conversoes;
import buteco.model.estoque.Estoque;
import buteco.model.produto.Categoria;
import buteco.model.produto.Grupo;
import buteco.model.produto.InsumosProduto;
import buteco.model.produto.Produto;
import buteco.repositories.ProdutoRepository;
import buteco.service.*;
import buteco.service.entradas.ErroEntrada;
import buteco.view.ProdutosView;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProdutosController {
    private ProdutosView view;
    private Scanner sc;
    private ErroEntrada errorEntrada;
    private ProdutoRepository produtoRepository;
    private CategoriaService categoriaService;
    private GrupoService grupoService;
    private ProdutoService produtoService;
    private InsumosProdutoService insumosProdutoService;
    private EstoqueService estoqueService;

//    public VerificaEntradaProduto verificaEntradaProduto;

    public ProdutosController(
            Scanner sc,
            ErroEntrada errorEntrada,
            ProdutoRepository produtoRepository,
            CategoriaService categoriaService,
            GrupoService grupoService,
            ProdutoService produtoService,
            InsumosProdutoService insumosProdutoService,
            EstoqueService estoqueService
    ){
        this.sc = sc;
        this.errorEntrada = errorEntrada;
        this.view = new ProdutosView(sc, errorEntrada, produtoRepository);
        this.produtoRepository = produtoRepository;
        this.categoriaService = categoriaService;
        this.grupoService = grupoService;
        this.produtoService = produtoService;
        this.insumosProdutoService = insumosProdutoService;
        this.estoqueService = estoqueService;
    }
    public void index(){
        int opcao; //declarando vazia

        do{
            opcao = view.exibirMenu();
            switch (opcao){
                case 1 -> cadastrarProduto();
                case 2 -> view.exibirProdutos();
                case 3 -> editarProduto();
                case 4 -> excluirProduto();
                case 0 -> view.exibirMensagem("VOLTANDO..");
                default -> view.exibirMensagem("VALOR INVALIDO!!!");
            }

        }while(opcao != 0 );
    }

    public void cadastrarProduto(){

        String nome = errorEntrada.trataEntradaString("Insira o nome do Produto:");
        double valUnit = errorEntrada.trataEntradaDouble("Insira o valor unitario:");

        //chama funcao para verificar entradas;
        Categoria categoria = this.solicitaEntradaCategoria();
        Grupo grupo = this.solicitaEntradaGrupo();

        //caso queira add observacao no produto
        //sc.nextLine(); //esse sc server pois as vezes vem um "enter" a mais
        view.exibirMensagem("Observacao produto(opcional)");
        String obs = sc.nextLine();

        Produto produto = new Produto();
        if (categoria.getCategoria().equals("PRODUTO COM INSUMOS")){
            produto.setInsumos(this.adicionarInsumos(produto));
        }


        produto.setCategoria(categoria);
        produto.setGrupo(grupo);

        produto.setNome(nome);
        produto.setPrecoVenda(valUnit);
        produto.setObservacao(obs);

        produtoService.salvarProduto(produto);

        if (categoria.getCategoria().equals("PRODUTO COM INSUMOS")){
            this.cadastraEstoqueProdutoComInsumo(produto);
        }

        System.out.println("Produto Cadastrado!!");
    }

    public Categoria solicitaEntradaCategoria(){
        while(true){
            try{
                System.out.println("========CATEGORIAS========");
                categoriaService.findAllCategoria().stream().forEach(System.out::println);
                Long opcao = errorEntrada.trataEntradaLong("Insira a categoria: ");
                return categoriaService.findById(opcao);
            }catch (IllegalArgumentException e){
                System.out.println("Categoria não encontrada, tente novamente!");
            }

        }

    }

    public Grupo solicitaEntradaGrupo(){
        while(true){
            try{
                System.out.println("========GRUPOS========");
                grupoService.findAllGrupo().stream().forEach(System.out::println);
                Long idGrupo = errorEntrada.trataEntradaLong("Insira o grupo: ");
                return grupoService.findById(idGrupo);
            }catch (IllegalArgumentException e){
                System.out.println("Grupo não encontrada, tente novamente!");
            }

        }
    }

    public List<InsumosProduto> adicionarInsumos(Produto prod){
            List <InsumosProduto> list = new ArrayList<>();

            while(true){
                InsumosProduto insumosProduto = new InsumosProduto();
                Produto insumo = this.solicitaEntradaIngrediente();

                insumosProduto.setProduto(prod);
                insumosProduto.setInsumo(insumo);
                double qtde = errorEntrada.trataEntradaDouble("Insira a quantidade a ser usada: ");
                insumosProduto.setQtde(qtde);

                list.add(insumosProduto);
                int resp = errorEntrada.trataEntradaInt("Deseja adicionar mais? [1] - SIM; [0] - NAO.");
                if (resp == 0){
                    return list;
                }
            }

        }

    public Produto solicitaEntradaIngrediente(){
            while (true){
                try{
                    view.exibirIngredientes();
                    Long idProd = errorEntrada.trataEntradaLong("Insira o Insumo");
                    Produto ing = produtoService.findById(idProd);

                    if(ing.getStatus().equals(EStatus.INATIVO)){
                        System.out.println("Ingrediente INATIVO");
                    }
                    if (ing.getCategoria().getCategoria().equals("INSUMO") || ing.getCategoria().getCategoria().equals("SERVICO") ){
                        return ing;
                    }
                }catch (IllegalArgumentException e ){
                    System.out.println("Ingrediente nao encontrado");
                }
            }
        }

    public Produto solicitaEntradaProduto(){
        view.exibirProdutos();
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
    public void editarProduto(){
        Produto produto = this.solicitaEntradaProduto();


        String nome = errorEntrada.trataEntradaString("Insira o nome do Produto:");
        double valUnit = errorEntrada.trataEntradaDouble("Insira o valor unitario:");

        Categoria categoria = produto.getCategoria();

        //nao deixa alterar o tipo de produto, caso seja insumo ou servico
        if(produto.getCategoria().getCategoria().equals("NORMAL") ){
            //chama funcao para verificar entradas;
            categoria = this.solicitaEntradaCategoria();
        }

        Grupo grupo = this.solicitaEntradaGrupo();

        //caso queira add observacao no produto
        //sc.nextLine(); //esse sc server pois as vezes vem um "enter" a mais
        view.exibirMensagem("Observacao produto(opcional)");
        String obs = sc.nextLine();

        if (categoria.getCategoria().equals("PRODUTO COM INSUMOS")){
            this.removerInsumos(produto);
            produto.setInsumos(this.adicionarInsumos(produto));
        }


        produto.setCategoria(categoria);
        produto.setGrupo(grupo);

        produto.setNome(nome);
        produto.setPrecoVenda(valUnit);
        produto.setObservacao(obs);

        produtoService.salvarProduto(produto);

        if (categoria.getCategoria().equals("PRODUTO COM INSUMOS")){
            this.cadastraEstoqueProdutoComInsumo(produto);
        }

        System.out.println("Produto Editado!!");

    }

    public void removerInsumos(Produto prod){
        if(!prod.getInsumos().isEmpty()){
            prod.getInsumos().forEach(element -> {
                insumosProdutoService.deletarInsumos(element.getId());
            });
        }
    }

    public void excluirProduto(){
        Produto produto = this.solicitaEntradaProduto();
        produto.setStatus(EStatus.INATIVO);
        produtoService.excluirProduto(produto);
        System.out.println("PRODUTO INATIVADO");

    }

    public void cadastraEstoqueProdutoComInsumo(Produto prod){
        estoqueService.createSimpleEstoque(prod);
    }
//
//        //setando a lista de ingredientes no produto
//        produto.setIngredientesProdutos(listaIngredientesProdutos);
//
//        //caso queira add observacao no produto
//        //sc.nextLine(); //esse sc server pois as vezes vem um "enter" a mais
//        view.exibirMensagem("Observacao produto(opcional)");
//        String obs = sc.nextLine();
//
//        produto.setObservacao(obs);
//        // funcao para setar o estoque no produto;
//        cadastrarEstoque(produto);
//
//        //adicionando na lista de produtos cadastrados
//        this.produtos.add(produto);
//
//        view.exibirMensagem("Produto Cadastrado!!");
//    }
//
//    public ETipoProduto escolheTipoProduto(int tipo){
//        ETipoProduto tipoProduto = ETipoProduto.NORMAL;
//
//        switch (tipo){
//            case 2 -> tipoProduto = ETipoProduto.PRODUTOCOMCOMPLEMENTO;
//            case 3 -> tipoProduto = ETipoProduto.INGREDIENTE;
//            case 4 -> tipoProduto = ETipoProduto.SERVICO_Hr;
//        }
//
//        return tipoProduto;
//    }
//
//    public void cadastrarIngredienteProduto(Produto produto, List<IngredientesProduto> listaIngredientesProdutos){
//        view.exibirMensagem("---PRODUTOS---");
//        view.exibirProdutos(this.produtos);
//        int codigoIngrediente = this.verificaEntradaProduto.verificaEntradaCodProduto();
//
//        double qtdeMontagem = errorEntrada.trataEntradaDouble("ESCOLHA A QUANTIDADE A SER USADA PARA MONTAGEM:");
//
//        Produto ingrediente = this.produtos.get(codigoIngrediente);
//
//        IngredientesProduto ingr = new IngredientesProduto(produto, ingrediente, qtdeMontagem);
//
//        listaIngredientesProdutos.add(ingr);
//    }
//
//    public void cadastrarEstoque(Produto produto){
//        //pegando o tamanho da lista do estoque para criar um codigo de estoque
//        int listEstoque = this.estoques.size();
//        //inicializando em "0" o estoque
//        Estoque estoque = new Estoque(listEstoque + 1, produto, 0);
//        //setando no produto a qual estoque deve se referenciar
//        produto.setEstoque(estoque);
//        //add na lista de estoques o estoque criado
//        this.estoques.add(estoque);
//    }
//
//    public void excluirProduto(){
//        if (this.produtos.size() > 0 ){
//            view.exibirMensagem("QUAL PRODUTO DESEJA EXCLUIR?");
//            view.exibirProdutos(this.produtos);
//            int cod = this.verificaEntradaProduto.verificaEntradaCodProduto();
//            this.produtos.remove(cod);
//        }else{
//            System.out.println("SEM PRODUTO CADASTRADO");
//        }
//    }
//
//    public void editarProduto(){
//        if (this.produtos.size() > 0 ){
//            view.exibirMensagem("QUAL PRODUTO DESEJA EDITAR?");
//            view.exibirProdutos(this.produtos);
//            int cod = this.verificaEntradaProduto.verificaEntradaCodProduto();
//            Produto produto = this.produtos.get(cod);
//
//            String nome = errorEntrada.trataEntradaString("Insira o nome do Produto:");
//            produto.setNome(nome);
//            double valUnit = errorEntrada.trataEntradaDouble("Insira o valor unitario:");
//            produto.setValorUnitario(valUnit);
//
//            int opcao = errorEntrada.trataEntradaInt("Tipo de produto: [1] - NORMAL; [2] - PRODUTO COM COMPLEMENTOS; [3] - INGREDIENTE; [4] - SERVICO(NAO DESCONTA DO ESTOQUE);");
//            ETipoProduto tipoProduto = escolheTipoProduto(opcao);
//            if(opcao == 2) {
//                view.exibirIngredienteProduto(produto);
//
//                int opcaoIng = errorEntrada.trataEntradaInt("DESEJA: [1] - ADICIONAR; [2] - EDITAR; [3] - REMOVER; [0] - PULAR; INGREDIENTES");
//                menuIngrediente(opcaoIng, produto, produto.getIngredientesProdutos());
//            }
//        }else{
//            System.out.println("SEM PRODUTO CADASTRADO");
//        }
//    }
//
//    public void menuIngrediente(int opcaoIng, Produto produto, List<IngredientesProduto> listaIngredientesProdutos){
//        switch (opcaoIng){
//            case 1:
//                cadastrarIngredientes(produto, listaIngredientesProdutos);
//                produto.setIngredientesProdutos(listaIngredientesProdutos);
//            break;
//            case 2:
//                editarIngrediente(produto);
//                break;
//            case 3:
//                excluirIngrediente(produto);
//                break;
//            default:
//                System.out.println("Pronto");
//                break;
//        }
//    }
//
//    public void cadastrarIngredientes(Produto produto, List<IngredientesProduto> listaIngredientesProdutos){
//        double maisIngredientes = 0;
//        do {
//            if(maisIngredientes == 0 || maisIngredientes == 1){
//                cadastrarIngredienteProduto(produto, listaIngredientesProdutos);
//            }else{
//                view.exibirMensagem("VALOR INVALIDO");
//            }
//
//            view.exibirMensagem("Deseja cadastrar mais Ingredientes para esse produto?");
//            maisIngredientes = errorEntrada.trataEntradaInt("[1] - SIM; [0] - NAO");
//
//        }while(maisIngredientes  != 0);
//    }
//
//    public void editarIngrediente(Produto produto){
//        int codIng = errorEntrada.trataEntradaInt("INSIRA O CODIGO");
//         for(IngredientesProduto p : produto.getIngredientesProdutos()){
//             if(p.getIngredienteProduto().getCodigo() == codIng){
//                 double qtdeMontagem = errorEntrada.trataEntradaDouble("ESCOLHA A QUANTIDADE A SER USADA PARA MONTAGEM:");
//                 p.setQtde(qtdeMontagem);
//
//             }
//         }
//    }
//
//    public void excluirIngrediente(Produto produto){
//        int codIng = errorEntrada.trataEntradaInt("INSIRA O CODIGO");
//        //System.out.println(codIng);
//        //produto.getIngredientesProdutos().remove(codIng);
//        for (int x = 0; x < produto.getIngredientesProdutos().size(); x++){
//            IngredientesProduto ing = produto.getIngredientesProdutos().get(x);
//
//            if(ing.getIngredienteProduto().getCodigo() == codIng){
//                produto.getIngredientesProdutos().remove(x);
//                break;
//            }
//        }
//    }
}
