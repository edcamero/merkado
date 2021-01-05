package GUI;

import VO.Producto;
import VO.TipoProducto;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import merka.Fachada;

public class BuscarProducto extends javax.swing.JFrame {

    private DefaultTableModel model;
    TableRowSorter<DefaultTableModel> tr;
    private ArrayList<Producto> productos;
    private Producto producto;
    JTextField codigo, nombre, precio, cantidad, subTotal;
    Ventas ventas;

    public BuscarProducto(Producto p, JTextField c, JTextField n, JTextField prec, JTextField cant, JTextField subT) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.cargar();
        producto = p;
        codigo = c;
        nombre = n;
        precio = prec;
        cantidad = cant;
        subTotal = subT;
        tablaProductos.setDefaultEditor(Object.class, null);

    }

    public void cargar() {
        // Creamos un objeto Table con el molde del nuestro
        productos = Fachada.getInstancia().obtenerProductos();
        String data[][] = {};
        String col[] = {"ID", "CODIGO", "NOMBRE", "PRECIO/COMPRA", "PRECIO/VENTA", "DESCRIPCION", "TIPO/PRODUCTO"};
        model = new DefaultTableModel(data, col);
        ///alumno d = cab;
        //************************************
        if (productos.size() != 0) {
            for (Producto producto : productos) {
                Object[] fila = new Object[7];
                fila[0] = producto.getId();
                fila[1] = producto.getCodigo();
                fila[2] = producto.getNombre();
                fila[3] = producto.getPrecioCompra();
                fila[4] = producto.getPrecioVenta();
                fila[5] = producto.getDescripcion();
                fila[6] = producto.getTipoProducto();
                model.addRow(fila);
            }
            this.tablaProductos.setModel(model);
        }
        this.tablaProductos.setModel(model);
        tablaProductos.setDefaultEditor(Object.class, null);
    }

    private void filtro() {
        model = (DefaultTableModel) tablaProductos.getModel();
        tr = new TableRowSorter<>(model);
        tablaProductos.setRowSorter(tr);
        if (boxBuscar.getSelectedItem().toString().equals("Nombre")) {
            tr.setRowFilter(RowFilter.regexFilter(txtBuscar.getText(), 2)); //busca por nombre
        } else {
            tr.setRowFilter(RowFilter.regexFilter(txtBuscar.getText(), 0)); //busca por codigo
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        boxBuscar = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductosMouseClicked(evt);
            }
        });
        tablaProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaProductosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaProductos);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar"));

        jLabel1.setText("Buscar:");

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        jLabel2.setText("Por:");

        boxBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Codigo" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boxBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(boxBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        filtro();
        String c = boxBuscar.getSelectedItem().toString();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void tablaProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosMouseClicked
        // guardar el producto seleccionado
        if (productos.size() > 0) {
            JTable source = (JTable) evt.getSource();
            int prod_id = Integer.parseInt(source.getValueAt(source.getSelectedRow(), 0).toString());
            String code = source.getValueAt(source.getSelectedRow(), 1).toString();
            String nombre = source.getValueAt(source.getSelectedRow(), 2).toString();
            int precioCompra = (int) source.getValueAt(source.getSelectedRow(), 3);
            int precioVenta = (int) source.getValueAt(source.getSelectedRow(), 4);
            String descripcion = source.getValueAt(source.getSelectedRow(), 5).toString();
            int tipo = (int) source.getValueAt(source.getSelectedRow(), 6);

            producto.setId(prod_id);
            producto.setCodigo(code);
            producto.setNombre(nombre);
            producto.setPrecioCompra(precioCompra);
            producto.setPrecioVenta(precioVenta);
            producto.setCantidad(1);
            producto.setDescripcion(descripcion);
            producto.setTipoProducto(tipo);

            this.codigo.setText(code);
            this.nombre.setText(nombre);
            this.precio.setText(String.valueOf(precioVenta));
            this.cantidad.setText(String.valueOf(1));
            this.subTotal.setText(String.valueOf(precioVenta));
        }
    }//GEN-LAST:event_tablaProductosMouseClicked

    private void tablaProductosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaProductosKeyPressed
        //EVENTO ENTER
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            dispose();
        }
    }//GEN-LAST:event_tablaProductosKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {

            javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(tipo_producto.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new BuscarProducto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaProductos;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
