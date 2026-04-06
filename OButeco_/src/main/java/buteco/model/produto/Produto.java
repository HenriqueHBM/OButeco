package buteco.model.produto;
import buteco.enums.EStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produtos") //  entidade com nome padrão, tabela mapeada separadamente
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto incremental
    private Long id; //primary key da tabela

    @Column(name = "nome") //coloquei de exemplo, mas assim que identifica as colunas da tabela
    private String nome;

    @Column(name = "status")
    @Enumerated(EnumType.STRING) //dizendo que essa coluna é enum type
    @ColumnDefault("'ATIVO'") // por padrao cria como Ativo
    private EStatus status = EStatus.ATIVO; //setando ja o valor

    @ManyToOne
    @JoinColumn(name = "fk_id_categoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "fk_id_grupos")
    private Grupo grupo;

    //um prod tem varios insumos | mapped by essa relacao nao e a dona, quem manda é o produto | cascade tudo que fizer com o produto, faco com os insumos
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<InsumosProduto> insumos = new ArrayList<>();


    @CreationTimestamp //data de criacao do produto
    @Column(name = "created_at", nullable = true)
    private Instant dataCriacao; //instant se traduzir para modo literal, instante atual

    @UpdateTimestamp //data de atualizacao do produto
    @Column(name = "updated_at", nullable = true)
    private Instant dataAtualizado;

    @Column(name = "preco_venda")
    private double precoVenda;

    @Column(name = "observacao", nullable = true)
    private String observacao;

    public Produto(){

    }

    public Produto(Long id, String nome, Categoria categoria, double precoVenda, Grupo grupo) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.precoVenda = precoVenda;
        this.grupo = grupo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public List<InsumosProduto> getInsumos(){return insumos;}

    public void setInsumos(List<InsumosProduto> insumos) {
        this.insumos = insumos;
    }

    public Grupo getGrupo() { return grupo; }

    public void setGrupo(Grupo grupo) { this.grupo = grupo; }

    @Override
    public String toString(){
        return "Produto{" +
                "id="+id+
                ", nome="+nome+
                ", categoria="+categoria.getCategoria()+
                ", preco_venda="+precoVenda+
//                "conversao="+conversao.get
        "}";
    }
}
