package buteco.model.entity.pessoa;


import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

@Entity
@Table (name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "usuario")
    private String login;

    @Column(name = "senha")
    private String senha;

    @ManyToOne
    @JoinColumn(name = "fk_id_cargo")
    private Cargo cargo;

    public Usuario() {}

    public Usuario(Long id, String nome, String login, String senha, Cargo cargo) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.cargo = cargo;
    }


    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getLogin() { return login; }

    public void setLogin(String login) { this.login = login; }

    public String getSenha() { return senha; }

    public void setSenha(String senha) { this.senha = senha; }

    public Cargo getCargo() { return cargo; }

    public void setCargo(Cargo cargo) { this.cargo = cargo; }

}
