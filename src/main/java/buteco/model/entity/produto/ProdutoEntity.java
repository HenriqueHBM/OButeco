package buteco.model.entity.produto;
import buteco.model.enums.EStatus;
import buteco.model.entity.estoque.EstoqueEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produtos") //  entidade com nome padrão, tabela mapeada separadamente
public class ProdutoEntity {

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
    private CategoriaEntity categoriaEntity;

    @ManyToOne
    @JoinColumn(name = "fk_id_grupo")
    private GrupoEntity grupoEntity;

    //um prod tem varios insumos | mapped by essa relacao nao e a dona, quem manda é o produto | cascade tudo que fizer com o produto, faco com os insumos
    @OneToMany(mappedBy = "produtoEntity", cascade = CascadeType.ALL)
    private List<InsumosProdutoEntity> insumos = new ArrayList<>();


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

    @OneToMany(mappedBy = "produtoEntity", cascade = CascadeType.ALL)
    private List<EstoqueEntity> estoqueEntities = new ArrayList<>();

//    @ManyToMany
//    @JoinTable(
//            name = "insumos_produtos",
//            joinColumns = @JoinColumn(name = "fk_id_produto"),
//            inverseJoinColumns = @JoinColumn(name = "fk_insumos_produto")
//    )
//    private List<Produto> ingredientesProdutos;


    public ProdutoEntity(){

    }

    public ProdutoEntity(Long id, String nome, CategoriaEntity categoriaEntity, double precoVenda, GrupoEntity grupoEntity) {
        this.id = id;
        this.nome = nome;
        this.categoriaEntity = categoriaEntity;
        this.precoVenda = precoVenda;
        this.grupoEntity = grupoEntity;
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

    public CategoriaEntity getCategoria() {
        return categoriaEntity;
    }

    public void setCategoria(CategoriaEntity categoriaEntity) {
        this.categoriaEntity = categoriaEntity;
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

    public List<InsumosProdutoEntity> getInsumos(){return insumos;}

    public void setInsumos(List<InsumosProdutoEntity> insumos) {
        this.insumos = insumos;
    }

    public GrupoEntity getGrupo() { return grupoEntity; }

    public void setGrupo(GrupoEntity grupoEntity) { this.grupoEntity = grupoEntity; }

    public List<EstoqueEntity> getEstoques() {
        return estoqueEntities;
    }

    public void setEstoques(List<EstoqueEntity> estoqueEntities) {
        this.estoqueEntities = estoqueEntities;
    }

    @Override
    public String toString(){
        return "Produto{" +
                "id="+id+
                ", nome="+nome+
                ", categoria="+ categoriaEntity.getCategoria()+
                ", preco_venda="+precoVenda+
//                "conversao="+conversao.get
        "}";
    }
}
