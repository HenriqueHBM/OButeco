package buteco.model.pessoa;
import jakarta.persistence.*;

import jakarta.persistence.Entity;

@Entity
@Table(name = "cargos")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @ManyToOne
    @Column(name = "fk_cargos_id")
    private Cargo cargo;

    public Cargo(Long id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public Long getId() { return id; }

    public String getNome() { return nome; }


}
