package buteco.model.entity.estoque;

import buteco.model.entity.conversao.ConversoesEntity;
import buteco.model.entity.pessoa.UsuarioEntity;
import buteco.model.entity.produto.Produto;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "movimentacoes_estoques")
public class MovimentacoesEstoqueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_id_estoque", nullable = false)
    private EstoqueEntity estoqueEntity;

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
    private UsuarioEntity usuarioEntity;

    @ManyToOne
    @JoinColumn(name = "fk_id_conversao",nullable = false)
    private ConversoesEntity conversoesEntity;

    @CreationTimestamp
    @Column(name = "data_movimentacao")
    private Instant dataMovimentacao;

    @Column(name = "observacao", nullable = true)
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "fk_id_produto")
    private Produto produto;

    public MovimentacoesEstoqueEntity() {
    }

    public MovimentacoesEstoqueEntity(Long id, EstoqueEntity estoqueEntity, String tipo, double quantidade, double valorUnitario, double valorTotal, UsuarioEntity usuarioEntity, ConversoesEntity conversoesEntity, Instant dataMovimentacao, String observacao, Produto produto) {
        this.id = id;
        this.estoqueEntity = estoqueEntity;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
        this.usuarioEntity = usuarioEntity;
        this.conversoesEntity = conversoesEntity;
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

    public EstoqueEntity getEstoque() {
        return estoqueEntity;
    }

    public void setEstoque(EstoqueEntity estoqueEntity) {
        this.estoqueEntity = estoqueEntity;
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

    public UsuarioEntity getUsuario() {
        return usuarioEntity;
    }

    public void setUsuario(UsuarioEntity usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
    }

    public ConversoesEntity getConversoes() {
        return conversoesEntity;
    }

    public void setConversoes(ConversoesEntity conversoesEntity) {
        this.conversoesEntity = conversoesEntity;
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
                ", estoque=" + estoqueEntity +
                ", tipo='" + tipo + '\'' +
                ", quantidade=" + quantidade +
                ", valorUnitario=" + valorUnitario +
                ", valorTotal=" + valorTotal +
               // ", usuario=" + usuario +
                ", conversoes=" + conversoesEntity +
                ", dataMovimentacao=" + dataMovimentacao +
                ", observacao='" + observacao + '\'' +
                '}';
    }
}