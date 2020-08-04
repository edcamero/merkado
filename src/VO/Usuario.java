package VO;

public class Usuario {

    private String usuario, password;
    private int idTipoUsuario;
    private TipoUsuario tipoUsuario;

    public Usuario() {
    }

    public Usuario(String usuario, String password,TipoUsuario tipoUsuario) {
        this.usuario = usuario;
        this.password = password;
        this.tipoUsuario=tipoUsuario;
        this.idTipoUsuario=this.tipoUsuario.getId();
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    

}
