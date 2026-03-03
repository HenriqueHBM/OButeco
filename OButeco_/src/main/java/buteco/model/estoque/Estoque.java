package buteco.model.estoque;

import buteco.model.produto.Produto;
import buteco.model.restaurante.Restaurante;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Estoque {
    private Produto produto;
    private Restaurante restaurante;
    private double qtdeEstoque;
    private double valorUnitario;
    private double valorTotal;
    private int codEstoque;
    private LocalDate dataValidade;
    private String local;
    private LocalDateTime dataCadastro;
    private Conversao conversao;

    public Estoque(
       Produto produto,
       Restaurante restaurante,
       double valorUnitario,
       int codEstoque,
       LocalDate dataValidade,
       Conversao conversao
   ){
        this.produto = produto;
        this.restaurante = restaurante;
        this.valorUnitario = valorUnitario;
        this.codEstoque = codEstoque;
        this.dataValidade = dataValidade;
        this.conversao = conversao;
    }
}
