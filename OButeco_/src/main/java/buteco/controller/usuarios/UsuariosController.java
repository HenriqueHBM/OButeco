package buteco.controller.usuarios;

import buteco.service.entradas.ErroEntrada;
import buteco.service.entradas.VerificaEntradaProduto;
import buteco.model.movimentacoes.Entrada;
import buteco.model.movimentacoes.Saida;
import buteco.model.pessoa.Usuario;
import buteco.model.pessoa.Cargo;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsuariosController {

    //List<Usuario> usuarios = new ArrayList<>();
    //List<Cargo> cargos = new ArrayList<>();
    private Scanner sc;
    private ErroEntrada erroEntrada;


    public UsuariosController(List<Usuario> usuarios, List<Cargo> cargos, Scanner sc, ErroEntrada erroEntrada) {
    //    this.usuarios = usuarios;
    //    this.cargos = cargos;
        this.sc = sc;
        this.erroEntrada = erroEntrada;
    //    cargos.add(new Cargo(1, "ADMIN"));
    //    cargos.add(new Cargo(2, "FUNCIONARIO"));
    //    usuarios.add(new Usuario(1, "Dono", "dono", "123", cargos.get(1)));
    }

    public Usuario login(List<Usuario> usuarios) {

        System.out.println("===== LOGIN ======");

        String login = erroEntrada.trataEntradaString("Login: ");
        String senha = erroEntrada.trataEntradaString("Senha: ");

        for(Usuario u : usuarios){
            if(u.getLogin().equals(login) && u.getSenha().equals(senha)){
                System.out.println("Bem-vindo " + u.getNome());
                return u;
            }
        }

        System.out.println("Login Inválido!");
        return null;
    }

    public void cadastrarUsuario(){

        String nome = erroEntrada.trataEntradaString("Nome: ");
        String login = erroEntrada.trataEntradaString("Login: ");
        String senha = erroEntrada.trataEntradaString("Senha: ");

        /*Usuario u = new Usuario(
                usuarios.size()+1,
                nome,
                login,
                senha,
                cargos.get(1)
        );

        usuarios.add(u);

        System.out.println("Usuario Cadastrado com Sucesso!");
    */}

}
