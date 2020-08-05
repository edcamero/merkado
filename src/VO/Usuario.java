package VO;

public class Usuario {

    private String usuario, password;
    private int idTipoUsuario,id;
    private TipoUsuario tipoUsuario;

    public Usuario() {
    }

    public Usuario(String usuario, String password,TipoUsuario tipoUsuario) {
        this.usuario = usuario;
        this.password = password;
        this.tipoUsuario=tipoUsuario;
        this.idTipoUsuario=this.tipoUsuario.getId();
    }

    public Usuario(int id,String usuario, TipoUsuario tipoUsuario) {
        this.usuario = usuario;
        this.tipoUsuario=tipoUsuario;
        this.idTipoUsuario=this.tipoUsuario.getId();
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    

}
