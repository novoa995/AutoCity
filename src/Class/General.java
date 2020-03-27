//GODWITHME
package Class;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class General {
    
    /* Declaramos la variable para hacer conexion a la DB */
    conectar c = new conectar();
    Connection con = c.conexion();
    
    public void cbo_proveedores(JComboBox cbo)
    {
        try 
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from proveedores");
            
            while(rs.next())
            {
                //guardamos los registros en el ComboBox
                cbo.addItem( rs.getString("nombre") );  //decimos que se obtenga el dato del atributo nombre
            }
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        } 
    }
    
    public void limpiar_cboProveedores(JComboBox cbo)
    {
        cbo.removeAllItems();
        cbo.addItem("Seleccione opcion");
    }
    
    //obtenemos el precio de compra con el IVA
    public String compra_IVA(String PrecioCompra)
    {
        double precio_compra, pCompra_IVA;    
        precio_compra = Double.parseDouble(PrecioCompra);
        pCompra_IVA = (precio_compra * 0.16) + precio_compra;
        //convertimos el precio a solo dos decimales y lo ponemos en txtPrecioIVA        
        return DosDecimales(pCompra_IVA);  
    }
    
    public String DosDecimales(Double precio)
    {
        //clase para poder obtener solo 2 DECIMALES en el numero double
        DecimalFormat df = new DecimalFormat("#.00");
        //poner punto enves de coma en las cantidades
        DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);
        
        return df.format(precio);
    }
    
}
