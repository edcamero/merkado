package VO;

import java.util.ArrayList;
import java.util.Date;
import merka.Fachada;

public class Factura {

    private int id;
    private int totalFactura;
    private Date fecha;
    private Cliente cliente;
    private Empleado empleado;
    private String estado;
    private ArrayList<FacturaProducto> detalles;

    public Factura() {
        this.detalles = new ArrayList<>();
        this.totalFactura = 0;
        this.estado = "PENDIENTE";
    }

    public Factura(Date fecha, Cliente cliente, Empleado empleado, ArrayList<FacturaProducto> detalles, String estado) {
        this.id = id;
        this.fecha = fecha;
        this.cliente = cliente;
        this.empleado = empleado;
        this.detalles = detalles;
        this.estado = estado;
    }

    public Factura(int id, Date fecha, Cliente cliente, Empleado empleado, ArrayList<FacturaProducto> detalles, String estado) {
        this.id = id;
        this.fecha = fecha;
        this.cliente = cliente;
        this.empleado = empleado;
        this.detalles = detalles;
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public int getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(int totalFactura) {
        this.totalFactura = totalFactura;
    }

    public ArrayList<FacturaProducto> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<FacturaProducto> detalles) {
        this.detalles = detalles;
    }

    //agregar productos a la factura
    public boolean AgregarProducto(FacturaProducto fp) {
        boolean existe = this.detalles.contains(fp);
        if (!existe) {
            this.detalles.add(fp);
            this.totalFactura += fp.getTotal();
            Fachada.getInstancia().registrarFacturaProducto(fp);
            return true;
        } else {
            for (FacturaProducto detalle : this.detalles) {
                if (detalle.equals(fp)) {
                    detalle.addCantidad(fp.getCantidad());
                    this.totalFactura += fp.getTotal();
                    return true;
                }
            }
        }
        return false;
    }

    //eliminar productos de la factura
    public boolean EliminarProducto(Producto producto, int cantidad) {
        FacturaProducto facturaPro = new FacturaProducto(producto, cantidad);
        boolean existe = this.detalles.contains(facturaPro);
        if (existe) {
            for (FacturaProducto detalle : this.detalles) {
                if (detalle.equals(facturaPro)) {
                    if (detalle.removeCantidad(cantidad)) {
                        this.totalFactura -= facturaPro.getTotal();
                        if (detalle.getCantidad() == 0) {
                            detalles.remove(detalle);
                        }
                        return true;
                    }
                }
            }
        } else {
            return false;
        }
        return false;
    }

}
