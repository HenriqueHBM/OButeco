package buteco.model.pessoa;

public class Usuario  extends Pessoa{
    private String usuario;
    private String senha;
    private boolean status_login;
    private int celular;

    public Usuario(String usuario, String senha, boolean status_login, int celular) {
        this.usuario = usuario;
        this.senha = senha;
        this.status_login = status_login;
        this.celular = celular;
    }

    public String getUsuario() { return usuario; }

    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getSenha() { return senha; }

    public void setSenha(String senha) { this.senha = senha; }

    public boolean isStatus_login() { return status_login; }

    public void setStatus_login(boolean status_login) { this.status_login = status_login; }

    public int getCelular() { return celular; }

    public void setCelular(int celular) { this.celular = celular; }
}
