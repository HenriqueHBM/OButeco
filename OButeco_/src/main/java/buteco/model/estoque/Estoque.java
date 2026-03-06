package buteco.model.estoque;

import buteco.model.conversao.Conversao;
import buteco.model.produto.Produto;
import buteco.model.restaurante.Restaurante;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Estoque {
    private Produto produto;
    private double qtdeEstoque;
    private double valorTotal;
    private int codEstoque;
    private String local;
    private LocalDateTime dataCadastro;

    public Estoque(
            Produto produto,
            double qtdeEstoque,
            int codEstoque
    ){
        this.produto = produto;
        this.qtdeEstoque = qtdeEstoque;
        this.codEstoque = codEstoque;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
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
