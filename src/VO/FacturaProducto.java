package VO;

import java.util.Objects;
import javax.swing.JOptionPane;

public class FacturaProducto {

    private int id;
    private int id_factura;
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

    public FacturaProducto(int id_factura, Producto producto, int cantidad, int total) {
        this.id_factura = id_factura;
        this.producto = producto;
        this.cantidad = cantidad;
        this.total = total;
    }

    public FacturaProducto(int id, int id_factura, Producto producto, int cantidad, int total) {
        this.id = id;
        this.id_factura = id_factura;
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

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
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
        this.total = producto.getPrecioVenta() * this.cantidad;
    }

    public void addCantidad(int cantidad) {
        this.cantidad += cantidad;
        this.total = producto.getPrecioVenta() * this.cantidad;
    }

    public boolean removeCantidad(int cantidad) {
        if (this.cantidad - cantidad >= 0) {
            this.cantidad -= cantidad;
            this.total = producto.getPrecioVenta() * this.cantidad;
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "LA CANTIDAD A ELIMINAR NO ES VALIDA");
            return false;
        }
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
