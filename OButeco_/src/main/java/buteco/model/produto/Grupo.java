package buteco.model.produto;

import jakarta.persistence.*;

@Entity
@Table(name = "grupos")
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String grupo;

    public Grupo(){

    }

    public Grupo(Long id, String grupo){
        this.id = id;
        this.grupo = grupo;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) { this.grupo = grupo; }

    @Override
    public String toString(){
        return "[" + id + "] - " + grupo;
    }
}
