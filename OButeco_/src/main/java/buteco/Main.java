package buteco;

import buteco.model.produto.Categoria;
import buteco.model.produto.Conversoes;
import buteco.model.produto.Produto;
import buteco.repositories.CategoriaRepository;
import buteco.repositories.ConversoesRepository;
import buteco.repositories.CustomizerFactory;
import buteco.repositories.ProdutoRepository;
import jakarta.persistence.EntityManager;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in); //passar isso para as classesControllers para nao ficar instanciando o tempo todo
        System.out.println("--O BUTECO--");

        EntityManager em = CustomizerFactory.getEntityManager();
        ProdutoRepository produtoRepository = new ProdutoRepository(em);
        CategoriaRepository categoriaRepository = new CategoriaRepository(em);
        ConversoesRepository conversoesRepository = new ConversoesRepository(em);

        //criando a tabela e um valor nela já
        Conversoes con = new Conversoes();
        con.setNome("Unidade");
        con.setNomenclatura("Un");
        conversoesRepository.create(con);

        //criando uma categoria nova
        Categoria new_cat = new Categoria();
        new_cat.setCategoria("NORMAL");
        categoriaRepository.create(new_cat);

        //buscando as info no banco
        var cat = categoriaRepository.findById(1L);
        var conversao = conversoesRepository.findById(1L);

        //setando um novo produto
        Produto p1 = new Produto();
        p1.setNome("Coca");
        p1.setPrecoVenda(15.00);
        p1.setCategoria(cat);
        p1.setConversao(conversao);
        produtoRepository.create(p1);

        //p
        System.out.println(p1);;

        em.close();
        CustomizerFactory.fechar();

//        int entradaMenu = 0;
//        List<Produto> produtos = new ArrayList<>();
//        List<Estoque> estoques = new ArrayList<>();
//        List<Saida> saidas = new ArrayList<>();
//        ErroEntrada errorEntrada = new ErroEntrada(sc);
//
////      Declarando os controllers
//        ProdutosController produtosController = new ProdutosController(sc, errorEntrada, produtos, estoques);
//        EstoqueController estoqueController = new EstoqueController(sc, errorEntrada, produtos, estoques, saidas);
//        UsuariosController usuarioController = new UsuariosController();
//
//
//        do{
//            // Funcao para tentar tratar caso usuario passe um caracter
//            entradaMenu = errorEntrada.trataEntradaInt("[1] - PRODUTOS; [2] - ESTOQUE; [3] - USUARIOS;  [0] - SAIR");
//            switch (entradaMenu){
//                case 1 -> produtosController.index();
//                case 2 -> estoqueController.index();
//                case 3 -> System.out.println("EM DESENVOLVIMENTO");
//                case 0 -> System.out.println("ATE MAIS!!!");
//                default -> System.out.println("VALOR INVALIDO!!!");
//
//            }
//        }while(entradaMenu != 0 );
    }
}