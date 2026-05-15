package buteco.model.pessoa;
import jakarta.persistence.*;

import jakarta.persistence.Entity;

@Entity
@Table(name = "cargos")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cargo")
    private String nome;

    public Cargo() {}

    public Cargo(Long id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public Long getId() { return id; }

    public String getNome() { return nome; }


}
