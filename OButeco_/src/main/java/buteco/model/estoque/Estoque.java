package buteco.model.estoque;

import buteco.model.conversao.Conversao;
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

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public double getQtdeEstoque() {
        return qtdeEstoque;
    }

    public void setQtdeEstoque(double qtdeEstoque) {
        this.qtdeEstoque = qtdeEstoque;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getCodEstoque() {
        return codEstoque;
    }

    public void setCodEstoque(int codEstoque) {
        this.codEstoque = codEstoque;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

}
