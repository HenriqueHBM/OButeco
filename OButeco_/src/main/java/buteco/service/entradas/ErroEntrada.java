package buteco.service.entradas;

import java.util.Scanner;

public class ErroEntrada {
    private Scanner sc;

    public ErroEntrada(Scanner sc){
        this.sc = sc;
    }

    public int  trataEntradaInt(String texto){
        while (true){
            try{
                System.out.println(texto);
                int valor = Integer.parseInt(sc.nextLine());
                return valor;
            }catch (NumberFormatException e ){
                System.out.println("VALOR INVALIDO (INSIRA UM NUMERO)");
            }
        }
    }

    public double trataEntradaDouble(String texto) {
        while (true) {
            try {
                System.out.print(texto);
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("VALOR INVÁLIDO (DIGITE UM NÚMERO)");
            }
        }
    }

    public String trataEntradaString(String msg) {
        while (true) {
            System.out.print(msg);
            String texto = sc.nextLine();

            //caso o texto nao esteja vazio
            if (!texto.isBlank()) {
                return texto;
            }

            System.out.println("O campo não pode estar vazio.");
        }
    }
}
