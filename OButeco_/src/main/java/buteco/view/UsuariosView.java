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
        return erroEntrada.trataEntradaInt("[1] - CADASTRAR USUARIO; [0]SAIR");
    }
}
