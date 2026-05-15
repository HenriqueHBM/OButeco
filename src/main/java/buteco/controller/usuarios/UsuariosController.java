package buteco.controller.usuarios;

import buteco.service.entradas.ErroEntrada;
import buteco.service.UsuarioService;
import buteco.repositories.UsuarioRepository;
import buteco.repositories.CargoRepository;
import buteco.service.entradas.VerificaEntradaProduto;
import buteco.model.pessoa.Usuario;
import buteco.model.pessoa.Cargo;
import buteco.view.UsuariosView;

import java.util.Scanner;

public class UsuariosController {

    private UsuariosView view;
    private UsuarioRepository usuarioRepository;
    private UsuarioService usuarioService;
    private CargoRepository cargoRepository;
    private Scanner sc;
    private ErroEntrada erroEntrada;
    private Usuario usuarioLogado;

    public UsuariosController(
            Scanner sc,
            ErroEntrada erroEntrada,
            CargoRepository cargoRepository,
            UsuarioRepository usuarioRepository,
            UsuarioService usuarioService,
            Usuario usuarioLogado
            )
    {
        this.sc = sc;
        this.erroEntrada = erroEntrada;
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
        this.view = new UsuariosView(sc, erroEntrada, usuarioRepository);
        this.cargoRepository = cargoRepository;
        this.usuarioLogado = usuarioLogado;
    }

    public void index(){
        int opcao;

        do {
            opcao = view.exibirMenu();
            switch (opcao) {
                case 1 -> cadastrarUsuario(usuarioLogado);
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("VALOR INVALIDO");
            }
        }while (opcao != 0);
    }

    public Usuario login () {

        System.out.println("===== LOGIN ======");

        String login = erroEntrada.trataEntradaString("Login: ");
        String senha = erroEntrada.trataEntradaString("Senha: ");

        try{
            usuarioLogado = usuarioService.login(login, senha);
            System.out.println("Bem-vindo " + usuarioLogado.getNome());
            return usuarioLogado;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void cadastrarUsuario(Usuario usuarioLogado) {

        if(usuarioLogado.getCargo().getId() != 2){
            System.out.println("Acesso negado!");
            return;
        }

        String nome = erroEntrada.trataEntradaString("Nome: ");
        String login = erroEntrada.trataEntradaString("Login: ");
        String senha = erroEntrada.trataEntradaString("Senha: ");

        Long idCargo = erroEntrada.trataEntradaLong("Id do Cargo: ");
        Cargo cargo = cargoRepository.findById(idCargo);

        if (cargo == null) {
            System.out.println("Cargo não encontrado");
            return;
        }

        usuarioService.cadastrar(nome, login, senha, cargo);

        System.out.println("Usuário cadastrado com sucesso!");
    }

}
