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
}
