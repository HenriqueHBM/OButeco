package buteco.view;

import buteco.repositories.UsuarioRepository;
import buteco.service.entradas.ErroEntrada;

import java.util.Scanner;

public class UsuariosView {
    Scanner sc;
    private ErroEntrada erroEntrada;
    private UsuarioRepository usuarioRepository;

    public UsuariosView(Scanner sc, ErroEntrada erroEntrada, UsuarioRepository usuarioRepository) {
        this.sc = sc;
        this.erroEntrada = erroEntrada;
        this.usuarioRepository = usuarioRepository;
    }

    public int exibirMenu() {
        return erroEntrada.trataEntradaInt("[1] - CADASTRAR USUARIO; [2] - EDITAR USUARIO; [3] - DELETAR USUARIO; [4] - EXIBIR USUARIOS; [0]SAIR");
    }

    public void exibirUsuario() {
        var usuarios = usuarioRepository.findAll();

        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuario encontrado");
            return;
        }

        System.out.println("==== USUARIOS ====");
        usuarios.forEach(u -> System.out.println(
                "ID: " + u.getId() +
                " | Nome: " + u.getNome() +
                " | Login: " + u.getLogin() +
                " | Cargo: " + u.getCargo().getNome()
        ));
    }
}
