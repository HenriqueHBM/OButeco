package buteco.model.produto;

import buteco.enums.ETipoProduto;
import buteco.model.conversao.Conversao;
import buteco.model.estoque.Estoque;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Produto {
    private String nome;
    private int codigo;
    private double precoVenda;
    private LocalDateTime dataCadastro;
    private ETipoProduto tipoProduto;
    private double valorUnitario;
    private List<IngredientesProduto> ingredientesProdutos = new ArrayList<>();
    private String observacao;
    private Estoque estoque;

    public Produto(String nome, int codigo, double valorUnitario, ETipoProduto tipoProduto) {
        this.nome = nome;
        this.codigo = codigo;
        this.dataCadastro = LocalDateTime.now();
        this.tipoProduto = tipoProduto;
        this.valorUnitario = valorUnitario;
    }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public int getCodigo() { return codigo; }

    public void setCodigo(int codigo) { this.codigo = codigo; }

    public double getPrecoVenda() { return precoVenda; }

    public void setPrecoVenda(double precoVenda) { this.precoVenda = precoVenda; }

    public LocalDateTime getDataCadastro() { return dataCadastro; }

    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }

    public ETipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(ETipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public List<IngredientesProduto> getIngredientesProdutos() {
        return ingredientesProdutos;
    }

    public void setIngredientesProdutos(List<IngredientesProduto> ingredientesProdutos) {
        this.ingredientesProdutos = ingredientesProdutos;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }
}
