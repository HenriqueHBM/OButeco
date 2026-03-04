package buteco.model.produto;

import buteco.model.conversao.Conversao;

import java.time.LocalDateTime;

public class Produto {
    private String nome;
    private int codigo;
    private double precoVenda;
    private LocalDateTime dataCadastro;
    private double qtdeConversao;

    public Produto(String nome, int codigo, double precoVenda, double qtdeConversao) {
        this.nome = nome;
        this.codigo = codigo;
        this.precoVenda = precoVenda;
        this.dataCadastro = LocalDateTime.now();
        this.qtdeConversao = qtdeConversao;
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
