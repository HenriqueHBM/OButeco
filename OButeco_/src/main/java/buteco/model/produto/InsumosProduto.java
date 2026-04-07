package buteco.model.produto;

import jakarta.persistence.*;

@Entity
@Table(name = "insumos_produtos")
public class InsumosProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_id_produto")
    private Produto produto; //referencia do mappedBy na class Produto

    @ManyToOne
    @JoinColumn(name = "fk_insumo_produto")
    private Produto insumo;

    @Column(name = "qtde")
    private double qtde;

    public InsumosProduto(){

    };
    public InsumosProduto(Produto produto, Produto insumo, double qtde) {
        this.produto = produto;
        this.insumo = insumo;
        this.qtde = qtde;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Produto getInsumo() {
        return insumo;
    }

    public void setInsumo(Produto insumo) {
        this.insumo = insumo;
    }

    public double getQtde() {
        return qtde;
    }

    public void setQtde(double qtde) {
        this.qtde = qtde;
    }
}
