    package buteco.model.entity.estoque;

    import buteco.model.entity.conversao.ConversoesEntity;
    import buteco.model.entity.produto.Produto;
    import jakarta.persistence.*;
    import org.hibernate.annotations.CreationTimestamp;
    import org.hibernate.annotations.UpdateTimestamp;

    import java.time.Instant;

    @Entity
    @Table(name = "estoques")
    public class EstoqueEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "fk_id_produto", nullable = false)
        private Produto produto;

        @Column(name = "qtde_estoque", nullable = true)
        private double qntdEstoque;

        @ManyToOne
        @JoinColumn(name = "fk_id_conversao", nullable = false)
        private ConversoesEntity conversoesEntity;

        @Column(name = "local", nullable = true)
        private String local;

        @CreationTimestamp
        @Column(name = "created_at")
        private Instant dataCriacao;

        @UpdateTimestamp
        @Column(name = "updated_at")
        private Instant dataAtualizado;

        public EstoqueEntity() {
        }

        public EstoqueEntity(Long id, Produto produto, ConversoesEntity conversoesEntity, double qntdEstoque, String local, Instant dataCriacao, Instant dataAtualizado) {
            this.id = id;
            this.produto = produto;
            this.qntdEstoque = qntdEstoque;
            this.conversoesEntity = conversoesEntity;
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

        public ConversoesEntity getConversoes() {
            return conversoesEntity;
        }

        public void setConversoes(ConversoesEntity conversoesEntity) {
            this.conversoesEntity = conversoesEntity;
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
                    ", conversoes=" + conversoesEntity +
                    ", local='" + local + '\'' +
                    ", dataCriacao=" + dataCriacao +
                    ", dataAtualizado=" + dataAtualizado +
                    '}';
        }
    }