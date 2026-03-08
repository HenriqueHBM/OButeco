package buteco.service.entradas;

import buteco.model.produto.Produto;

import java.util.List;

public class VerificaEntradaProduto {
    int opcao;
    ErroEntrada errorEntrada;
    List<Produto> produtos;

    public VerificaEntradaProduto(ErroEntrada errorEntrada, List<Produto> produtos){
        this.errorEntrada = errorEntrada;
        this.produtos = produtos;
    }

    public int verificaEntradaCodProduto(){
        int opcao;
        while (true){
            opcao = errorEntrada.trataEntradaInt("Insira o codigo");
            if (opcao > 0){

                for(int x = 0; x <= this.produtos.size(); x++){
                    Produto p = produtos.get(x);
                    if(p.getCodigo() == opcao ){
                        return x;
                    }
                }
            }
            System.out.println("CODIGO INVALIDO!!");
        }
    }
}
