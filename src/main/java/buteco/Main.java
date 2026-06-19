package buteco;

import buteco.config.FlyWayconfig;
import buteco.controller.estoque.EstoquesControllerInterface;
import buteco.controller.estoque.impl.EstoquesControllerImpl;
import buteco.controller.produtos.ProdutosControllerInterface;
import buteco.controller.produtos.impl.ProdutosControllerImpl;
import buteco.model.entity.pessoa.UsuarioEntity;
import buteco.model.repositories.*;
import buteco.model.repositories.estoque.ConversoesRepository;
import buteco.model.repositories.estoque.EstoqueRepository;
import buteco.model.repositories.estoque.MovimentacoesEstoqueRepository;
import buteco.model.repositories.pessoa.CargoRepository;
import buteco.model.repositories.pessoa.UsuarioRepository;
import buteco.model.repositories.produto.CategoriaRepository;
import buteco.model.repositories.produto.GrupoRepository;
import buteco.model.repositories.produto.InsumosProdutoRepository;
import buteco.model.repositories.produto.ProdutoRepository;
import buteco.model.service.*;
import buteco.model.service.entradas.ConversoesService;
import buteco.view.*;
import buteco.view.components.Cards;
import buteco.view.components.Colors;
import buteco.view.estoque.EstoquesView;
import buteco.view.pessoa.LoginView;
import buteco.view.produto.ProdutoView;
import jakarta.persistence.EntityManager;

import java.util.Locale;
import java.util.Scanner;
import java.util.logging.LogManager;

public class Main {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale", "1.0");
        System.setProperty("sun.java2d.dpiaware", "true");

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

        UsuarioEntity usuarioEntityLogado = new UsuarioEntity();

        //Services
        EstoqueService estoqueService = new EstoqueService(estoqueRepository, produtoRepository, conversoesRepository);
        MovimentacoesEstoqueService movimentacoesEstoqueService = new MovimentacoesEstoqueService(movimentacoesEstoqueRepository, estoqueRepository, estoqueService, conversoesRepository, produtoRepository, usuarioRepository);
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
//        UsuariosController usuariosController = new UsuariosController(sc, errorEntrada, cargoRepository, usuarioRepository, usuarioService, usuarioLogado);

//        LoginView loginView = new LoginView(usuarioService);
//
//        loginView.setLocationRelativeTo(null);
//        loginView.setVisible(true);
//        while (loginView.getUsuarioLogado() == null) {
//
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) { e.printStackTrace(); }
//        }

//        final UsuarioEntity usuarioEntityFinal = loginView.getUsuarioLogado();

        Colors colors = new Colors();
        Cards cards = new Cards(colors);
        MainView view = new MainView(colors, cards);

//        view.clicarUsuarioAction(e -> {
//            UsuarioView usuarioView = new UsuarioView(usuarioService, cargoRepository, usuarioRepository);
//            usuarioView.setVisible(true);
//        });

        ProdutosControllerInterface produtosController =
                new ProdutosControllerImpl(produtoService, categoriaService, grupoService, insumosProdutoService);
        EstoquesControllerInterface estoquesController =
                new EstoquesControllerImpl(produtoService, conversoesService, estoqueService, movimentacoesEstoqueService, usuarioService);

        view.clicarProdutoAction(e -> {
            ProdutoView produtoView = new ProdutoView(produtoService, insumosProdutoService, produtosController);
            produtoView.setVisible(true);
        });
        view.clicarEstoqueAction(e -> {
            EstoquesView estoquesView = new EstoquesView(estoquesController, 1L);
            estoquesView.setVisible(true);
        });

        view.clicarSair(e -> {
            em.close();
            CustomizerFactory.fechar();
            System.exit(0);
        });


    }
}