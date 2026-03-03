package buteco.model.movimentacoes;

import buteco.model.estoque.Estoque;
import buteco.model.produto.Produto;

import java.time.LocalDateTime;

public class Entrada {
    private Produto produto;
    private Estoque estoque;
    private double preco;
    private int qtde;
    private LocalDateTime dataMovimentacao;
}
