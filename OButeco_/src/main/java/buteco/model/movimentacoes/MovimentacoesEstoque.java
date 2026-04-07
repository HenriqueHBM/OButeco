package buteco.model.movimentacoes;

import buteco.model.estoque.Estoque;
import buteco.model.pessoa.Usuario;
import buteco.model.produto.Conversoes;
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
    @JoinColumn(name = "id_estoque") //colocar nome da coluna como fk_id_estoque? no momento esta como o diagrama
    private Estoque estoque; //colocar nome da var como idEstoque?

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario; //colocar nome da var como idUsuario?

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "qtde")
    private double quantidade;

    @Column(name = "valor_unitario")
    private double valorUnitario;

    @Column(name = "valor_total")
    private double valorTotal;

    @CreationTimestamp
    @Column(name = "data_movimentacao")
    private Instant dataMovimentacao;

    @Column(name = "observacao")
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "fk_id_conversao")
    private Conversoes conversoes;

    public MovimentacoesEstoque() {
    }

    public MovimentacoesEstoque(Long id, Estoque estoque, Usuario usuario, String tipo, double quantidade, double valorUnitario, double valorTotal, Instant dataMovimentacao, String observacao, Conversoes conversoes) {
        this.id = id;
        this.estoque = estoque;
        this.usuario = usuario;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
        this.dataMovimentacao = dataMovimentacao;
        this.observacao = observacao;
        this.conversoes = conversoes;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public Conversoes getConversoes() {
        return conversoes;
    }

    public void setConversoes(Conversoes conversoes) {
        this.conversoes = conversoes;
    }

    @Override
    public String toString() {
        return "MovimentacoesEstoque{" +
                "id=" + id +
                ", estoque=" + estoque +
                ", usuario=" + usuario +
                ", tipo='" + tipo + '\'' +
                ", quantidade=" + quantidade +
                ", valorUnitario=" + valorUnitario +
                ", valorTotal=" + valorTotal +
                ", dataMovimentacao=" + dataMovimentacao +
                ", observacao='" + observacao + '\'' +
                ", conversoes=" + conversoes +
                '}';
    }
}
