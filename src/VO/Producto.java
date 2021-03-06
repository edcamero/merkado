package VO;

public class Producto {

    private int id;
    private String nombre;
    private String codigo;
    private int precioCompra;
    private int precioVenta;
    private int cantidad;
    private String descripcion;
    private int tipoProducto;

    public Producto() {
    }

    public Producto(String codigo, String nombre, int precioCompra, int precioVenta, int cantidad, String descripcion, int tipoProducto) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.tipoProducto = tipoProducto;
    }

    public Producto(int id, String codigo, String nombre, int precioCompra, int precioVenta, int cantidad, String descripcion, int tipoProducto) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.tipoProducto = tipoProducto;
    }

    public int getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(int tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(int precioCompra) {
        this.precioCompra = precioCompra;
    }

    public int getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(int precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

 
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Producto other = (Producto) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", codigo=" + codigo + ", precioCompra=" + precioCompra + ", precioVenta=" + precioVenta + ", cantidad=" + cantidad + ", descripcion=" + descripcion + ", tipoProducto=" + tipoProducto + '}';
    }

    
}
