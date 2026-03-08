package buteco.model.estoque;

import buteco.model.conversao.Conversao;
import buteco.model.movimentacoes.Entrada;
import buteco.model.movimentacoes.Saida;
import buteco.model.produto.Produto;
import buteco.model.restaurante.Restaurante;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Estoque {
    private Produto produto;
    private double qtdeEstoque;
    private double valorTotal;
    private int codEstoque;
    private String local;
    private LocalDateTime dataCadastro;
    private List<Saida> saidas;
    private List<Entrada> entradas;

    public Estoque(
            int codEstoque,
            Produto produto,
            double qtdeEstoque
    ){
        this.produto = produto;
        this.qtdeEstoque = qtdeEstoque;
        this.codEstoque = codEstoque;
        this.saidas = new ArrayList<>();
        this.entradas = new ArrayList<>();
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

    public List<Saida> getSaidas() {
        return saidas;
    }

    public void setSaidas(List<Saida> saidas) {
        this.saidas = saidas;
    }

    public void addSaida(Saida saida){
        this.saidas.add(saida);
    }

    public List<Entrada> getEntradas() {
        return entradas;
    }

    public void setEntradas(List<Entrada> entradas) {
        this.entradas = entradas;
    }

    public void atualizaValorTotalEstoque(){
        setValorTotal(getQtdeEstoque() * produto.getValorUnitario());
    }
}
