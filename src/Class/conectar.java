
package Class;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class conectar {

    Connection conect = null;

    public Connection conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conect = DriverManager.getConnection("jdbc:mysql://localhost/autocity", "root", "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        }
        return conect;
    }
    
    //metodo para conexion aleternativa (para restaurar BD)
    public Connection con_alt() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conect = DriverManager.getConnection("jdbc:mysql://localhost/mysql", "root", "");
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, "Error " + e);
        }
        return conect;
    }

}
