package buteco.model.movimentacoes;

import buteco.model.estoque.Estoque;
import buteco.model.produto.Produto;

import java.time.LocalDateTime;

public class Saida {
    private Produto produto;
    private Estoque estoque;
    private double preco;
    private int qtde;
    private LocalDateTime dataMovimentacao;

    public Saida(Produto produto, Estoque estoque, double preco, int qtde, LocalDateTime dataMovimentacao) {
        this.produto = produto;
        this.estoque = estoque;
        this.preco = preco;
        this.qtde = qtde;
        this.dataMovimentacao = dataMovimentacao;
    }

    public Produto getProduto() { return produto; }

    public void setProduto(Produto produto) { this.produto = produto; }

    public Estoque getEstoque() { return estoque; }

    public void setEstoque(Estoque estoque) { this.estoque = estoque; }

    public double getPreco() { return preco; }

    public void setPreco(double preco) { this.preco = preco; }

    public int getQtde() { return qtde; }

    public void setQtde(int qtde) { this.qtde = qtde; }

    public LocalDateTime getDataMovimentacao() { return dataMovimentacao; }

    public void setDataMovimentacao(LocalDateTime dataMovimentacao) { this.dataMovimentacao = dataMovimentacao; }
}
