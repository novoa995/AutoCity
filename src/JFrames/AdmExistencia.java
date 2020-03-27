//GODWITHME
package JFrames;

import Class.conectar;
import Class.validacion;
import ds.desktop.notify.DesktopNotify;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

public class AdmExistencia extends javax.swing.JFrame {

    boolean click_Aumentar = true, click_Disminuir = false; 
    
    /* Declaramos la variable para hacer conexion a la DB */
    conectar c = new conectar();
    Connection con = c.conexion();
    
    validacion val = new validacion();
    
    public AdmExistencia() {
        initComponents();
        txtCodigo.setEditable(true);
        txtCodigo.setBackground(new java.awt.Color(0, 0, 0));
        txtCodigo.setForeground(new java.awt.Color(255, 255, 255));
        this.setLocationRelativeTo(null); //ponemos al centro la ventana
        //hacemos que el text area salte de linea cada vez q sobrepase toda la fila
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
    }
    
    //verificamos que el codigo del producto exista para poner su infromacion en el pnl
    int verificar_codigo(String codigo)
    {
        int count = 0;
        try 
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select COUNT(*),codigo,proveedor,descripcion,Existencia,Stock_min from inventario WHERE codigo='"+codigo+"'");
            while(rs.next())
            {
                //ponemos la informacion del codigo obtenido
                count = rs.getInt("COUNT(*)");
                txtProd.setText(rs.getString("codigo"));
                txtProv.setText(rs.getString("proveedor"));
                txtDescripcion.setText(rs.getString("descripcion"));
                txtExistencia.setText(rs.getString("Existencia"));
                txtStockMin.setText(rs.getString("Stock_min"));
            }
        } 
        catch (Exception ex) 
        {
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        }
        return count;
    }
    
    /*limpiamos el pnl cuando 
    --se escriba un codigo que no se encuentre en la BD
    --se presione al btn limpiar
    */
    void limpiar_pnl()
    {
        txtProd.setText("");
        txtProv.setText("");
        txtDescripcion.setText("");
        txtExistencia.setText("");
        txtStockMin.setText("");
    }
    
    float obt_existencia()
    {
        float existencia_bd = 0;
        try 
        {
            String codigo = txtCodigo.getText(); 
            float cantidad = Float.parseFloat(txtCant.getText());
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select Existencia from inventario WHERE codigo='"+codigo+"'");
            while(rs.next())
            {
                //obtenemos la existencia de la base de datos
                existencia_bd = rs.getFloat("Existencia");
                if(click_Aumentar == true)
                {
                    existencia_bd = existencia_bd + cantidad;
                }
                else if (click_Disminuir == true)
                {
                    existencia_bd = existencia_bd - cantidad;
                }
            }
        } 
        catch (Exception ex) 
        {
            DesktopNotify.showDesktopMessage("Error", "Ha ocurrido un error al querer actualizar la existencia,"
                        + " verifique que la informacion proporcionada sea la correcta.", DesktopNotify.ERROR, 13000);  
        }
        return existencia_bd;
    }
            
    //modificamos la existenicia del registro seleccionado
    void modificar_exist(float existencia)
    {
        //obtenemos todos los datos ingresados
        String codigo;
        codigo = txtCodigo.getText(); 
        
        try 
        {
            String consulta_modificar = "";
            //consulta de modificar la existencia de cualquier producto
            consulta_modificar = "UPDATE inventario SET Existencia='"+existencia+"'"
                + " WHERE codigo = '"+ codigo +"'";

            PreparedStatement psd = con.prepareStatement( consulta_modificar );
            int grd = psd.executeUpdate();  //ejecutamos la consulta
            if(grd > 0)
            {
                float cantidad = Float.parseFloat(txtCant.getText());
                if(click_Aumentar == true)
                {
                    float exist_anterior = existencia - cantidad;
                    DesktopNotify.showDesktopMessage("Existencia Modificada", "Se ha actualizado con éxito la existencia del producto: \n Codigo: "+codigo+""
                            + "\n Cantidad: +"+cantidad+"\n Existencia Anterior: "+exist_anterior+"\n Existencia Actual: "+existencia, DesktopNotify.SUCCESS, 13000);                    

                }
                else if (click_Disminuir == true)
                {
                    float exist_anterior = existencia + cantidad;
                    DesktopNotify.showDesktopMessage("Existencia Modificada", "Se ha actualizado con éxito la existencia del producto: \n Codigo: "+codigo+""
                            + "\n Cantidad: -"+cantidad+"\n Existencia Anterior: "+exist_anterior+"\n Existencia Actual: "+existencia, DesktopNotify.SUCCESS, 13000);
                }
                txtCodigo.setText(""); //despues de actualizar limpiamos el txt de codigo
                txtCant.setText("1");   //reiniciamos la cant a 1, para que no aparezca la anterior
                //ponemos la existencia ya ACTUALIZADA en el campo de informacion si NO esta vacio (informacion)                    
                if(!txtProd.getText().isEmpty())
                {
                    txtExistencia.setText(String.valueOf(existencia));                    
                }
            }
            else if(grd == 0 )
            {
                DesktopNotify.showDesktopMessage("Error", "Ha ocurrido un error al querer actualizar la existencia,"
                        + " verifique que la informacion proporcionada sea la correcta.", DesktopNotify.ERROR, 13000);
            }
        } 
        catch (Exception ex) {
            DesktopNotify.showDesktopMessage("Error", "Ha ocurrido un error al querer actualizar la existencia,"
                        + " verifique que la informacion proporcionada sea la correcta.", DesktopNotify.ERROR, 10000);        
        }
    }
    void mostrarInformacion()
    {
        String codigo = txtCodigo.getText(); 
        //verificamos que exista el prod, para poner la informacion de este
        int prods = verificar_codigo(codigo);
        //si NO se ENCONTRO ningun producto con el codigo escrito...
        if(prods == 0)
        {
            limpiar_pnl();
            DesktopNotify.showDesktopMessage("Error", "El código del producto \""+codigo+"\" no se encuentra registrado en la base de datos.", DesktopNotify.ERROR, 10000);   
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Background = new javax.swing.JPanel();
        btncerrar = new javax.swing.JLabel();
        btnMinimizar = new javax.swing.JLabel();
        Titulo = new javax.swing.JLabel();
        Separador_titulo = new javax.swing.JSeparator();
        lblCantidad = new javax.swing.JLabel();
        lblCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtCant = new javax.swing.JTextField();
        SeparadorProv = new javax.swing.JSeparator();
        btnAum = new javax.swing.JLabel();
        btnDism = new javax.swing.JLabel();
        SeparadorProv1 = new javax.swing.JSeparator();
        SeparadorProv2 = new javax.swing.JSeparator();
        SeparadorProv3 = new javax.swing.JSeparator();
        SeparadorProv4 = new javax.swing.JSeparator();
        SeparadorProv5 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        Separador_titulo1 = new javax.swing.JSeparator();
        Titulo1 = new javax.swing.JLabel();
        pnlRegistrar = new javax.swing.JPanel();
        lblRegistrar = new javax.swing.JLabel();
        lblIconMod = new javax.swing.JLabel();
        pnlCancelar = new javax.swing.JPanel();
        lblCancelar = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Datos = new javax.swing.JPanel();
        lblProveedor = new javax.swing.JLabel();
        txtProd = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        lblCodigo1 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextArea();
        lblDescripcion = new javax.swing.JLabel();
        txtExistencia = new javax.swing.JTextField();
        lblExistencia = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        lblStockMin = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        txtStockMin = new javax.swing.JTextField();
        txtProv = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        lblCant = new javax.swing.JLabel();
        lblDisminuir = new javax.swing.JLabel();
        lblAumentar = new javax.swing.JLabel();
        btnMostrarInf = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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

        btnMinimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/puntero-hacia-abajo.png"))); // NOI18N
        btnMinimizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMinimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMinimizarMouseClicked(evt);
            }
        });
        Background.add(btnMinimizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 5, -1, 20));

        Titulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Titulo.setForeground(new java.awt.Color(255, 153, 51));
        Titulo.setText("    Información del producto");
        Background.add(Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 250, 30));

        Separador_titulo.setBackground(new java.awt.Color(80, 80, 80));
        Separador_titulo.setForeground(new java.awt.Color(80, 80, 80));
        Background.add(Separador_titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 420, 10));

        lblCantidad.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblCantidad.setForeground(new java.awt.Color(255, 153, 51));
        lblCantidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCantidad.setText("Cantidad:");
        Background.add(lblCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, 100, -1));

        lblCodigo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblCodigo.setForeground(new java.awt.Color(255, 153, 51));
        lblCodigo.setText("Codigó del producto:");
        Background.add(lblCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 150, -1));

        txtCodigo.setFont(new java.awt.Font("Browallia New", 0, 20)); // NOI18N
        txtCodigo.setForeground(new java.awt.Color(255, 255, 255));
        txtCodigo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCodigo.setBorder(null);
        txtCodigo.setCaretColor(new java.awt.Color(255, 255, 255));
        txtCodigo.setOpaque(false);
        txtCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoFocusLost(evt);
            }
        });
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });
        Background.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 139, 210, -1));

        txtCant.setFont(new java.awt.Font("Browallia New", 0, 21)); // NOI18N
        txtCant.setForeground(new java.awt.Color(255, 255, 255));
        txtCant.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCant.setText("1");
        txtCant.setBorder(null);
        txtCant.setCaretColor(new java.awt.Color(255, 255, 255));
        txtCant.setOpaque(false);
        txtCant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantActionPerformed(evt);
            }
        });
        txtCant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantKeyTyped(evt);
            }
        });
        Background.add(txtCant, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 138, 72, -1));

        SeparadorProv.setBackground(new java.awt.Color(255, 51, 0));
        SeparadorProv.setForeground(new java.awt.Color(255, 51, 0));
        Background.add(SeparadorProv, new org.netbeans.lib.awtextra.AbsoluteConstraints(405, 150, 27, -1));

        btnAum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/flecha-hacia-arriba.png"))); // NOI18N
        btnAum.setToolTipText("Aumentar cantidad.");
        btnAum.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAum.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAumMouseClicked(evt);
            }
        });
        Background.add(btnAum, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 140, -1, 10));

        btnDism.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/angulo-de-flecha-hacia-abajo.png"))); // NOI18N
        btnDism.setToolTipText("Disminuir cantidad.");
        btnDism.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDism.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDismMouseClicked(evt);
            }
        });
        Background.add(btnDism, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 152, -1, 10));

        SeparadorProv1.setBackground(new java.awt.Color(255, 51, 0));
        SeparadorProv1.setForeground(new java.awt.Color(255, 51, 0));
        Background.add(SeparadorProv1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 163, 210, 10));

        SeparadorProv2.setBackground(new java.awt.Color(255, 51, 0));
        SeparadorProv2.setForeground(new java.awt.Color(255, 51, 0));
        SeparadorProv2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        Background.add(SeparadorProv2, new org.netbeans.lib.awtextra.AbsoluteConstraints(405, 140, 10, 25));

        SeparadorProv3.setBackground(new java.awt.Color(255, 51, 0));
        SeparadorProv3.setForeground(new java.awt.Color(255, 51, 0));
        Background.add(SeparadorProv3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 163, 102, 10));

        SeparadorProv4.setBackground(new java.awt.Color(255, 51, 0));
        SeparadorProv4.setForeground(new java.awt.Color(255, 51, 0));
        Background.add(SeparadorProv4, new org.netbeans.lib.awtextra.AbsoluteConstraints(405, 138, 27, -1));

        SeparadorProv5.setBackground(new java.awt.Color(255, 51, 0));
        SeparadorProv5.setForeground(new java.awt.Color(255, 51, 0));
        SeparadorProv5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        Background.add(SeparadorProv5, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 139, 10, 25));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/codigo-de-barras.png"))); // NOI18N
        jLabel1.setToolTipText("Buscar producto");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        Background.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, -1, -1));

        Separador_titulo1.setBackground(new java.awt.Color(80, 80, 80));
        Separador_titulo1.setForeground(new java.awt.Color(80, 80, 80));
        Background.add(Separador_titulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 420, 10));

        Titulo1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        Titulo1.setForeground(new java.awt.Color(255, 51, 0));
        Titulo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titulo1.setText("Administrar Existencia");
        Background.add(Titulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 460, -1));

        pnlRegistrar.setBackground(new java.awt.Color(255, 51, 0));
        pnlRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlRegistrar.setOpaque(false);
        pnlRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlRegistrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlRegistrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlRegistrarMouseExited(evt);
            }
        });
        pnlRegistrar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblRegistrar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblRegistrar.setForeground(new java.awt.Color(255, 102, 51));
        lblRegistrar.setText("Aumentar");
        pnlRegistrar.add(lblRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 6, -1, -1));

        lblIconMod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/boton-actualizar.png"))); // NOI18N
        pnlRegistrar.add(lblIconMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 6, -1, -1));

        Background.add(pnlRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 182, 110, 35));

        pnlCancelar.setBackground(new java.awt.Color(255, 51, 0));
        pnlCancelar.setToolTipText("Cerrar ventana");
        pnlCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlCancelar.setOpaque(false);
        pnlCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlCancelarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlCancelarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlCancelarMouseExited(evt);
            }
        });
        pnlCancelar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCancelar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblCancelar.setForeground(new java.awt.Color(255, 102, 51));
        lblCancelar.setText("Cancelar");
        pnlCancelar.add(lblCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 6, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cancelar_cons.png"))); // NOI18N
        pnlCancelar.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 6, -1, -1));

        Background.add(pnlCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 182, 110, 35));

        Datos.setOpaque(false);
        Datos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblProveedor.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblProveedor.setForeground(new java.awt.Color(255, 153, 51));
        lblProveedor.setText("Proveedor:");
        Datos.add(lblProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 77, -1));

        txtProd.setEditable(false);
        txtProd.setFont(new java.awt.Font("Browallia New", 0, 20)); // NOI18N
        txtProd.setForeground(new java.awt.Color(255, 255, 255));
        txtProd.setBorder(null);
        txtProd.setCaretColor(new java.awt.Color(255, 255, 255));
        txtProd.setOpaque(false);
        txtProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProdActionPerformed(evt);
            }
        });
        Datos.add(txtProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 110, 20));

        jSeparator2.setBackground(new java.awt.Color(255, 51, 0));
        jSeparator2.setForeground(new java.awt.Color(255, 51, 0));
        Datos.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 110, 10));

        lblCodigo1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblCodigo1.setForeground(new java.awt.Color(255, 153, 51));
        lblCodigo1.setText("Código:");
        Datos.add(lblCodigo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 60, -1));

        txtDescripcion.setEditable(false);
        txtDescripcion.setColumns(20);
        txtDescripcion.setFont(new java.awt.Font("Browallia New", 0, 20)); // NOI18N
        txtDescripcion.setForeground(new java.awt.Color(255, 255, 255));
        txtDescripcion.setRows(5);
        txtDescripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 0)));
        txtDescripcion.setCaretColor(new java.awt.Color(255, 255, 255));
        txtDescripcion.setOpaque(false);
        Datos.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 210, 90));

        lblDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblDescripcion.setForeground(new java.awt.Color(255, 153, 51));
        lblDescripcion.setText("Descripción:");
        Datos.add(lblDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 90, -1));

        txtExistencia.setEditable(false);
        txtExistencia.setFont(new java.awt.Font("Browallia New", 0, 20)); // NOI18N
        txtExistencia.setForeground(new java.awt.Color(255, 255, 255));
        txtExistencia.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtExistencia.setBorder(null);
        txtExistencia.setCaretColor(new java.awt.Color(255, 255, 255));
        txtExistencia.setOpaque(false);
        txtExistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtExistenciaActionPerformed(evt);
            }
        });
        Datos.add(txtExistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, 70, 20));

        lblExistencia.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblExistencia.setForeground(new java.awt.Color(255, 153, 51));
        lblExistencia.setText("Existencia:");
        Datos.add(lblExistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 120, 70, -1));

        jSeparator3.setBackground(new java.awt.Color(255, 51, 0));
        jSeparator3.setForeground(new java.awt.Color(255, 51, 0));
        Datos.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 80, 70, 10));

        lblStockMin.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblStockMin.setForeground(new java.awt.Color(255, 153, 51));
        lblStockMin.setText("Stock mínimo:");
        Datos.add(lblStockMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 100, -1));

        jSeparator4.setBackground(new java.awt.Color(255, 51, 0));
        jSeparator4.setForeground(new java.awt.Color(255, 51, 0));
        Datos.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 140, 70, 10));

        txtStockMin.setEditable(false);
        txtStockMin.setFont(new java.awt.Font("Browallia New", 0, 20)); // NOI18N
        txtStockMin.setForeground(new java.awt.Color(255, 255, 255));
        txtStockMin.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtStockMin.setBorder(null);
        txtStockMin.setCaretColor(new java.awt.Color(255, 255, 255));
        txtStockMin.setOpaque(false);
        txtStockMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockMinActionPerformed(evt);
            }
        });
        Datos.add(txtStockMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 70, 20));

        txtProv.setEditable(false);
        txtProv.setFont(new java.awt.Font("Browallia New", 0, 20)); // NOI18N
        txtProv.setForeground(new java.awt.Color(255, 255, 255));
        txtProv.setBorder(null);
        txtProv.setCaretColor(new java.awt.Color(255, 255, 255));
        txtProv.setOpaque(false);
        txtProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProvActionPerformed(evt);
            }
        });
        Datos.add(txtProv, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 100, 20));

        jSeparator5.setBackground(new java.awt.Color(255, 51, 0));
        jSeparator5.setForeground(new java.awt.Color(255, 51, 0));
        Datos.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 100, 10));

        lblCant.setFont(new java.awt.Font("Browallia New", 0, 18)); // NOI18N
        lblCant.setForeground(new java.awt.Color(51, 255, 0));
        lblCant.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCant.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Datos.add(lblCant, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 98, 30, 20));

        Background.add(Datos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 460, 160));

        lblDisminuir.setBackground(new java.awt.Color(0, 0, 0));
        lblDisminuir.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblDisminuir.setForeground(new java.awt.Color(255, 153, 51));
        lblDisminuir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDisminuir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/menos.png"))); // NOI18N
        lblDisminuir.setText(" Disminuir");
        lblDisminuir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblDisminuir.setOpaque(true);
        lblDisminuir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDisminuirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblDisminuirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDisminuirMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblDisminuirMousePressed(evt);
            }
        });
        Background.add(lblDisminuir, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 62, 120, 28));

        lblAumentar.setBackground(new java.awt.Color(80, 80, 80));
        lblAumentar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblAumentar.setForeground(new java.awt.Color(255, 153, 51));
        lblAumentar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAumentar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/boton-de-suma.png"))); // NOI18N
        lblAumentar.setText(" Aumentar");
        lblAumentar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAumentar.setOpaque(true);
        lblAumentar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAumentarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblAumentarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblAumentarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblAumentarMousePressed(evt);
            }
        });
        Background.add(lblAumentar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 62, 120, 28));

        btnMostrarInf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/informacion.png"))); // NOI18N
        btnMostrarInf.setToolTipText("Mostrar la información del producto");
        btnMostrarInf.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMostrarInf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMostrarInfMouseClicked(evt);
            }
        });
        Background.add(btnMostrarInf, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 238, -1, -1));

        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/escoba.png"))); // NOI18N
        btnLimpiar.setToolTipText("Limpar los campos de la información del producto");
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLimpiarMouseClicked(evt);
            }
        });
        Background.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 238, -1, -1));

        getContentPane().add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 430));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtStockMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockMinActionPerformed

    }//GEN-LAST:event_txtStockMinActionPerformed

    private void txtExistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtExistenciaActionPerformed

    }//GEN-LAST:event_txtExistenciaActionPerformed

    private void txtProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProdActionPerformed

    }//GEN-LAST:event_txtProdActionPerformed

    private void btnDismMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDismMouseClicked
        //obtenemos la cantidad que esta en el txt
        int cantidad = Integer.parseInt(txtCant.getText());
        //si la cantidad es numero positivo
        if(cantidad > 1)
        {
            cantidad--; //restamos la cantidad
            txtCant.setText( String.valueOf(cantidad) );  //ponemos la cantidad en el txt
        }
    }//GEN-LAST:event_btnDismMouseClicked

    private void btnAumMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAumMouseClicked
        int cantidad = Integer.parseInt(txtCant.getText());
        cantidad++;
        txtCant.setText( String.valueOf(cantidad) );
    }//GEN-LAST:event_btnAumMouseClicked

    private void txtCantKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyTyped
        //validamos como costo para aceptar decimales
        val.long_costo(evt, txtCant, 4);
        val.val_costo(txtCant, evt);
    }//GEN-LAST:event_txtCantKeyTyped

    private void btnMinimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizarMouseClicked
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_btnMinimizarMouseClicked

    private void btncerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncerrarMouseClicked
        this.dispose();
    }//GEN-LAST:event_btncerrarMouseClicked

    private void txtProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProvActionPerformed

    private void lblAumentarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAumentarMouseEntered
        //osucurecemos(gris) el lbl
        lblAumentar.setBackground(new Color(80,80,80));
    }//GEN-LAST:event_lblAumentarMouseEntered

    private void lblAumentarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAumentarMousePressed
        //quitamos gris al boton
        lblAumentar.setBackground(Color.BLACK);
    }//GEN-LAST:event_lblAumentarMousePressed

    private void lblAumentarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAumentarMouseExited
        //si NO esta presionado el boton general...
        if(click_Aumentar == false)
        {
            //desabilitamos color
            lblAumentar.setBackground(Color.BLACK);
        }
    }//GEN-LAST:event_lblAumentarMouseExited

    private void lblAumentarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAumentarMouseClicked
        click_Aumentar = true;
        click_Disminuir = false;
        
        lblAumentar.setBackground(new Color(80,80,80));
        lblDisminuir.setBackground(Color.BLACK);
        
        lblRegistrar.setText("Aumentar");
        lblCant.setForeground(new Color(51,255,0));
    }//GEN-LAST:event_lblAumentarMouseClicked

    private void lblDisminuirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDisminuirMouseEntered
        //habilitamos el boton
        lblDisminuir.setBackground(new Color(80,80,80));
    }//GEN-LAST:event_lblDisminuirMouseEntered

    private void lblDisminuirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDisminuirMouseExited
        //si NO esta presionado el boton disminuir...
        if(click_Disminuir == false)
        {
            //desabilitamos color
            lblDisminuir.setBackground(Color.BLACK);
        }
    }//GEN-LAST:event_lblDisminuirMouseExited

    private void lblDisminuirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDisminuirMousePressed
        //desabilitamos el color
        lblDisminuir.setBackground(Color.BLACK);
    }//GEN-LAST:event_lblDisminuirMousePressed

    private void lblDisminuirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDisminuirMouseClicked
        click_Aumentar = false;
        click_Disminuir = true;
        
        lblAumentar.setBackground(Color.BLACK);
        lblDisminuir.setBackground(new Color(80,80,80));
        
        lblRegistrar.setText("Disminuir");
        lblCant.setForeground(new Color(255,0,0));
    }//GEN-LAST:event_lblDisminuirMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        //lblDisminuir.setOpaque(false);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void pnlRegistrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlRegistrarMouseClicked
        //Si los campos de texto NO ESTAN VACIOS....
        if(!txtCodigo.getText().isEmpty() && !txtCant.getText().isEmpty() && !txtCant.getText().equals("."))
        {
            //obtenemos la existencia nueva dependiendo si aumentamos o disminuimos
            float existencia_nueva = obt_existencia();
            modificar_exist(existencia_nueva);
        }
        //SI estan VACIOS....
        else
        {
            DesktopNotify.showDesktopMessage("Error", "Proporcione los datos necesarios para poder actualizar", DesktopNotify.ERROR, 10000);
        }
    }//GEN-LAST:event_pnlRegistrarMouseClicked

    private void pnlRegistrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlRegistrarMouseEntered
        //ponemos borde al panel
        pnlRegistrar.setBorder(BorderFactory.createLineBorder(new Color(255,51,0)));
        //coloreamos naranja oscuro al label
        lblRegistrar.setForeground(new Color(255,51,0));
    }//GEN-LAST:event_pnlRegistrarMouseEntered

    private void pnlRegistrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlRegistrarMouseExited
        //QUITAMOS borde al panel
        pnlRegistrar.setBorder(null);
        //coloreamos naranja CLARO al label
        lblRegistrar.setForeground(new Color(255,102,51));
    }//GEN-LAST:event_pnlRegistrarMouseExited

    private void pnlCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCancelarMouseClicked
        //cerramos la ventana de administrar exist
        dispose();
    }//GEN-LAST:event_pnlCancelarMouseClicked

    private void pnlCancelarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCancelarMouseEntered
        //ponemos borde al panel
        pnlCancelar.setBorder(BorderFactory.createLineBorder(new Color(255,51,0)));
        //coloreamos naranja oscuro al label
        lblCancelar.setForeground(new Color(255,51,0));
    }//GEN-LAST:event_pnlCancelarMouseEntered

    private void pnlCancelarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCancelarMouseExited
        ///QUITAMOS borde al panel
        pnlCancelar.setBorder(null);
        //coloreamos naranja CLARO al label
        lblCancelar.setForeground(new Color(255,102,51));
    }//GEN-LAST:event_pnlCancelarMouseExited

    private void btnLimpiarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarMouseClicked
        limpiar_pnl();
    }//GEN-LAST:event_btnLimpiarMouseClicked

    private void txtCantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoFocusLost
        //mostrarInformacion();
    }//GEN-LAST:event_txtCodigoFocusLost

    private void btnMostrarInfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMostrarInfMouseClicked
        //si el campo del codigo NO esta VACIO....
        if(!txtCodigo.getText().isEmpty())
        {
            mostrarInformacion();
        }
        else
        {
            DesktopNotify.showDesktopMessage("Error", "Proporcione un código de producto.", DesktopNotify.ERROR, 10000);   
        }
    }//GEN-LAST:event_btnMostrarInfMouseClicked


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AumExistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AumExistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AumExistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AumExistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdmExistencia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    private javax.swing.JPanel Datos;
    private javax.swing.JSeparator SeparadorProv;
    private javax.swing.JSeparator SeparadorProv1;
    private javax.swing.JSeparator SeparadorProv2;
    private javax.swing.JSeparator SeparadorProv3;
    private javax.swing.JSeparator SeparadorProv4;
    private javax.swing.JSeparator SeparadorProv5;
    private javax.swing.JSeparator Separador_titulo;
    private javax.swing.JSeparator Separador_titulo1;
    private javax.swing.JLabel Titulo;
    private javax.swing.JLabel Titulo1;
    private javax.swing.JLabel btnAum;
    private javax.swing.JLabel btnDism;
    private javax.swing.JLabel btnLimpiar;
    private javax.swing.JLabel btnMinimizar;
    private javax.swing.JLabel btnMostrarInf;
    private javax.swing.JLabel btncerrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel lblAumentar;
    private javax.swing.JLabel lblCancelar;
    private javax.swing.JLabel lblCant;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblCodigo1;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblDisminuir;
    private javax.swing.JLabel lblExistencia;
    private javax.swing.JLabel lblIconMod;
    private javax.swing.JLabel lblProveedor;
    private javax.swing.JLabel lblRegistrar;
    private javax.swing.JLabel lblStockMin;
    private javax.swing.JPanel pnlCancelar;
    private javax.swing.JPanel pnlRegistrar;
    private javax.swing.JTextField txtCant;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtExistencia;
    private javax.swing.JTextField txtProd;
    private javax.swing.JTextField txtProv;
    private javax.swing.JTextField txtStockMin;
    // End of variables declaration//GEN-END:variables
}