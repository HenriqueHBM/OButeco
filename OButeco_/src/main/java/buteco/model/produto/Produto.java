package buteco.model.produto;

import java.time.LocalDateTime;

public class Produto {
    private String nome;
    private int codigo;
    private double precoVenda;
    private LocalDateTime dataCadastro;

    public Produto(String nome, int codigo, double precoVenda, LocalDateTime dataCadastro) {
        this.nome = nome;
        this.codigo = codigo;
        this.precoVenda = precoVenda;
        this.dataCadastro = dataCadastro;
    }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public int getCodigo() { return codigo; }

    public void setCodigo(int codigo) { this.codigo = codigo; }

    public double getPrecoVenda() { return precoVenda; }

    public void setPrecoVenda(double precoVenda) { this.precoVenda = precoVenda; }

    public LocalDateTime getDataCadastro() { return dataCadastro; }

    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }
}
