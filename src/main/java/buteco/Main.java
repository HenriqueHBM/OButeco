package buteco;

import buteco.config.FlyWayconfig;
import buteco.controller.estoque.EstoqueController;
import buteco.controller.estoque.EstoquesController;
import buteco.controller.produtos.ProdutosController;
import buteco.controller.usuarios.UsuariosController;
import buteco.model.produto.*;
import buteco.model.pessoa.Usuario;
import buteco.repositories.*;
import buteco.service.*;
import buteco.service.entradas.ConversoesService;
import buteco.service.entradas.ErroEntrada;
import buteco.view.*;
import buteco.view.components.Cards;
import buteco.view.components.Colors;
import jakarta.persistence.EntityManager;

import java.util.Locale;
import java.util.Scanner;
import java.util.logging.LogManager;

public class Main {
    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration(
                    Main.class.getClassLoader().getResourceAsStream("logging.properties")
            );
        } catch (Exception e) {
            System.out.println("Erro ao carregar configuração de log");
        }

        FlyWayconfig.migrate();
        FlyWayconfig.migrate();

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in); //passar isso para as classesControllers para nao ficar instanciando o tempo todo
        System.out.println("--O BUTECO--");

        EntityManager em = CustomizerFactory.getEntityManager();

        //Repositories
        ProdutoRepository produtoRepository = new ProdutoRepository(em);
        CategoriaRepository categoriaRepository = new CategoriaRepository(em);
        GrupoRepository grupoRepository = new GrupoRepository(em);
        InsumosProdutoRepository insumosProdutoRepository = new InsumosProdutoRepository(em);
        EstoqueRepository estoqueRepository = new EstoqueRepository(em);
        MovimentacoesEstoqueRepository movimentacoesEstoqueRepository = new MovimentacoesEstoqueRepository(em);
        ConversoesRepository conversoesRepository = new ConversoesRepository(em);
        UsuarioRepository usuarioRepository = new UsuarioRepository(em);
        CargoRepository cargoRepository = new CargoRepository(em);
        //

        ErroEntrada errorEntrada = new ErroEntrada(sc);
        Usuario usuarioLogado = new Usuario();

        //Services
        EstoqueService estoqueService = new EstoqueService(estoqueRepository, produtoRepository, conversoesRepository, errorEntrada);
        MovimentacoesEstoqueService movimentacoesEstoqueService = new MovimentacoesEstoqueService(movimentacoesEstoqueRepository, estoqueRepository, estoqueService, conversoesRepository, produtoRepository, errorEntrada, usuarioRepository);
        CategoriaService categoriaService = new CategoriaService(categoriaRepository);
        GrupoService grupoService = new GrupoService(grupoRepository);
        ProdutoService produtoService = new ProdutoService(produtoRepository);
        InsumosProdutoService insumosProdutoService = new InsumosProdutoService(insumosProdutoRepository);
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);
        ConversoesService conversoesService = new ConversoesService(conversoesRepository);
        //

////      Declarando os controllers
//        ProdutosController produtosController = new ProdutosController(sc, errorEntrada, produtoRepository, categoriaService, grupoService, produtoService, insumosProdutoService, estoqueService);
//        EstoqueController estoqueController = new EstoqueController(sc, errorEntrada, estoqueRepository, produtoRepository, estoqueService, movimentacoesEstoqueService, conversoesRepository, produtoService);
        UsuariosController usuariosController = new UsuariosController(sc, errorEntrada, cargoRepository, usuarioRepository, usuarioService, usuarioLogado);
        EstoquesController estoquesController = new EstoquesController(produtoService, conversoesService, estoqueService, movimentacoesEstoqueService);

        LoginView loginView = new LoginView(usuarioService);

        loginView.setLocationRelativeTo(null);
        loginView.setVisible(true);
        while (loginView.getUsuarioLogado() == null) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) { e.printStackTrace(); }
        }

        final Usuario usuarioFinal = loginView.getUsuarioLogado();

        Colors colors = new Colors();
        Cards cards = new Cards(colors);
        MainView view = new MainView(colors, cards);

        view.clicarUsuarioAction(e -> {
            UsuarioView usuarioView = new UsuarioView(usuarioService, cargoRepository, usuarioRepository);
            usuarioView.setVisible(true);
        });
        view.clicarProdutoAction(e -> {
            ProdutoView produtoView = new ProdutoView(produtoService, categoriaService, grupoService);
            produtoView.setVisible(true);
        });
        view.clicarEstoqueAction(e -> {
            EstoquesView estoquesView = new EstoquesView(estoquesController, usuarioFinal);
            estoquesView.setVisible(true);
        });


//        do{
//            // Funcao para tentar tratar caso usuario passe um caracter
//            entradaMenu = errorEntrada.trataEntradaInt("[1] - PRODUTOS; [2] - ESTOQUE; [3] - USUARIOS;  [0] - SAIR");
//            switch (entradaMenu){
//                case 1 -> produtosController.index();
//                case 2 -> estoqueController.index();
//                case 3 -> usuariosController.index();
//                case 0 -> System.out.println("ATE MAIS!!!");
//                default -> System.out.println("VALOR INVALIDO!!!");
//
//            }
//        }while(entradaMenu != 0 );

        view.clicarSair(e -> {
            em.close();
            CustomizerFactory.fechar();
            System.exit(0);
        });

        //criando a tabela e um valor nela já
//        Grupo grupo = new Grupo();
//        grupo.setGrupo("Comida");
//        grupoRepository.create(grupo);
//
//        //criando uma categoria nova
//        Categoria new_cat = new Categoria();
//        new_cat.setCategoria("COM_INSUMO");
//        categoriaRepository.create(new_cat);

        //buscando as info no banco
//        var cat = categoriaRepository.findById(1L);
//        var grupo_tb = grupoRepository.findById(1L);
//
//        //setando um ingrediente
//        Produto p2 = new Produto();
//        p2.setNome("Queijo");
//        p2.setPrecoVenda(0.50);
//        p2.setCategoria(cat);
//        p2.setGrupo(grupo_tb);
//        produtoRepository.create(p2);
//
//        //setando um novo produto
//        Produto p1 = new Produto();
//        p1.setNome("Pizza");
//        p1.setPrecoVenda(45.00);
//        p1.setCategoria(cat);
//        p1.setGrupo(grupo_tb);
//        produtoRepository.create(p1);
//
//        //relacao de produto e insumos
//        InsumosProduto rel = new InsumosProduto();
//        rel.setProduto(p1);
//        rel.setInsumo(p2);
//        rel.setQtde(200);
//
//        insumosProdutoRepository.create(rel);
//
//        //pegando a relacao do produto(pizza) e add um ingrediente na lista
//        p1.getInsumos().add(rel);
////
//        //p
//        System.out.println(p1);;
//

    }
}