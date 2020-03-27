//GODWITHME

package JPanels;

import Class.*;
import JFrames.Principal;
import VentanasDialogo.RegProv;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class ConsProds extends javax.swing.JPanel {

    /* Declaramos la variable para hacer conexion a la DB */
    conectar c = new conectar();
    Connection con = c.conexion();
    TextAutoCompleter ac;
    
        
    General gn = new General();
    validacion val = new validacion();
    Principal pr;
    //RegProv rp = new RegProv(this.pr, true);
    
    //banderas para controlar los colores de botones cunado se den click
    public static boolean click_Gnl = true, click_StockMin = false;
    
    //guardaremos la ruta de la foto seleccionada
    String img_selecc="";
    //guardaremos el codigo por si se quiere modificar
    String codigo;
    boolean bloquearModificar, bloquearEliminar,pnlDatos;
    
    public ConsProds(Principal pr) {
        initComponents();
        
        //igualamos el objeto de la clase principal recibido, para asi poder abrir ventanas desde esta
        this.pr = pr;
        
        BarrFoto.setVisible(false);
        //hacemos que el text area salte de linea cada vez q sobrepase toda la fila
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        
        setGris(pnlGeneral);    //ponemos gris oscuro al boton general
        des_pnl(); //desabilitamos los lbls hasta seleccionar un registro
        bloquear_Modificar();
        bloquear_Eliminar();
        estilo_tb();    //damos estilo (colores) a la tabla         
        mostrar_datos("","general");
        tamaño_columnas();  //damos un tamaño a cada columna de la tabla
        alinear_tabla();    //alineamos las cantidades de la tabla a la derecha
        //autocompletable(); //ponemos las opciones para autocompletar el txtBuscar        
        //pondremos estilo a determinadas filas de la tb (las que tengan < existencia)
        tbProds.setDefaultRenderer(Object.class, new RenderTB());   //mandamos el constructor de nuestra clase que Renderizara la tb
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
    
    //ALINEAR cantidades (en la tabla) a la DERECHA
    void alinear_tabla()
    {
        //DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();         
        DefaultTableCellRenderer Alinear = new RenderTB(); 
        Alinear.setHorizontalAlignment(SwingConstants.RIGHT);//.LEFT .RIGHT .CENTER
        tbProds.getColumnModel().getColumn(2).setCellRenderer(Alinear);
        tbProds.getColumnModel().getColumn(3).setCellRenderer(Alinear);
    }
    
    //ponemos tamaño a las columnas de la tabla
    void tamaño_columnas()
    {
        TableColumnModel columnModel = tbProds.getColumnModel();

        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(25);
        columnModel.getColumn(3).setPreferredWidth(25);
    }
    
    void autocompletable()
    {
        //instanciamos la clase autocompletable, y especificamos a que txt
        ac = new TextAutoCompleter(txtBuscar);
        try 
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select codigo, descripcion from inventario");
            
            //metemos TODOS los nombres de los proveedores (de la BD) a la opcion de autocompletable
            while(rs.next())
            {
                String codigo = rs.getString("codigo");
                String descripcion = rs.getString("descripcion");
                        
                //añadimos la cadena a la lista de opciones del autocompltable
                ac.addItem( codigo );
                ac.addItem( descripcion );
            }
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        }
    }
    
    //BLOQUEAMOS el pnlEliminar al iniciar (hasta que se seleccione un registro)
    void bloquear_Eliminar()
    {
        bloquearEliminar = true;
        //ponemos color gris (bloquedo) a etiqueta
        lblEliminar.setForeground(Color.GRAY);
        //ponemos icono en gris (bloqueado)
        lblIconElim.setIcon( new ImageIcon(getClass().getResource("/Images/boton-eliminar1.png") ));
        pnlEliminar.setBorder(null);   //quitamos el borde
        pnlEliminar.setEnabled(false);  //desabilitamos el pnl
        pnlEliminar.setToolTipText("Seleccionar registro para eliminar.");
    }
    
    //DESBLOQUEAMOS el pnlModificar cuando se selecciona un registro
    void desbloquear_Eliminar()
    {
        bloquearEliminar = false;        
        //ponemos color naranja_bajo (desbloqueado) a etiqueta
        lblEliminar.setForeground(new Color(255,102,51));
        //ponemos icono en naranja (desbloqueado)
        lblIconElim.setIcon( new ImageIcon(getClass().getResource("/Images/boton-eliminar.png") ));
        pnlEliminar.setEnabled(true);
        pnlEliminar.setToolTipText("Eliminar el registro seleccionado.");
    }
    
    //BLOQUEAMOS el pnlModificar al iniciar (hasta que se seleccione un registro)
    void bloquear_Modificar()
    {
        bloquearModificar = true;
        //ponemos color gris (bloquedo) a etiqueta
        lblModificar.setForeground(Color.GRAY);
        //ponemos icono en gris (bloqueado)
        lblIconMod.setIcon( new ImageIcon(getClass().getResource("/Images/boton-actualizar_1.png") ));
        pnlModificar.setBorder(null);   //quitamos el borde
        pnlModificar.setEnabled(false);  //desabilitamos el pnl
        pnlModificar.setToolTipText("Seleccionar registro para modificar.");
    }
    
    //DESBLOQUEAMOS el pnlModificar cuando se selecciona un registro
    void desbloquear_Modificar()
    {
        bloquearModificar = false;        
        //ponemos color naranja_bajo (desbloqueado) a etiqueta
        lblModificar.setForeground(new Color(255,102,51));
        //ponemos icono en naranja (desbloqueado)
        lblIconMod.setIcon( new ImageIcon(getClass().getResource("/Images/boton-actualizar.png") ));
        pnlModificar.setEnabled(true);
        pnlModificar.setToolTipText("Modificar la información del registro.");
    }
    
    //desabilitamos el pnl de entrada de datos hasta que selccione un registro
    void des_pnl()
    {
        pnlDatos = false;
        //desabilitamos los lbls
        lblFotografia.setEnabled(false);
        lblCodigo.setEnabled(false);
        lblProveedor.setEnabled(false);
        lblDescripcion.setEnabled(false);
        lblExistencia.setEnabled(false);
        lblStockMin.setEnabled(false);
        lblPrecioCompra.setEnabled(false);
        lblPrecioIVA.setEnabled(false);
        lblPrecioVenta.setEnabled(false);
        jSeparator2.setEnabled(false);
        
        //desabilitamos los txt
        txtCodigo.setEnabled(false);
        cboProveedor.setEnabled(false);
        txtDescripcion.setEnabled(false);
        txtExistencia.setEnabled(false);
        txtStockMin.setEnabled(false);
        txtPrecioCompra.setEnabled(false);
        txtPrecioIVA.setEnabled(false);
        txtPrecioVenta.setEnabled(false);
        btnProveedor.setEnabled(false);
        
        //desabilitamos borde al txtDesc
        txtDescripcion.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        //desbilitamos separadores
        jSeparator2.setForeground(Color.GRAY);
        jSeparator2.setBackground(Color.GRAY);
        jSeparator3.setForeground(Color.GRAY);
        jSeparator3.setBackground(Color.GRAY);
        jSeparator4.setForeground(Color.GRAY);
        jSeparator4.setBackground(Color.GRAY);
        jSeparator5.setForeground(Color.GRAY);
        jSeparator5.setBackground(Color.GRAY);
        jSeparator6.setForeground(Color.GRAY);
        jSeparator6.setBackground(Color.GRAY);
        jSeparator7.setForeground(Color.GRAY);
        jSeparator7.setBackground(Color.GRAY);
    }
    
    //limpiamos todo el pnl de datos al dar click en gnrl o stockMin
    void limpiar_pnl()
    {
        //limpiamos los txt
        txtCodigo.setText("");
        txtDescripcion.setText("");
        txtExistencia.setText("");
        txtStockMin.setText("");
        txtPrecioCompra.setText("");
        txtPrecioIVA.setText("");
        txtPrecioVenta.setText("");
                
        //limpiamos el cbobox
        cboProveedor.removeAllItems();

        //limpiamos la fotografia
        lblFotografia.setIcon(null);
        lblFotografia.setText("Elegir Foto");
    }
    
    //habilitamos el pnl de entrada de datos cuando selccione un registro
    void habilitar_pnl()
    {
        pnlDatos = true;
        //desabilitamos los lbls
        lblFotografia.setEnabled(true);
        lblCodigo.setEnabled(true);
        lblProveedor.setEnabled(true);
        lblDescripcion.setEnabled(true);
        lblExistencia.setEnabled(true);
        lblStockMin.setEnabled(true);
        lblPrecioCompra.setEnabled(true);
        lblPrecioIVA.setEnabled(true);
        lblPrecioVenta.setEnabled(true);
        jSeparator2.setEnabled(true);
        
        //desabilitamos los txt
        txtCodigo.setEnabled(true);
        cboProveedor.setEnabled(true);
        txtDescripcion.setEnabled(true);
        txtExistencia.setEnabled(true);
        txtStockMin.setEnabled(true);
        txtPrecioCompra.setEnabled(true);
        txtPrecioIVA.setEnabled(true);
        txtPrecioVenta.setEnabled(true);
        btnProveedor.setEnabled(true);
        
        //habilitamos borde al txtDesc
        txtDescripcion.setBorder(BorderFactory.createLineBorder(new Color(255,51,0)));
        
        //habilitamos separadores
        jSeparator2.setForeground(new Color(255,51,0));
        jSeparator2.setBackground(new Color(255,51,0));
        jSeparator3.setForeground(new Color(255,51,0));
        jSeparator3.setBackground(new Color(255,51,0));
        jSeparator4.setForeground(new Color(255,51,0));
        jSeparator4.setBackground(new Color(255,51,0));
        jSeparator5.setForeground(new Color(255,51,0));
        jSeparator5.setBackground(new Color(255,51,0));
        jSeparator6.setForeground(new Color(255,51,0));
        jSeparator6.setBackground(new Color(255,51,0));
        jSeparator7.setForeground(new Color(255,51,0));
        jSeparator7.setBackground(new Color(255,51,0));
    }
    
    //damos colores (estilo) a la tabla
    void estilo_tb()
    {
        //fondo
        jScrollPane1.getViewport().setBackground(Color.BLACK);
        //encabezado
        JTableHeader t = tbProds.getTableHeader();
        t.setBackground(Color.black);
        t.setForeground(new Color(255,51,0));  //color naranja osucro
    }
    
    //agregamos animacion de color gris claro al pasar mouse
    void setGris(JPanel panel)
    {
        panel.setBackground(new Color(80,80,80));
    }
    
    void resetGris(JPanel panel)
    {
       panel.setBackground(new Color(0,0,0)); 
    }
    
    //Metodo para CONSULTAR los datos
    //pasamos como parametros el dato a buscar y el tipo de tabla que se visualizara (genral o stock_min)
    void mostrar_datos(String dato, String tipo)
    {   
        //Declaramos los encabezados que tendra la tabla en un arreglo
        String encabezados[] =  {"Código", "Descripción", "Existencia", "Precio Venta $" };
        String registros[] = new String [4];    //declaramos el arreglo donde se guardaran los registros
     
        DefaultTableModel model = new DefaultTableModel(null, encabezados){   //evitamos que se pueda editar la tabla
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
                    	}
		};
        try 
        {
            Statement st = con.createStatement();
            String consulta = null;
            if(tipo.equals("general"))
            {
                consulta = "select codigo, descripcion, Existencia, precio_venta from inventario where codigo like '"+ dato +"%'"
                        + " || descripcion like '"+ dato +"%' || Existencia like '"+ dato +"%' || precio_venta like '"+ dato +"%'";
            }
            else if(tipo.equals("stock_min"))
            {
                consulta = "select codigo, descripcion, Existencia, precio_venta, Stock_min from inventario where CONCAT(codigo, descripcion, Existencia, precio_venta)"
                    + " like '%"+ dato +"%' && Existencia < Stock_min ORDER BY Existencia";
            }
            ResultSet rs = st.executeQuery(consulta);
            
            while(rs.next())
            {
                //guardamos los registros en nuestro arreglo
                registros[0] = rs.getString("codigo");  //decimos que se obtenga el dato del atributo clave_serv
                registros[1] = rs.getString("descripcion");
                registros[2] = rs.getString("Existencia");
                registros[3] = rs.getString("precio_venta");

                model.addRow(registros);    //añadimos nuestro arreglo al modelo de tabla 
            }
            tbProds.setModel(model);    //le añadimos a NUESTRA TABLA todo el modelo anterior
            tamaño_columnas();
            alinear_tabla();
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        }
    }
    
    //obtenemos los datos del registro seleccionado y los pasamos al pnl
    void setData()
    {
        int fila = tbProds.getSelectedRow();    //Guardamos la fila que se esta seleccionando (de la tabla) en una variable

                if (fila >= 0)  //si fila es igual o mayor que 0 quiere decir que una de las filas son seleccionadas
                {
                    desbloquear_Eliminar();
                    desbloquear_Modificar();
                    habilitar_pnl();   //habilitamos todos los campos de texto para poder sobrescribir en ellos
                    gn.limpiar_cboProveedores(cboProveedor);
                    gn.cbo_proveedores(cboProveedor);   //mandamos a RELLENAR el cboProveedores
                    
                    //obtenemos el texto de cada una de las columnas de la tabla y las escibimos en cada campo de texto
                    txtCodigo.setText(tbProds.getValueAt(fila, 0).toString()); //obtenemos el texto del num de fila y columna
                    //guardamos el codigo del registro seleccionado por si despues se quiere modificar                    
                    codigo = tbProds.getValueAt(fila, 0).toString(); 
                    txtDescripcion.setText(tbProds.getValueAt(fila,1).toString());
                    txtExistencia.setText(tbProds.getValueAt(fila,2).toString());
                    txtPrecioVenta.setText(tbProds.getValueAt(fila, 3).toString());
                    //obtenemos los datos restantes de la base de datos
                    getData(tbProds.getValueAt(fila, 0).toString());
                }
    }
    
    //obtenemos los datos de la BD que faltan del registro seleccionado
    void getData(String codigo)
    {
        try 
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select proveedor, Stock_min, Precio_compra from inventario WHERE codigo='"+codigo+"'");
            while(rs.next())
            {
                //guardamos los registros en nuestro arreglo
                cboProveedor.setSelectedItem(rs.getString("proveedor"));  //decimos que se obtenga el dato del atributo clave_serv
                txtStockMin.setText(rs.getString("Stock_min"));
                txtPrecioCompra.setText(rs.getString("Precio_compra"));
                //mandamos el precioCompra para que le sume el IVA y lo coloque en txtPrecioIVA
                txtPrecioIVA.setText(gn.compra_IVA(txtPrecioCompra.getText()));
                
                //ejecutamos el hilo para mostrar (en paralelo) la foto en el pnl 
                Thread hilo = new Hilos("ProcesoFoto", codigo); //mandamos el nombre_hilo y el codigo del restro selccionado para hacer la consulta
                hilo.start();
                
                //quitamos el texto del lblFotografia para que la foto ABARQUE TODO EL ESPACIO
                lblFotografia.setText("");
            }
        } 
        catch (Exception ex) 
        {
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        }
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
    
    //modificamos el registro seleccionado
    void modificar()
    {
        //obtenemos todos los datos ingresados
        String codigo_actual,proveedor,descripcion,existencia,stock_min,precio_comp,precio_vnt;
        codigo_actual = txtCodigo.getText();
        proveedor = cboProveedor.getSelectedItem().toString();
        descripcion = txtDescripcion.getText();
        existencia = txtExistencia.getText();
        stock_min=txtStockMin.getText();
        precio_comp=txtPrecioCompra.getText();
        precio_vnt=txtPrecioVenta.getText();
        
        try 
        {
            String consulta_modificar = "";
           //consulta de modificar con foto (si se ah seleccionado nueva foto)
            if( !img_selecc.equals("") )
            {
                consulta_modificar = "UPDATE inventario SET codigo=?,proveedor=?,descripcion=?"
                    + ",Existencia=?,Stock_min=?,precio_compra=?,precio_venta=?,foto=?"
                    + " WHERE codigo = '"+ codigo +"'";
            }
            //consulta para modificar SIN FOTO (el usuario no cambio de foto)
            else if( img_selecc.equals("") )
            {
                consulta_modificar = "UPDATE inventario SET codigo=?,proveedor=?,descripcion=?"
                    + ",Existencia=?,Stock_min=?,precio_compra=?,precio_venta=?"
                    + " WHERE codigo = '"+ codigo +"'";
            }

            PreparedStatement psd = con.prepareStatement( consulta_modificar );
            psd.setString(1, codigo_actual);
            psd.setString(2, proveedor);
            psd.setString(3, descripcion);
            psd.setString(4, existencia);
            psd.setString(5, stock_min);
            psd.setString(6, precio_comp);
            psd.setString(7, precio_vnt);
            //si hay foto la actualizamos en la base de datos
            if( !img_selecc.equals("") )
            {
                FileInputStream foto = new FileInputStream(img_selecc);
                psd.setBinaryStream(8, foto);
            }
            
            int grd = psd.executeUpdate();  //ejecutamos la consulta
            
            if( grd > 0)    //si se completo la modificacion entra al if
            {
                JOptionPane.showMessageDialog(null, "Producto "+txtCodigo.getText()+" modificado con éxito", "Registro Modificado", JOptionPane.INFORMATION_MESSAGE);
                limpiar_pnl();
                des_pnl();  //bloqueamos el panel de datos
                //bloqueamos los botones
                bloquear_Modificar();
                bloquear_Eliminar();
            }
        } 
        catch (Exception ex) {
           JOptionPane.showMessageDialog(null, "Error: " + ex);
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
    
    void eliminar()
    {
        try 
        {
            PreparedStatement psd = con.prepareStatement("DELETE FROM inventario WHERE codigo='"+ txtCodigo.getText() +"'");
            int grd = psd.executeUpdate();  //ejecutamos la consulta
            
            if( grd > 0)    //si se completo la eliminacion entra al if
            {
                JOptionPane.showMessageDialog(null, "Producto " + txtCodigo.getText() +  " eliminado con éxito", "Registro Eliminado", JOptionPane.INFORMATION_MESSAGE);
                limpiar_pnl();
                des_pnl();  //bloqueamos el panel de datos
                //bloqueamos los botones
                bloquear_Modificar();
                bloquear_Eliminar();
            }
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        }
    }

    //verificamos que el codigo del producto exista
    int verficar_existencia(String codigo)
    {
        int count = 0;
        try 
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select COUNT(*) from inventario WHERE codigo='"+codigo+"'");
            while(rs.next())
            {
                count = rs.getInt("COUNT(*)");
            }
        } 
        catch (Exception ex) 
        {
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        }
        return count;  
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlEliminar = new javax.swing.JPanel();
        lblEliminar = new javax.swing.JLabel();
        lblIconElim = new javax.swing.JLabel();
        pnlCancelar = new javax.swing.JPanel();
        lblCancelar = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        pnlModificar = new javax.swing.JPanel();
        lblModificar = new javax.swing.JLabel();
        lblIconMod = new javax.swing.JLabel();
        Titulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        Datos = new javax.swing.JPanel();
        BarrFoto = new javax.swing.JProgressBar();
        lblProveedor = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lblFotografia = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lblCodigo = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextArea();
        lblDescripcion = new javax.swing.JLabel();
        cboProveedor = new javax.swing.JComboBox<>();
        txtExistencia = new javax.swing.JTextField();
        lblExistencia = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        btnProveedor = new javax.swing.JLabel();
        lblStockMin = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        txtStockMin = new javax.swing.JTextField();
        lblPrecioCompra = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        txtPrecioCompra = new javax.swing.JTextField();
        txtPrecioIVA = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        lblPrecioIVA = new javax.swing.JLabel();
        txtPrecioVenta = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        lblPrecioVenta = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbProds = new javax.swing.JTable();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        panel_tabla = new javax.swing.JPanel();
        pnlGeneral = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        pnlStockMin = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtBuscar = new JPanels.JCTextField();

        setOpaque(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlEliminar.setBackground(new java.awt.Color(0, 0, 0));
        pnlEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlEliminarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlEliminarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlEliminarMouseExited(evt);
            }
        });
        pnlEliminar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblEliminar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblEliminar.setForeground(new java.awt.Color(255, 102, 51));
        lblEliminar.setText("Eliminar");
        pnlEliminar.add(lblEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 8, -1, -1));

        lblIconElim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/boton-eliminar.png"))); // NOI18N
        pnlEliminar.add(lblIconElim, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 8, -1, -1));

        add(pnlEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(301, 365, 120, 40));

        pnlCancelar.setBackground(new java.awt.Color(255, 51, 0));
        pnlCancelar.setToolTipText("Volver a la interfaz de Inventario.");
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
        pnlCancelar.add(lblCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 8, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cancelar_cons.png"))); // NOI18N
        pnlCancelar.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 8, -1, -1));

        add(pnlCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 365, 120, 40));

        pnlModificar.setBackground(new java.awt.Color(255, 51, 0));
        pnlModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlModificar.setOpaque(false);
        pnlModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlModificarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlModificarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlModificarMouseExited(evt);
            }
        });
        pnlModificar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblModificar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblModificar.setForeground(new java.awt.Color(255, 102, 51));
        lblModificar.setText("Modificar");
        pnlModificar.add(lblModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 8, -1, -1));

        lblIconMod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/boton-actualizar.png"))); // NOI18N
        pnlModificar.add(lblIconMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 8, -1, -1));

        add(pnlModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(437, 365, 120, 40));

        Titulo.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        Titulo.setForeground(new java.awt.Color(255, 51, 0));
        Titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titulo.setText("Consulta de Productos");
        add(Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 700, 40));

        jSeparator1.setBackground(new java.awt.Color(80, 80, 80));
        jSeparator1.setForeground(new java.awt.Color(80, 80, 80));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 620, 10));

        Datos.setOpaque(false);
        Datos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        Datos.add(BarrFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(555, 90, 110, -1));

        lblProveedor.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblProveedor.setForeground(new java.awt.Color(255, 153, 51));
        lblProveedor.setText("Proveedor:");
        Datos.add(lblProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 77, -1));

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
        Datos.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 130, 20));

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
        Datos.add(lblFotografia, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 160, 170));

        jSeparator2.setBackground(new java.awt.Color(255, 51, 0));
        jSeparator2.setForeground(new java.awt.Color(255, 51, 0));
        Datos.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 130, 10));

        lblCodigo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblCodigo.setForeground(new java.awt.Color(255, 153, 51));
        lblCodigo.setText("Código:");
        Datos.add(lblCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 60, -1));

        txtDescripcion.setColumns(20);
        txtDescripcion.setFont(new java.awt.Font("Browallia New", 0, 20)); // NOI18N
        txtDescripcion.setForeground(new java.awt.Color(255, 255, 255));
        txtDescripcion.setRows(5);
        txtDescripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 0)));
        txtDescripcion.setCaretColor(new java.awt.Color(255, 255, 255));
        txtDescripcion.setOpaque(false);
        Datos.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 230, 90));

        lblDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblDescripcion.setForeground(new java.awt.Color(255, 153, 51));
        lblDescripcion.setText("Descripción:");
        Datos.add(lblDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 90, -1));

        cboProveedor.setBackground(new java.awt.Color(0, 0, 0));
        cboProveedor.setFont(new java.awt.Font("Browallia New", 0, 20)); // NOI18N
        cboProveedor.setForeground(new java.awt.Color(255, 255, 255));
        cboProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboProveedor.setOpaque(false);
        Datos.add(cboProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 120, -1));

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
        Datos.add(txtExistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 30, 80, 20));

        lblExistencia.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblExistencia.setForeground(new java.awt.Color(255, 153, 51));
        lblExistencia.setText("Existencia:");
        Datos.add(lblExistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, 70, -1));

        jSeparator3.setBackground(new java.awt.Color(255, 51, 0));
        jSeparator3.setForeground(new java.awt.Color(255, 51, 0));
        Datos.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, 80, 10));

        btnProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/AjusteProv.png"))); // NOI18N
        btnProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProveedorMouseClicked(evt);
            }
        });
        Datos.add(btnProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, -1, 25));

        lblStockMin.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblStockMin.setForeground(new java.awt.Color(255, 153, 51));
        lblStockMin.setText("Stock mínimo:");
        Datos.add(lblStockMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, 100, -1));

        jSeparator4.setBackground(new java.awt.Color(255, 51, 0));
        jSeparator4.setForeground(new java.awt.Color(255, 51, 0));
        Datos.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 80, 10));

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
        Datos.add(txtStockMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 80, 20));

        lblPrecioCompra.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblPrecioCompra.setForeground(new java.awt.Color(255, 153, 51));
        lblPrecioCompra.setText("Precio compra:  $");
        Datos.add(lblPrecioCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(317, 90, 120, -1));

        jSeparator5.setBackground(new java.awt.Color(255, 51, 0));
        jSeparator5.setForeground(new java.awt.Color(255, 51, 0));
        Datos.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 110, 80, 10));

        txtPrecioCompra.setFont(new java.awt.Font("Browallia New", 0, 20)); // NOI18N
        txtPrecioCompra.setForeground(new java.awt.Color(255, 255, 255));
        txtPrecioCompra.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPrecioCompra.setBorder(null);
        txtPrecioCompra.setCaretColor(new java.awt.Color(255, 255, 255));
        txtPrecioCompra.setOpaque(false);
        txtPrecioCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioCompraActionPerformed(evt);
            }
        });
        Datos.add(txtPrecioCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, 80, 20));

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
        Datos.add(txtPrecioIVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 120, 80, 20));

        jSeparator6.setBackground(new java.awt.Color(255, 51, 0));
        jSeparator6.setForeground(new java.awt.Color(255, 51, 0));
        Datos.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 140, 80, 10));

        lblPrecioIVA.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblPrecioIVA.setForeground(new java.awt.Color(255, 153, 51));
        lblPrecioIVA.setText("Precio compra (+IVA):  $");
        Datos.add(lblPrecioIVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(267, 120, 170, -1));

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
        Datos.add(txtPrecioVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 150, 80, 20));

        jSeparator7.setBackground(new java.awt.Color(255, 51, 0));
        jSeparator7.setForeground(new java.awt.Color(255, 51, 0));
        Datos.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 170, 80, 10));

        lblPrecioVenta.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblPrecioVenta.setForeground(new java.awt.Color(255, 153, 51));
        lblPrecioVenta.setText("Precio venta:  $");
        Datos.add(lblPrecioVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(331, 150, -1, -1));

        add(Datos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 700, 190));

        tbProds.setBackground(new java.awt.Color(0, 0, 0));
        tbProds.setForeground(new java.awt.Color(255, 153, 51));
        tbProds.setModel(new javax.swing.table.DefaultTableModel(
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
        tbProds.setSelectionBackground(new java.awt.Color(80, 80, 80));
        tbProds.setSelectionForeground(new java.awt.Color(235, 235, 235));
        tbProds.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProdsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbProds);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 660, 240));

        jSeparator8.setBackground(new java.awt.Color(255, 51, 0));
        jSeparator8.setForeground(new java.awt.Color(255, 51, 0));
        add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 392, 230, 10));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buscar.png"))); // NOI18N
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 362, -1, 30));

        panel_tabla.setBackground(new java.awt.Color(51, 51, 51));
        panel_tabla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlGeneral.setBackground(new java.awt.Color(0, 0, 0));
        pnlGeneral.setToolTipText("Informacion de todos los productos.");
        pnlGeneral.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlGeneral.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlGeneralMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlGeneralMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlGeneralMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlGeneralMousePressed(evt);
            }
        });
        pnlGeneral.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 153, 51));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("General");
        pnlGeneral.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 4, -1, 20));

        panel_tabla.add(pnlGeneral, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 30));

        pnlStockMin.setBackground(new java.awt.Color(0, 0, 0));
        pnlStockMin.setToolTipText("Información de los productos con stock minimo.");
        pnlStockMin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlStockMin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlStockMinMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlStockMinMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlStockMinMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlStockMinMousePressed(evt);
            }
        });
        pnlStockMin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 153, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Stock Min");
        pnlStockMin.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 4, -1, 20));

        panel_tabla.add(pnlStockMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 120, 30));

        add(panel_tabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 240, 30));

        txtBuscar.setBorder(null);
        txtBuscar.setForeground(new java.awt.Color(255, 255, 255));
        txtBuscar.setCaretColor(new java.awt.Color(255, 255, 255));
        txtBuscar.setFont(new java.awt.Font("Browallia New", 0, 20)); // NOI18N
        txtBuscar.setOpaque(false);
        txtBuscar.setPhColor(new java.awt.Color(153, 153, 153));
        txtBuscar.setPlaceholder("Buscar producto");
        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, 200, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtExistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtExistenciaActionPerformed
    }//GEN-LAST:event_txtExistenciaActionPerformed

    private void txtStockMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockMinActionPerformed
    }//GEN-LAST:event_txtStockMinActionPerformed

    private void txtPrecioCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioCompraActionPerformed
    }//GEN-LAST:event_txtPrecioCompraActionPerformed

    private void txtPrecioIVAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioIVAActionPerformed
    }//GEN-LAST:event_txtPrecioIVAActionPerformed

    private void txtPrecioVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioVentaActionPerformed
    }//GEN-LAST:event_txtPrecioVentaActionPerformed

    private void btnProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProveedorMouseClicked
        //si pnlDatos esta habilitado dejamos que se abra AdministrarProveedores
        if(pnlDatos == true)
        {
            //ABRIMOS la ventana de AdministrarProveedores
            RegProv rp = new RegProv(this.pr, true, cboProveedor);
            rp.setVisible(true);
        }
    }//GEN-LAST:event_btnProveedorMouseClicked

    private void pnlGeneralMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlGeneralMouseEntered
        setGris(pnlGeneral);
    }//GEN-LAST:event_pnlGeneralMouseEntered

    private void pnlGeneralMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlGeneralMouseExited
        //si NO esta presionado el boton general...
        if(click_Gnl == false)
        {
            //desabilitamos color
            resetGris(pnlGeneral);
        }
    }//GEN-LAST:event_pnlGeneralMouseExited

    private void pnlGeneralMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlGeneralMousePressed
        resetGris(pnlGeneral);
    }//GEN-LAST:event_pnlGeneralMousePressed

    private void pnlGeneralMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlGeneralMouseClicked
        click_Gnl = true;
        click_StockMin = false;
        
        setGris(pnlGeneral);
        resetGris(pnlStockMin);
        limpiar_pnl();  //limpiamos todos los datos que estan en el pnl
        des_pnl(); //desabilitamos los lbls hasta seleccionar un registro
        bloquear_Modificar();
        bloquear_Eliminar();
        mostrar_datos("","general");
    }//GEN-LAST:event_pnlGeneralMouseClicked

    private void pnlStockMinMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlStockMinMouseEntered
        setGris(pnlStockMin);
    }//GEN-LAST:event_pnlStockMinMouseEntered

    private void pnlStockMinMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlStockMinMouseExited
        //si NO esta presionado el boton de StockMin...
        if(click_StockMin == false)
        {
            //desabilitamos color
            resetGris(pnlStockMin);
        }
    }//GEN-LAST:event_pnlStockMinMouseExited

    private void pnlStockMinMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlStockMinMousePressed
        resetGris(pnlStockMin);
    }//GEN-LAST:event_pnlStockMinMousePressed

    private void pnlStockMinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlStockMinMouseClicked
        click_Gnl = false;
        click_StockMin = true;
        
        setGris(pnlStockMin);
        resetGris(pnlGeneral);
        limpiar_pnl();
        des_pnl();
        bloquear_Modificar();
        bloquear_Eliminar();
        mostrar_datos("","stock_min");
    }//GEN-LAST:event_pnlStockMinMouseClicked

    private void pnlModificarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlModificarMouseEntered
        //si el pnlmodificar NO esta bloqueado que ponga color al encimar el raton
        if(bloquearModificar == false)
        {
            //ponemos borde al panel
            pnlModificar.setBorder(BorderFactory.createLineBorder(new Color(255,51,0)));
            //coloreamos naranja oscuro al label
            lblModificar.setForeground(new Color(255,51,0));
        }
    }//GEN-LAST:event_pnlModificarMouseEntered

    private void pnlModificarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlModificarMouseExited
        //si el pnlmodificar NO esta bloqueado que ponga color al salir el raton
        if(bloquearModificar == false)
        {
            //QUITAMOS borde al panel
            pnlModificar.setBorder(null);
            //coloreamos naranja CLARO al label
            lblModificar.setForeground(new Color(255,102,51));        
        }

        
    }//GEN-LAST:event_pnlModificarMouseExited

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

    private void tbProdsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProdsMouseClicked
        //obtenemos los datos del registro seleccionado y los mostramos en el pnl
        setData();
    }//GEN-LAST:event_tbProdsMouseClicked

    private void pnlEliminarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlEliminarMouseEntered
        //si el pnlEliminar NO esta bloqueado que ponga color al entrar el raton
        if(bloquearEliminar == false)
        {
            //ponemos borde al panel
            pnlEliminar.setBorder(BorderFactory.createLineBorder(new Color(255,51,0)));
            //coloreamos naranja oscuro al label
            lblEliminar.setForeground(new Color(255,51,0));
        }
    }//GEN-LAST:event_pnlEliminarMouseEntered

    private void pnlEliminarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlEliminarMouseExited
        //si el pnlEliminar NO esta bloqueado que quite color al salir el raton
        if(bloquearEliminar == false)
        {
            ///QUITAMOS borde al panel
            pnlEliminar.setBorder(null);
            //coloreamos naranja CLARO al label
            lblEliminar.setForeground(new Color(255,102,51));
        }
    }//GEN-LAST:event_pnlEliminarMouseExited

    private void pnlCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCancelarMouseClicked
        //mandamos a mostrar el Jpanel inventario
        //pasamos como parametro el obj de la clase Principal
        Inventario inv = new Inventario(this.pr);
        mostrar_panel(inv);
    }//GEN-LAST:event_pnlCancelarMouseClicked

    private void pnlModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlModificarMouseClicked
        //si el pnlmodificar NO esta bloqueado....
        if(bloquearModificar == false)
        {
            //obtenemos el arreglo con TODOS LOS CAMPOS DE TEXTO (TextFields)
            JTextField arr_txt[] = gdr_txt();
            //validar que no HAYA NINGUN CAMPO NULL
            String proveedor = cboProveedor.getSelectedItem().toString();        
            boolean txt_null = val.val_null(arr_txt, txtDescripcion, proveedor);

            if ( txt_null == false )  //si NO HAY ERRROR de validacion....
            {
                modificar();
                img_selecc = ""; //reiniciamos la variable de ruta de imagen por si quiere volver a registar denuevo
                mostrar_datos("", "general");
            }
        }
    }//GEN-LAST:event_pnlModificarMouseClicked

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void lblFotografiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFotografiaMouseClicked
        //seleccionamos la imagen del producto
        seleccionar_arch();
    }//GEN-LAST:event_lblFotografiaMouseClicked

    private void pnlEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlEliminarMouseClicked
        //si el pnlEliminar NO esta bloqueado...
        if(bloquearEliminar == false)
        {
            String codigo = txtCodigo.getText();
            //verificamos antes de eliminar que el producto con el codigo seleccionado se encuentre en la BD
            int count_existencia = verficar_existencia(codigo);
            //si se encuentra algun registro en la BD.....
            if(count_existencia == 1)
            {
                //preguntamos si esta seguro de elimnar, si si entramos al if y eliminamos el registro
                int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro que deseas eliminar el producto: "+ codigo +"?", "Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                //si=0    no=1
                if(resp == 0)
                {
                    eliminar();
                    mostrar_datos("", "general"); //cargamos los datos generales (todos) en la tabla
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Codigo del producto invalido, no se encuentra en la base de datos", "Error al eliminar", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_pnlEliminarMouseClicked

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        if(click_Gnl == true)
        {
            mostrar_datos(txtBuscar.getText(),"general");
        }
        else if(click_StockMin == true)
        {
            mostrar_datos(txtBuscar.getText(),"stock_min");
        }
    }//GEN-LAST:event_txtBuscarKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JProgressBar BarrFoto;
    private javax.swing.JPanel Datos;
    private javax.swing.JLabel Titulo;
    private javax.swing.JLabel btnProveedor;
    private javax.swing.JComboBox<String> cboProveedor;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JLabel lblCancelar;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblEliminar;
    private javax.swing.JLabel lblExistencia;
    public static javax.swing.JLabel lblFotografia;
    private javax.swing.JLabel lblIconElim;
    private javax.swing.JLabel lblIconMod;
    private javax.swing.JLabel lblModificar;
    private javax.swing.JLabel lblPrecioCompra;
    private javax.swing.JLabel lblPrecioIVA;
    private javax.swing.JLabel lblPrecioVenta;
    private javax.swing.JLabel lblProveedor;
    private javax.swing.JLabel lblStockMin;
    private javax.swing.JPanel panel_tabla;
    private javax.swing.JPanel pnlCancelar;
    private javax.swing.JPanel pnlEliminar;
    private javax.swing.JPanel pnlGeneral;
    private javax.swing.JPanel pnlModificar;
    private javax.swing.JPanel pnlStockMin;
    private javax.swing.JTable tbProds;
    private JPanels.JCTextField txtBuscar;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtExistencia;
    private javax.swing.JTextField txtPrecioCompra;
    private javax.swing.JTextField txtPrecioIVA;
    private javax.swing.JTextField txtPrecioVenta;
    private javax.swing.JTextField txtStockMin;
    // End of variables declaration//GEN-END:variables
}
