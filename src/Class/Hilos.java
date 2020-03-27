//GODWITHME
//clase para poder cargar la foto (consultar) de la base de datos en paralelo
package Class;

import JPanels.ConsProds;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Hilos extends Thread{
    
    /* Declaramos la variable para hacer conexion a la DB */
    conectar c = new conectar();
    Connection con = c.conexion();
    
    //declaramos la variable para recibir el codigo a la cual se le hara la consulta de la foto
    String codigo;
    
    public Hilos(String nombre, String codigo)
    {
        super(nombre);
        this.codigo = codigo;
    }
    
    public void run()
    {
        /*
        //consultamos la fotografia del registro seleccionado
        try 
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select foto from inventario WHERE codigo='"+codigo+"'");
            while(rs.next())
            {
                //BufferedImage
                Image foto = ImageIO.read( rs.getBinaryStream("foto") );
                foto = foto.getScaledInstance(ConsProds.lblFoto.getWidth(), ConsProds.lblFoto.getHeight(), Image.SCALE_DEFAULT);
                ConsProds.lblFoto.setIcon( new ImageIcon(foto) );
            }
        } 
        catch (Exception ex) 
        {
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        }
        */
        //consultamos y añadimos la foto con una barra de progreso (por si tarda)
        barra();
    }
    
    private Timer t;
    private ActionListener ac;
    private int cont = 0;
    Image foto = null;
    
    //metodo para mantener el progreso mientras carga la foto de la base de datos
    void barra()
    {
        //inicializamos las vairbles para cada vez que restauramos
        cont = 0;
        
        //metodo ac que se ejecutara cada ciertos milisegundos
        ac = new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                cont = cont+1;  //aumentamos el contador
                ConsProds.BarrFoto.setValue(cont);   //mandamos el contador a la barra
                
                //si llega a 25 el cont leemos la foto de la base de datos
                if(cont == 26)
                {
                    //consultamos la fotografia del registro seleccionado
                    try 
                    {
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery("select foto from inventario WHERE codigo='"+codigo+"'");
                        if(rs.next())
                        {
                            //BufferedImage
                            foto = ImageIO.read( rs.getBinaryStream("foto") );
                        }
                    } 
                    catch (Exception ex) 
                    {
                        JOptionPane.showMessageDialog(null, "Error: " + ex);
                    }
                }
                else if(cont == 51)
                {
                    //modificamos el tamaño de la foto (al tamaño del lbl)
                    foto = foto.getScaledInstance(ConsProds.lblFotografia.getWidth(), ConsProds.lblFotografia.getHeight(), Image.SCALE_SMOOTH);
                }
                else if(cont == 76)
                {
                    //insertamos la foto en el lbl
                    ConsProds.lblFotografia.setIcon( new ImageIcon(foto) );                    
                }
                //si llega a 100 se completo la barra
                else if(cont == 100)
                {
                    t.stop();   //tenemos el timer
                    ConsProds.BarrFoto.setVisible(false);    //desaparecemos la barra
                    //ConsProds.lblFoto.setIcon( new ImageIcon(foto) );
                }
            }
        };
        
        //indicamos el tiempo (milisegundos) que se estara ejecutando el metodo ac
        t = new Timer(1, ac);
        //indicamos que se este escribiendo en la barra el procentaje
        ConsProds.BarrFoto.setStringPainted(true);
        ConsProds.BarrFoto.setVisible(true); //hacemos visible la barra
        t.start();  //arrancamos el timer (cada ciertos milisegundos se ejectura el metodo AC)
    }
}
