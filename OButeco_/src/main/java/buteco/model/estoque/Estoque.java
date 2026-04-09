package buteco.model.estoque;

import buteco.model.conversao.Conversoes;
import buteco.model.produto.Produto;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "estoques")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fk_id_produto", nullable = false)
    private Produto produto;

    @Column(name = "qtde_estoque", nullable = true)
    private double qntdEstoque;

    @ManyToOne
    @JoinColumn(name = "fk_id_conversao", nullable = false)
    private Conversoes conversoes;

    @Column(name = "local", nullable = true)
    private String local;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant dataCriacao;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant dataAtualizado;

    public Estoque() {
    }

    public Estoque(Long id, Produto produto, Conversoes conversoes, double qntdEstoque, String local, Instant dataCriacao, Instant dataAtualizado) {
        this.id = id;
        this.produto = produto;
        this.qntdEstoque = qntdEstoque;
        this.conversoes = conversoes;
        this.local = local;
        this.dataCriacao = dataCriacao;
        this.dataAtualizado = dataAtualizado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getQntdEstoque() {
        return qntdEstoque;
    }

    public void setQntdEstoque(double qntdEstoque) {
        this.qntdEstoque = qntdEstoque;
    }

    public Conversoes getConversoes() {
        return conversoes;
    }

    public void setConversoes(Conversoes conversoes) {
        this.conversoes = conversoes;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Instant dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Instant getDataAtualizado() {
        return dataAtualizado;
    }

    public void setDataAtualizado(Instant dataAtualizado) {
        this.dataAtualizado = dataAtualizado;
    }

    @Override
    public String toString() {
        return "Estoque{" +
                "id=" + id +
                ", produto=" + produto +
                ", qntdEstoque=" + qntdEstoque +
                ", conversoes=" + conversoes +
                ", local='" + local + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", dataAtualizado=" + dataAtualizado +
                '}';
    }
}