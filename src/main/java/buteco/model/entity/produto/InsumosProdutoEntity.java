package buteco.model.entity.produto;

import jakarta.persistence.*;

@Entity
@Table(name = "insumos_produtos")
public class InsumosProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_id_produto")
    private ProdutoEntity produtoEntity; //referencia do mappedBy na class Produto

    @ManyToOne
    @JoinColumn(name = "fk_insumos_produto")
    private ProdutoEntity insumo;

    @Column(name = "qtde")
    private double qtde;

    public InsumosProdutoEntity(){

    };
    public InsumosProdutoEntity(ProdutoEntity produtoEntity, ProdutoEntity insumo, double qtde) {
        this.produtoEntity = produtoEntity;
        this.insumo = insumo;
        this.qtde = qtde;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProdutoEntity getProduto() {
        return produtoEntity;
    }

    public void setProduto(ProdutoEntity produtoEntity) {
        this.produtoEntity = produtoEntity;
    }

    public ProdutoEntity getInsumo() {
        return insumo;
    }

    public void setInsumo(ProdutoEntity insumo) {
        this.insumo = insumo;
    }

    public double getQtde() {
        return qtde;
    }

    public void setQtde(double qtde) {
        this.qtde = qtde;
    }
}
