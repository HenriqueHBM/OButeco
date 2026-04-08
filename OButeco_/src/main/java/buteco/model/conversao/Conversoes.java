package buteco.model.conversao;
import jakarta.persistence.*;
@Entity
@Table(name="conversoes")
public class Conversoes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "conversao")
    private String nome;

    @Column(name = "nomenclatura")
    private String nomenclatura;

    public Conversoes(){

    }

    public Conversoes(Long id, String nome, String nomenclatura){
        this.id = id;
        this.nome = nome;
        this.nomenclatura = nomenclatura;
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

    public String getNomenclatura() {
        return nomenclatura;
    }

    public void setNomenclatura(String nomenclatura) {
        this.nomenclatura = nomenclatura;
    }

    @Override
    public String toString(){
        return "Conversoes={" +
                "id=" + id+
                ", nome"+nome+
                ", nomenclatura"+nomenclatura+
            "}";
    }
}
