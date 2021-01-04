package VO;

import java.util.Objects;

public class FacturaProducto {

    private int id;
    private Producto producto;
    private int cantidad;
    private int total;

    public FacturaProducto() {
    }

    public FacturaProducto(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.total = producto.getPrecioVenta() * cantidad;
    }

    public FacturaProducto(Producto producto, int cantidad, int total) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.total = total;
    }

    public FacturaProducto(int id, Producto producto, int cantidad, int total) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void addCantidad(int cantidad) {
        this.cantidad += cantidad;
        this.total = producto.getPrecioVenta() * this.cantidad;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
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
        final FacturaProducto other = (FacturaProducto) obj;
        if (!Objects.equals(this.producto, other.producto)) {
            return false;
        }
        return true;
    }

}
