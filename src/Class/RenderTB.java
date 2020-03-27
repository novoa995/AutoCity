//GODWITHME
package Class;

import JPanels.ConsProds;
import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

//clase para poder cambiar aparencia de la tabla
//(en ese caso el color de las filas)
public class RenderTB extends DefaultTableCellRenderer
{
    
    /* Declaramos la variable para hacer conexion a la DB */
    conectar c = new conectar();
    Connection con = c.conexion();

    @Override
    //recibira el nombre de tabla, valor, si esta seleccionado, si tiene focus, fila y columna de cada celda
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        //obtenemos el sotck_min del producto de la fila para despues compararlo con la existencia
        int stock_min = cons_existencia(table.getValueAt(row,0).toString());
        
        if(ConsProds.click_Gnl == true)
        {
            //setForeground(new Color(255,153,51));
            setForeground(new Color(255,102,51));
        }
        else if (ConsProds.click_StockMin == true)
        {
            //ponemos color rojo al stock minimo
            //setForeground(new Color(220,30,30));
            setForeground(new Color(255,102,51));
            
        }
        
        //hacemos que en la tbGeneral y tbStock_min se pongan en rojo los que tengan existencia<Stock_min
        /*//si en la columna de existencia es menor que el stock minimo, coloreamos de rojo
        if(Integer.parseInt( table.getValueAt(row,2).toString() ) <= stock_min)
        {
            setForeground(new Color(204,0,0));
        }
        //sino, coloreamos normal
        else
        {
            //setBackground(Color.BLACK);
            setForeground(new Color(255,153,51)); 
        }*/
        
        //System.out.println( row+" "+column+" "+table.getValueAt(row,column) );
        
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
    }
   
    //obtenemos el stock_min del producto de cada fila
    int cons_existencia(String codigo)
    {
        int stock_min = 0;
        try 
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT Stock_min FROM inventario WHERE codigo='"+codigo+"'");
            
            while(rs.next())
            {
                //obtenemos el stock min de dicho producto (codigo) para despues comparar con existencia
                stock_min = rs.getInt("Stock_min");
                
            }
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        }
        return stock_min;
    }
    
}
