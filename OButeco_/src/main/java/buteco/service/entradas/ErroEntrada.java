package buteco.service.entradas;

import java.util.Scanner;

public class ErroEntrada {
    private Scanner sc = new Scanner(System.in);
    public int  trataEntradaInt(String texto){
        int entradaDado = 0 ;
        int erro = 0;

        try {
            System.out.println(texto);
            entradaDado = sc.nextInt();
        }catch (Exception exception){
            System.out.println("VALOR INVALIDO (INSIRA UM NUMERO)");
            erro = 1;
        }

        return entradaDado;
    }
}
