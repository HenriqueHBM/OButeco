package buteco.model.entity.estoque;

import buteco.model.entity.pessoa.Usuario;
import buteco.model.entity.conversao.Conversoes;
import buteco.model.entity.produto.Produto;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "movimentacoes_estoques")
public class MovimentacoesEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_id_estoque", nullable = false)
    private Estoque estoque;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "qtde", nullable = false)
    private double quantidade;

    @Column(name = "valor_unitario", nullable = false)
    private double valorUnitario;

    @Column(name = "valor_total")
    private double valorTotal;

    @ManyToOne
    @JoinColumn(name = "fk_id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fk_id_conversao",nullable = false)
    private Conversoes conversoes;

    @CreationTimestamp
    @Column(name = "data_movimentacao")
    private Instant dataMovimentacao;

    @Column(name = "observacao", nullable = true)
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "fk_id_produto")
    private Produto produto;

    public MovimentacoesEstoque() {
    }

    public MovimentacoesEstoque(Long id, Estoque estoque, String tipo, double quantidade, double valorUnitario, double valorTotal, Usuario usuario, Conversoes conversoes, Instant dataMovimentacao, String observacao, Produto produto) {
        this.id = id;
        this.estoque = estoque;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
        this.usuario = usuario;
        this.conversoes = conversoes;
        this.dataMovimentacao = dataMovimentacao;
        this.observacao = observacao;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Conversoes getConversoes() {
        return conversoes;
    }

    public void setConversoes(Conversoes conversoes) {
        this.conversoes = conversoes;
    }

    public Instant getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(Instant dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Produto getProduto() { return  produto; }

    public void setProduto(Produto produto) { this.produto = produto; }


    @Override
    public String toString() {
        return "MovimentacoesEstoque{" +
                "id=" + id +
                ", estoque=" + estoque +
                ", tipo='" + tipo + '\'' +
                ", quantidade=" + quantidade +
                ", valorUnitario=" + valorUnitario +
                ", valorTotal=" + valorTotal +
               // ", usuario=" + usuario +
                ", conversoes=" + conversoes +
                ", dataMovimentacao=" + dataMovimentacao +
                ", observacao='" + observacao + '\'' +
                '}';
    }
}