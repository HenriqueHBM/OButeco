package buteco.model.entity.produto;

import jakarta.persistence.*;

@Entity
@Table(name = "grupos")
public class GrupoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String grupo;

    public GrupoEntity(){

    }

    public GrupoEntity(Long id, String grupo){
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
        return grupo;
    }
}
