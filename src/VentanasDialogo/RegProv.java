//GODWITHME
package VentanasDialogo;

import Class.*;
import JPanels.RegProd;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class RegProv extends javax.swing.JDialog {

    
    /* Declaramos la variable para hacer conexion a la DB */
    conectar c = new conectar();
    Connection con = c.conexion();
    
    General gn = new General();
    validacion val = new validacion();
    
    JComboBox cb_proveedores;
    
    //DefaultTableModel model;
    DefaultTableModel model = new DefaultTableModel(){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
    
    public RegProv(java.awt.Frame parent, boolean modal, JComboBox cbo) 
    {
        super(parent, modal);
        initComponents();
        
        this.cb_proveedores = cbo;
        this.setLocationRelativeTo(null);  //hacemos que la ventana se posicione en medio de la pantalla        
        estilo_tb();
        consultar();
    }
    
    //ponemos para q se coloree CLARO el borde y el frente del boton
    void setEncima(JLabel lbl)
    {
        //ponemos borde al panel
        lbl.setBorder(BorderFactory.createLineBorder(new Color(255,51,0)));
        //ponemos color oscuro a la letra
        lbl.setForeground(new Color(255,51,0));
    }
    
    //ponemos para q se coloree CLARO el borde y el frente del boton
    void setAfuera(JLabel lbl)
    {
        //QUITAMOS borde al panel
        lbl.setBorder(null);
        //coloreamos naranja CLARO al label
        lbl.setForeground(new Color(255,153,51));
    }
    
    //damos colores (estilo) a la tabla
    void estilo_tb()
    {
        //fondo
        jScrollPane1.getViewport().setBackground(Color.BLACK);
        //encabezado
        JTableHeader t = tbProv.getTableHeader();
        t.setBackground(Color.black);
        t.setForeground(new Color(255,51,0));  //color naranja osucro
    }
    
    //registramos al proveedor 
    void registrar()
    {
        String prov = txtproveedor.getText();
        
        //Procedimiento para insertar datos en la tabla proveedores
        String consulta_sql = "insert into proveedores (nombre) values (?)";
        //sql = "INSERT INTO clientes (Id_cli,nom_cli,ape_cli,tel_cli,dir_cli,email_cli,rfc_cli,cel_cli,ciu_cli,est_cli,col_cli,cp_cli) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try 
        {
            PreparedStatement psd = con.prepareStatement(consulta_sql);
            psd.setString(1,prov);

            int grd = psd.executeUpdate();
            
            //Si se guardo con exito entramos al if
            if(grd>0)
            {
                //JOptionPane.showMessageDialog(null, "Cliente " +  nombres +  " guardado con éxito", "Registro Completado", JOptionPane.INFORMATION_MESSAGE);
            }
            
        } 
        catch (SQLException ex) {
            //Logger.getLogger(JIFR_Servicios.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        }
    }
    
    void consultar()
    { 
        String registros[] = new String [1];    //declaramos el arreglo donde se guardaran los registros
        //Declaramos los encabezados que tendra la tabla en un arreglo
        String encabezado[] =  {"Proveedor"};    
        model = new DefaultTableModel(null, encabezado);   //Metemos los encabezados en el modelo de tabla
        try 
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from proveedores");
            
            while(rs.next())
            {
                //guardamos los registros en nuestro arreglo
                registros[0] = rs.getString("nombre");  //decimos que se obtenga el dato del atributo clave_serv
                model.addRow(registros);    //añadimos nuestro arreglo al modelo de tabla 
            }
            tbProv.setModel(model);    //le añadimos a NUESTRA TABLA todo el modelo anterior
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        } 
    }
    
    //quitamos el registro seleccionado de la tabla proveedores
    void quitar(String proveedor)
    {
        try 
        {
            PreparedStatement psd = con.prepareStatement("DELETE FROM proveedores WHERE nombre='"+ proveedor +"'");
            int grd = psd.executeUpdate();  //ejecutamos la consulta
            
            if( grd > 0)    //si se completo la eliminacion entra al if
            {
                //JOptionPane.showMessageDialog(null, "Proveedor " + proveedor +  " eliminado con éxito", "Registro Eliminado", JOptionPane.ERROR_MESSAGE);
            }
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        }
    }
    
    //obtenemos el proveedor de la fila seleccionado en la tabla
    String obt_fila()
    {   
        String proveedor = "";
        int fila = tbProv.getSelectedRow();    //Guardamos la fila que se esta seleccionando (de la tabla) en una variable
        if (fila >= 0)  //si fila es igual o mayor que 0 quiere decir que una de las filas son seleccionadas
        {
           //obtenemos el texto de cada una de las columnas y las escibimos en cada campo de texto
           proveedor = tbProv.getValueAt(fila, 0).toString();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Seleccione proveedor de la tabla", "Proveedor no seleccionado", JOptionPane.ERROR_MESSAGE);            
        }
        return proveedor;
    }

    //consultamos si el proveedor que se quiere ingresar no esta almacenado en la base de datos
    boolean cons_repetido()    
    {
        boolean repetido = false;
        try 
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from proveedores");
            
            while(rs.next())
            {
                String prov_bd = rs.getString("nombre");
                String prov_actual = txtproveedor.getText();
                if( prov_bd.equalsIgnoreCase( prov_actual ) )
                {
                    repetido = true;
                }        
            }
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        } 
        return repetido;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Background = new javax.swing.JPanel();
        btncerrar = new javax.swing.JLabel();
        Titulo = new javax.swing.JLabel();
        Separador_titulo = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbProv = new javax.swing.JTable();
        NombreProveedor = new javax.swing.JLabel();
        txtproveedor = new javax.swing.JTextField();
        SeparadorProv = new javax.swing.JSeparator();
        btnListo = new javax.swing.JLabel();
        btnQuitar = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Background.setBackground(new java.awt.Color(0, 0, 0));
        Background.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 0)));
        Background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btncerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cerrar.png"))); // NOI18N
        btncerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btncerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btncerrarMouseClicked(evt);
            }
        });
        Background.add(btncerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 5, -1, 20));

        Titulo.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        Titulo.setForeground(new java.awt.Color(255, 51, 0));
        Titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titulo.setText("Administrar Proveedores");
        Background.add(Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 460, -1));

        Separador_titulo.setBackground(new java.awt.Color(80, 80, 80));
        Separador_titulo.setForeground(new java.awt.Color(80, 80, 80));
        Background.add(Separador_titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 420, 10));

        tbProv = new javax.swing.JTable(){
            public boolean isCellEditable(int fila, int columna)
            {
                return false;
            }
        };
        tbProv.setBackground(new java.awt.Color(0, 0, 0));
        tbProv.setForeground(new java.awt.Color(255, 153, 51));
        tbProv.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbProv);

        Background.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 230, 130));

        NombreProveedor.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        NombreProveedor.setForeground(new java.awt.Color(255, 153, 51));
        NombreProveedor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NombreProveedor.setText("Nombre del proveedor:");
        Background.add(NombreProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 170, -1));

        txtproveedor.setFont(new java.awt.Font("Browallia New", 0, 20)); // NOI18N
        txtproveedor.setForeground(new java.awt.Color(255, 255, 255));
        txtproveedor.setBorder(null);
        txtproveedor.setCaretColor(new java.awt.Color(255, 255, 255));
        txtproveedor.setOpaque(false);
        txtproveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtproveedorKeyTyped(evt);
            }
        });
        Background.add(txtproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 170, -1));

        SeparadorProv.setBackground(new java.awt.Color(255, 51, 0));
        SeparadorProv.setForeground(new java.awt.Color(255, 51, 0));
        Background.add(SeparadorProv, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 133, 170, 10));

        btnListo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnListo.setForeground(new java.awt.Color(255, 153, 51));
        btnListo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Listo.png"))); // NOI18N
        btnListo.setText("   Listo");
        btnListo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnListo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnListoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnListoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnListoMouseExited(evt);
            }
        });
        Background.add(btnListo, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 200, 90, 30));

        btnQuitar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnQuitar.setForeground(new java.awt.Color(255, 153, 51));
        btnQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/borrar-usuario.png"))); // NOI18N
        btnQuitar.setText("  Quitar");
        btnQuitar.setToolTipText("Eliminar Proveedor.");
        btnQuitar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnQuitar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnQuitarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnQuitarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnQuitarMouseExited(evt);
            }
        });
        Background.add(btnQuitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, 90, 30));

        btnAgregar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 153, 51));
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/nuevo-usuario.png"))); // NOI18N
        btnAgregar.setText(" Agregar");
        btnAgregar.setToolTipText("Registrar Proveedor.");
        btnAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAgregarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAgregarMouseExited(evt);
            }
        });
        Background.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 150, 90, 30));

        getContentPane().add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 240));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btncerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncerrarMouseClicked
        this.dispose();
        //mandamos a limpiar y actualizar el cmboBox de proveedores
        gn.limpiar_cboProveedores(cb_proveedores);
        gn.cbo_proveedores(cb_proveedores);
    }//GEN-LAST:event_btncerrarMouseClicked

    private void btnAgregarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseEntered
        setEncima(btnAgregar);
    }//GEN-LAST:event_btnAgregarMouseEntered

    private void btnQuitarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuitarMouseEntered
        setEncima(btnQuitar);
    }//GEN-LAST:event_btnQuitarMouseEntered

    private void btnListoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListoMouseClicked
        this.dispose();
        //mandamos a limpiar y actualizar el cmboBox de proveedores
        gn.limpiar_cboProveedores(cb_proveedores);
        gn.cbo_proveedores(cb_proveedores);
    }//GEN-LAST:event_btnListoMouseClicked

    private void btnListoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListoMouseEntered
        setEncima(btnListo);
    }//GEN-LAST:event_btnListoMouseEntered

    private void btnAgregarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseExited
        setAfuera(btnAgregar);
    }//GEN-LAST:event_btnAgregarMouseExited

    private void btnQuitarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuitarMouseExited
        setAfuera(btnQuitar);
    }//GEN-LAST:event_btnQuitarMouseExited

    private void btnListoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListoMouseExited
        setAfuera(btnListo);
    }//GEN-LAST:event_btnListoMouseExited

    private void btnAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseClicked
        boolean repetido;
        //si HA INGRESADO nombre del proveedor...
        if(!txtproveedor.getText().isEmpty())
        {
            //llamamos al metodo de CONSULTAR PROVEEDOR REPETIDO
            //false = no esta en la BD  true = esta repetido en la BD
            repetido = cons_repetido();
            if(repetido == false) //si NO esta repetido......
            {
                registrar();
                consultar();
                txtproveedor.setText("");
            }
            else if ( repetido == true ) //si ESTA repetido...
            {
                JOptionPane.showMessageDialog(null, "El proveedor "+txtproveedor.getText()+" ya esta registrado en la base de datos", "Proveedor Incorrecto", JOptionPane.ERROR_MESSAGE);
                txtproveedor.setText("");
            }
        }
        //si NO HA INGRESADO nombre del proveedor...
        else
        {
            JOptionPane.showMessageDialog(null, "Agrege nombre del proveedor", "Proveedor Incorrecto", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarMouseClicked

    private void btnQuitarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuitarMouseClicked
        String proveedor = obt_fila(); //obtenemos el proveedor de la fila seleccionada
        quitar(proveedor);  //quitamos el proveedor de la tabla
        consultar(); //actualizamos la tabla
    }//GEN-LAST:event_btnQuitarMouseClicked

    private void txtproveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtproveedorKeyTyped
        val.longitud(evt, txtproveedor, 30);
    }//GEN-LAST:event_txtproveedorKeyTyped

    public static void main(String args[]) {
        
        /* Create and display the dialog */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RegProv dialog = new RegProv(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });*/
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    private javax.swing.JLabel NombreProveedor;
    private javax.swing.JSeparator SeparadorProv;
    private javax.swing.JSeparator Separador_titulo;
    private javax.swing.JLabel Titulo;
    private javax.swing.JLabel btnAgregar;
    private javax.swing.JLabel btnListo;
    private javax.swing.JLabel btnQuitar;
    private javax.swing.JLabel btncerrar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbProv;
    private javax.swing.JTextField txtproveedor;
    // End of variables declaration//GEN-END:variables
}
