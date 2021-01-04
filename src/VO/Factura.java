package VO;

import java.util.ArrayList;
import java.util.Date;

public class Factura {

    private int id;
    private int totalFactura;
    private Date fecha;
    private Cliente cliente;
    private Empleado empleado;
    private ArrayList<FacturaProducto> detalles;

    public Factura() {
        this.detalles = new ArrayList<>();
        this.totalFactura = 0;
    }

    public Factura(Date fecha, Cliente cliente, Empleado empleado, ArrayList<FacturaProducto> detalles) {
        this.id = id;
        this.fecha = fecha;
        this.cliente = cliente;
        this.empleado = empleado;
        this.detalles = detalles;
    }

    public Factura(int id, Date fecha, Cliente cliente, Empleado empleado, ArrayList<FacturaProducto> detalles) {
        this.id = id;
        this.fecha = fecha;
        this.cliente = cliente;
        this.empleado = empleado;
        this.detalles = detalles;
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

    public ArrayList<FacturaProducto> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<FacturaProducto> detalles) {
        this.detalles = detalles;
    }

    public boolean AgregarProducto(Producto producto, int cantidad) {
        FacturaProducto facturaPro = new FacturaProducto(producto, cantidad);
        boolean existe = this.detalles.contains(facturaPro);
        if (!existe) {
            this.detalles.add(facturaPro);
            this.totalFactura += facturaPro.getTotal();
        } else {
            for (FacturaProducto detalle : this.detalles) {
                if (detalle.equals(facturaPro)) {
                    detalle.addCantidad(cantidad);
                    this.totalFactura += facturaPro.getTotal();
                }
            }
        }
        return true;
    }
}
