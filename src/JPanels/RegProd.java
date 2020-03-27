//GODWITHME

package JPanels;

import Class.*;
import JFrames.Principal;
import VentanasDialogo.RegProv;
import com.placeholder.PlaceHolder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class RegProd extends javax.swing.JPanel {

    /* Declaramos la variable para hacer conexion a la DB */
    conectar c = new conectar();
    Connection con = c.conexion();
    
    Principal pr;
    General gn = new General();
    validacion val = new validacion();
    
    //guardaremos la ruta de la foto seleccionada
    String img_selecc="";
      
    public RegProd(Principal pr) {
        initComponents();
        //igualamos el objeto de la clase principal recibido, para asi poder abrir ventanas desde esta
        this.pr = pr;
        
        //hacemos que el text area salte de linea cada vez q sobrepase toda la fila
        salto_linea();
        gn.cbo_proveedores(cbProveedores);
        //PlaceHolder h = new PlaceHolder(txtCodigo,Color.orange, Color.blue, "olis", true, "Arial", 20);
    }
    
    //mostramos el panel a la ventana principal
    //Recibimos el panel que se quiere mostrar en la ventana principal
    void mostrar_panel(JPanel p)
    {
        p.setSize(700,600);
        p.setLocation(0, 0);
        
        pr.Ventana.removeAll(); //eliminamos todos los componentes dentro del JPanel Ventana
        pr.Ventana.add(p,BorderLayout.CENTER); //Agregamos el panel recibido a la ventana princcipal (JPanel Ventana)
        pr.Ventana.revalidate();
        pr.Ventana.repaint(); //repinta (actualiza, vuelve a pintar) la Ventana principal 
    }
    
    //hacemos que el text area salte de linea cada vez q sobrepase toda la fila    
    void salto_linea()
    {
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
    }
    
    //ponemos para q se coloree CLARO el borde y el frente del boton
    void setEncima(JPanel p)
    {
        //ponemos borde al panel
        p.setBorder(BorderFactory.createLineBorder(new Color(255,51,0)));
        
        //comparar para coleraer el label de cada panel
        //si el pannel recibido es igual a btnRegistrar...
        if( p == btnRegistrar )
        {
            //coloreamos naranja oscuro al label
            Registrar.setForeground(new Color(255,51,0));
        }
        else if( p == btnCancelar )
        {
            //coloreamos naranja oscuro al label
            Cancelar.setForeground(new Color(255,51,0));
        }
    }
    
    //ponemos para q se coloree CLARO el borde y el frente del boton
    void setAfuera(JPanel p)
    {
        //QUITAMOS borde al panel
        p.setBorder(null);
        
        //comparar para coleraer el label de cada panel
        //si el pannel recibido es igual a btnRegistrar...
        if( p == btnRegistrar )
        {
            //coloreamos naranja CLARO al label
            Registrar.setForeground(new Color(255,102,51));
        }
        else if( p == btnCancelar )
        {
            //coloreamos naranja CLARO al label
            Cancelar.setForeground(new Color(255,102,51));
        }
    }
    
    //guardamos todos los COMPONENTES DE TEXTO (TextField)
    //para despues validar si NO ESTAN NULL (vacios)
    JTextField[] gdr_txt()
    {
        JTextField arr_txt[] = new JTextField[5];
        arr_txt[0] = txtCodigo;
        arr_txt[1] = txtExistencia;
        arr_txt[2] = txtStockMin;
        arr_txt[3] = txtPrecioCompra;
        arr_txt[4] = txtPrecioVenta;
        
        return arr_txt;
    }
    
    //seleccionaremos la ruta de la imagen del producto
    void seleccionar_arch()
    {
        JFileChooser arch = new JFileChooser();
        //indicamos que SOLO se pueda seleccionar ARCHIVOS
        arch.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //Idicamos que NO se pueda selccionar mas de un archivo
        arch.setMultiSelectionEnabled(false);
        //ponemos filtro para que busque con la extension que pusimos
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formato de Imagenes (.JPG, .JPEG. PNG)", "jpg","jpeg","png");
        //arch.addChoosableFileFilter(filtro);  //AÑADIMOS (sumamos) el filtro a las opciones
        arch.setFileFilter(filtro);  //ponemos como filtro PREDETERMINADO
        //Ponemos titulo a la ventana de seleccion de archivo
        arch.setDialogTitle("Seleccionar Imagen");       
        //abrimos la ventana para seleccionar archivo
        int seleccion = arch.showOpenDialog(null);
        if(seleccion == JFileChooser.APPROVE_OPTION)
        {   
            //obtenemos la ruta la cual fue seleccionada
            img_selecc = arch.getSelectedFile().getAbsolutePath();
            //mostramos la imagen en el lbl 
            mostrar_imagen(img_selecc);
            //quitamos el texto del lblFotografia
            lblFotografia.setText("");
        }    
    }
    
    //mostraremos en el lbl la imagen seleccionada
    void mostrar_imagen(String ruta_imagen)
    {
        //tomamos la imagrn desde la ruta seleccionada
        Image foto = getToolkit().getImage(ruta_imagen);
        //damos un tamaño a la imagen
        foto = foto.getScaledInstance(lblFotografia.getWidth(), lblFotografia.getHeight(), Image.SCALE_SMOOTH);
        //agregamos la imagen al lblFotografia
        lblFotografia.setIcon(new ImageIcon(foto));
    }
    
    //limpia todos los campos de registro
    void limpiar_txt()
    {
        //limpiamos todos los txt
        txtCodigo.setText("");
        txtDescripcion.setText("");
        txtExistencia.setText("");
        txtStockMin.setText("");
        txtPrecioCompra.setText("");
        txtPrecioIVA.setText("");
        txtPrecioVenta.setText("");
        
        //limpiamos el comboBox
        cbProveedores.setSelectedIndex(0);
        
        //limpiamos la fotografia
        lblFotografia.setIcon(null);
        lblFotografia.setText("Elegir Foto");
    }
       
    //registramos los datos en la BD 
    void registrar()
    {
        //obtenemos todos los datos ingresados
        String codigo,proveedor,descripcion,existencia,stock_min,precio_comp,precio_vnt;
        codigo = txtCodigo.getText();
        proveedor = cbProveedores.getSelectedItem().toString();
        descripcion = txtDescripcion.getText();
        existencia = txtExistencia.getText();
        stock_min=txtStockMin.getText();
        precio_comp=txtPrecioCompra.getText();
        precio_vnt=txtPrecioVenta.getText();
        
        String consulta_sql = "insert into inventario (codigo,proveedor,descripcion,Existencia,Stock_min,precio_compra,precio_venta,foto) values (?,?,?,?,?,?,?,?)";
        //sql = "INSERT INTO clientes (Id_cli,nom_cli,ape_cli,tel_cli,dir_cli,email_cli,rfc_cli,cel_cli,ciu_cli,est_cli,col_cli,cp_cli) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try 
        {
            PreparedStatement psd = con.prepareStatement(consulta_sql);
            psd.setString(1,codigo);
            psd.setString(2, proveedor);
            psd.setString(3, descripcion);
            psd.setString(4, existencia);
            psd.setString(5, stock_min);
            psd.setString(6, precio_comp);
            psd.setString(7, precio_vnt);     
            
            //agregamos la imagen a la base de datos
            FileInputStream foto = null;
            //si se ah seleccionado una fotografia....
            if( !img_selecc.equals("") )
            {
                //File file = new File(img_selecc);
                foto = new FileInputStream(img_selecc);
                System.out.println("hay foto");
            }
            else if( img_selecc.equals("") )
            {
                System.out.println("no hay foto");
                foto = new FileInputStream(new File ("lib/Agregar_Imagen.png").getAbsolutePath());
            }
            psd.setBinaryStream(8, foto);

            int grd = psd.executeUpdate();
            
            //Si se guardo con exito entramos...
            if(grd>0)
            {
                JOptionPane.showMessageDialog(null, "Producto guardado con éxito", "Registro Completado", JOptionPane.INFORMATION_MESSAGE);
                limpiar_txt();
                img_selecc = ""; //reiniciamos la variable de ruta de imagen por si quiere volver a registar denuevo
            }            
        } 
        catch (Exception ex) {
            //Logger.getLogger(JIFR_Servicios.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Titulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        DatosRegistro = new javax.swing.JPanel();
        Codigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lblFotografia = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        Codigo1 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextArea();
        Codigo2 = new javax.swing.JLabel();
        cbProveedores = new javax.swing.JComboBox<>();
        txtExistencia = new javax.swing.JTextField();
        Codigo3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        Codigo4 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        txtStockMin = new javax.swing.JTextField();
        Codigo5 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        txtPrecioCompra = new javax.swing.JTextField();
        txtPrecioIVA = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        Codigo6 = new javax.swing.JLabel();
        txtPrecioVenta = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        Codigo7 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JPanel();
        Cancelar = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JPanel();
        Registrar = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setOpaque(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Titulo.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        Titulo.setForeground(new java.awt.Color(255, 51, 0));
        Titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titulo.setText("Registro de Producto");
        add(Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 700, 40));

        jSeparator1.setBackground(new java.awt.Color(80, 80, 80));
        jSeparator1.setForeground(new java.awt.Color(80, 80, 80));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 620, 10));

        DatosRegistro.setOpaque(false);
        DatosRegistro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Codigo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Codigo.setForeground(new java.awt.Color(255, 153, 51));
        Codigo.setText("Proveedor:");
        DatosRegistro.add(Codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 32, 77, -1));

        txtCodigo.setFont(new java.awt.Font("Browallia New", 0, 20)); // NOI18N
        txtCodigo.setForeground(new java.awt.Color(255, 255, 255));
        txtCodigo.setBorder(null);
        txtCodigo.setCaretColor(new java.awt.Color(255, 255, 255));
        txtCodigo.setOpaque(false);
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
        DatosRegistro.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 130, 20));

        lblFotografia.setBackground(new java.awt.Color(51, 51, 51));
        lblFotografia.setForeground(new java.awt.Color(0, 102, 204));
        lblFotografia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFotografia.setText("Elegir Foto");
        lblFotografia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        lblFotografia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblFotografia.setOpaque(true);
        lblFotografia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFotografiaMouseClicked(evt);
            }
        });
        DatosRegistro.add(lblFotografia, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, 160, 170));

        jSeparator2.setBackground(new java.awt.Color(255, 51, 0));
        jSeparator2.setForeground(new java.awt.Color(255, 51, 0));
        DatosRegistro.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 130, 10));

        Codigo1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Codigo1.setForeground(new java.awt.Color(255, 153, 51));
        Codigo1.setText("Código:");
        DatosRegistro.add(Codigo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 60, -1));

        txtDescripcion.setColumns(20);
        txtDescripcion.setFont(new java.awt.Font("Browallia New", 0, 20)); // NOI18N
        txtDescripcion.setForeground(new java.awt.Color(255, 255, 255));
        txtDescripcion.setRows(5);
        txtDescripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 0)));
        txtDescripcion.setCaretColor(new java.awt.Color(255, 255, 255));
        txtDescripcion.setOpaque(false);
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyTyped(evt);
            }
        });
        DatosRegistro.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 260, 90));

        Codigo2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Codigo2.setForeground(new java.awt.Color(255, 153, 51));
        Codigo2.setText("Descripción:");
        DatosRegistro.add(Codigo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 90, -1));

        cbProveedores.setBackground(new java.awt.Color(0, 0, 0));
        cbProveedores.setFont(new java.awt.Font("Browallia New", 0, 20)); // NOI18N
        cbProveedores.setForeground(new java.awt.Color(255, 255, 255));
        cbProveedores.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione opcion" }));
        cbProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DatosRegistro.add(cbProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, 120, -1));

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
        txtExistencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtExistenciaKeyTyped(evt);
            }
        });
        DatosRegistro.add(txtExistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, 80, 20));

        Codigo3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Codigo3.setForeground(new java.awt.Color(255, 153, 51));
        Codigo3.setText("Existencia:");
        DatosRegistro.add(Codigo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 70, -1));

        jSeparator3.setBackground(new java.awt.Color(255, 51, 0));
        jSeparator3.setForeground(new java.awt.Color(255, 51, 0));
        DatosRegistro.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 120, 80, 10));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/AjusteProv.png"))); // NOI18N
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        DatosRegistro.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 32, -1, 25));

        Codigo4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Codigo4.setForeground(new java.awt.Color(255, 153, 51));
        Codigo4.setText("Stock mínimo:");
        DatosRegistro.add(Codigo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 160, 100, -1));

        jSeparator4.setBackground(new java.awt.Color(255, 51, 0));
        jSeparator4.setForeground(new java.awt.Color(255, 51, 0));
        DatosRegistro.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 180, 80, 10));

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
        txtStockMin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStockMinKeyTyped(evt);
            }
        });
        DatosRegistro.add(txtStockMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 160, 80, 20));

        Codigo5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Codigo5.setForeground(new java.awt.Color(255, 153, 51));
        Codigo5.setText("Precio compra:  $");
        DatosRegistro.add(Codigo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 210, 120, -1));

        jSeparator5.setBackground(new java.awt.Color(255, 51, 0));
        jSeparator5.setForeground(new java.awt.Color(255, 51, 0));
        DatosRegistro.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 230, 80, 10));

        txtPrecioCompra.setFont(new java.awt.Font("Browallia New", 0, 20)); // NOI18N
        txtPrecioCompra.setForeground(new java.awt.Color(255, 255, 255));
        txtPrecioCompra.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPrecioCompra.setBorder(null);
        txtPrecioCompra.setCaretColor(new java.awt.Color(255, 255, 255));
        txtPrecioCompra.setOpaque(false);
        txtPrecioCompra.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPrecioCompraFocusLost(evt);
            }
        });
        txtPrecioCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioCompraActionPerformed(evt);
            }
        });
        txtPrecioCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioCompraKeyTyped(evt);
            }
        });
        DatosRegistro.add(txtPrecioCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 210, 80, 20));

        txtPrecioIVA.setEditable(false);
        txtPrecioIVA.setFont(new java.awt.Font("Browallia New", 0, 20)); // NOI18N
        txtPrecioIVA.setForeground(new java.awt.Color(255, 255, 255));
        txtPrecioIVA.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPrecioIVA.setBorder(null);
        txtPrecioIVA.setCaretColor(new java.awt.Color(255, 255, 255));
        txtPrecioIVA.setOpaque(false);
        txtPrecioIVA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioIVAActionPerformed(evt);
            }
        });
        DatosRegistro.add(txtPrecioIVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 250, 80, 20));

        jSeparator6.setBackground(new java.awt.Color(255, 51, 0));
        jSeparator6.setForeground(new java.awt.Color(255, 51, 0));
        DatosRegistro.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 270, 80, 10));

        Codigo6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Codigo6.setForeground(new java.awt.Color(255, 153, 51));
        Codigo6.setText("Precio compra (+IVA):  $");
        DatosRegistro.add(Codigo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 250, 170, -1));

        txtPrecioVenta.setFont(new java.awt.Font("Browallia New", 0, 20)); // NOI18N
        txtPrecioVenta.setForeground(new java.awt.Color(255, 255, 255));
        txtPrecioVenta.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPrecioVenta.setBorder(null);
        txtPrecioVenta.setCaretColor(new java.awt.Color(255, 255, 255));
        txtPrecioVenta.setOpaque(false);
        txtPrecioVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioVentaActionPerformed(evt);
            }
        });
        txtPrecioVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioVentaKeyTyped(evt);
            }
        });
        DatosRegistro.add(txtPrecioVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 290, 80, 20));

        jSeparator7.setBackground(new java.awt.Color(255, 51, 0));
        jSeparator7.setForeground(new java.awt.Color(255, 51, 0));
        DatosRegistro.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 310, 80, 10));

        Codigo7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Codigo7.setForeground(new java.awt.Color(255, 153, 51));
        Codigo7.setText("Precio venta:  $");
        DatosRegistro.add(Codigo7, new org.netbeans.lib.awtextra.AbsoluteConstraints(484, 290, -1, -1));

        btnCancelar.setToolTipText("Volver a la interfaz de Inventario.");
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.setOpaque(false);
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelarMouseExited(evt);
            }
        });
        btnCancelar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Cancelar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Cancelar.setForeground(new java.awt.Color(255, 102, 51));
        Cancelar.setText("Cancelar");
        btnCancelar.add(Cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 13, -1, 25));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Cancelar.png"))); // NOI18N
        btnCancelar.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, -1));

        DatosRegistro.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 360, 130, 50));

        btnRegistrar.setToolTipText("Guardar producto.");
        btnRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrar.setOpaque(false);
        btnRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegistrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegistrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegistrarMouseExited(evt);
            }
        });
        btnRegistrar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Registrar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Registrar.setForeground(new java.awt.Color(255, 102, 51));
        Registrar.setText("Registrar");
        btnRegistrar.add(Registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 13, -1, 25));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Agr-BasedeDatos.png"))); // NOI18N
        btnRegistrar.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, -1));

        DatosRegistro.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 360, 130, 50));

        add(DatosRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 700, 440));
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtExistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtExistenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtExistenciaActionPerformed

    private void txtStockMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockMinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStockMinActionPerformed

    private void txtPrecioCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioCompraActionPerformed

    private void txtPrecioIVAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioIVAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioIVAActionPerformed

    private void txtPrecioVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioVentaActionPerformed

    private void btnRegistrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarMouseEntered
        //ponemos color OSCURO al boton
        setEncima(btnRegistrar);
    }//GEN-LAST:event_btnRegistrarMouseEntered

    private void btnCancelarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseEntered
        //ponemos color OSCURO al boton
        setEncima(btnCancelar);
    }//GEN-LAST:event_btnCancelarMouseEntered

    private void btnRegistrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarMouseExited
        //pponemos color CLARO al boton
        setAfuera(btnRegistrar);
    }//GEN-LAST:event_btnRegistrarMouseExited

    private void btnCancelarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseExited
        //pponemos color CLARO al boton
        setAfuera(btnCancelar);
    }//GEN-LAST:event_btnCancelarMouseExited

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        //ABRIMOS la ventana de AdministrarProveedores
        RegProv rp = new RegProv(this.pr, true, cbProveedores);
        rp.setVisible(true);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
        //mandamos a mostrar el Jpanel inventario
        //pasamos como parametro el obj de la clase Principal
        Inventario inv = new Inventario(this.pr);
        mostrar_panel(inv);
    }//GEN-LAST:event_btnCancelarMouseClicked

    private void btnRegistrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarMouseClicked
        //obtenemos el arreglo con TODOS LOS CAMPOS DE TEXTO (TextFields)
        JTextField arr_txt[] = gdr_txt();
        //validar que no HAYA NINGUN CAMPO NULL
        String proveedor = cbProveedores.getSelectedItem().toString();        
        boolean txt_null = val.val_null(arr_txt, txtDescripcion, proveedor);
        
        if ( txt_null == false )  //si NO HAY ERRROR de validacion....
        {
            //registramos en la base de datos
            registrar();
            //img_selecc = ""; //reiniciamos la variable de ruta de imagen por si quiere volver a registar denuevo
        }
    }//GEN-LAST:event_btnRegistrarMouseClicked

    private void lblFotografiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFotografiaMouseClicked
        //seleccionamos la imagen del producto
        seleccionar_arch();
    }//GEN-LAST:event_lblFotografiaMouseClicked

    private void txtPrecioCompraFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPrecioCompraFocusLost
        //si el txtPrecioCompra NO ESTA VACIO......
        if(!txtPrecioCompra.getText().isEmpty())
        {
            //mandamos el precioCompra para que le sume el IVA y lo coloque en txtPrecioIVA
            txtPrecioIVA.setText(gn.compra_IVA(txtPrecioCompra.getText()));
        }
        //si ESTA VACIO.....
        else
        {
            txtPrecioIVA.setText("");
        }
    }//GEN-LAST:event_txtPrecioCompraFocusLost

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        val.longitud(evt, txtCodigo, 50);
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyTyped
        val.longitud(evt, txtDescripcion, 140);
    }//GEN-LAST:event_txtDescripcionKeyTyped

    private void txtExistenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtExistenciaKeyTyped
        //validamos como costo para aceptar decimales
        val.long_costo(evt, txtExistencia, 4);
        val.val_costo(txtExistencia, evt);
    }//GEN-LAST:event_txtExistenciaKeyTyped

    private void txtStockMinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockMinKeyTyped
        val.longitud(evt, txtStockMin, 4);
        val.val_nums(evt);
    }//GEN-LAST:event_txtStockMinKeyTyped

    private void txtPrecioCompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioCompraKeyTyped
        val.long_costo(evt, txtPrecioCompra, 4);
        val.val_costo(txtPrecioCompra, evt);
    }//GEN-LAST:event_txtPrecioCompraKeyTyped

    private void txtPrecioVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioVentaKeyTyped
        val.long_costo(evt, txtPrecioVenta, 4);
        val.val_costo(txtPrecioVenta, evt);    }//GEN-LAST:event_txtPrecioVentaKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Cancelar;
    private javax.swing.JLabel Codigo;
    private javax.swing.JLabel Codigo1;
    private javax.swing.JLabel Codigo2;
    private javax.swing.JLabel Codigo3;
    private javax.swing.JLabel Codigo4;
    private javax.swing.JLabel Codigo5;
    private javax.swing.JLabel Codigo6;
    private javax.swing.JLabel Codigo7;
    private javax.swing.JPanel DatosRegistro;
    private javax.swing.JLabel Registrar;
    private javax.swing.JLabel Titulo;
    private javax.swing.JPanel btnCancelar;
    private javax.swing.JPanel btnRegistrar;
    public static javax.swing.JComboBox<String> cbProveedores;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JLabel lblFotografia;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtExistencia;
    private javax.swing.JTextField txtPrecioCompra;
    private javax.swing.JTextField txtPrecioIVA;
    private javax.swing.JTextField txtPrecioVenta;
    private javax.swing.JTextField txtStockMin;
    // End of variables declaration//GEN-END:variables
}
