package buteco.controller.estoque;

import buteco.model.estoque.Estoque;
import buteco.view.EstoqueView;

import java.time.LocalDate;
import java.util.List;

public class EstoqueController {
    private EstoqueView view;
    static List<Estoque> estoques;

    public EstoqueController() {

    }


    public void index(){

        int opcao = 0;

        do{
            opcao = view.exibirMenu();
            switch (opcao){
                case 1 -> cadastrarEntrada();
            }

        }while(opcao != 0 );
//        sc.close();

    }

    public void cadastrarEntrada(){

    }
}
