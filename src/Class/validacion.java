//GODWITHME
package Class;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class validacion {
    
    //variable para controlar la validacion de los numeros maximos(2) despues del punto decimal
    static boolean punto;  //se activara cuando se coloque un punto (.)
    int long_max; //se GUARDARA la longitud MAXIMA una vez activado el punto
    
    //valdacion para comprobar que ningun campo de texto SEA NULL
    public boolean val_null( JTextField arr_txt[], JTextArea txtDescripcion, String prov )
    {
        boolean error_null = false;
        //si el txt Descripcion esta VACIO.....
        if(txtDescripcion.getText().isEmpty())
        {
            error_null = true;
            JOptionPane.showMessageDialog(null, "Verifique que ningun campo de texto se encuentre vacio", "Campos de texto null", JOptionPane.ERROR_MESSAGE);
        }
        //si NO SE HA SELECCIONADO proveedor...
        else if(prov.equals("Seleccione opcion"))
        {
            error_null = true;
            JOptionPane.showMessageDialog(null, "Seleccione proveedor existente", "Verifique proveedor", JOptionPane.ERROR_MESSAGE);            
        }
        //entoncs... verificar que los demas txt NO esten vacios
        else
        {
            //ciclo para RECORRER todos los txt del arreglo
            for(int x=0; x < arr_txt.length; x++)
            {
                //si el elemento actual ES IGUAL A VACIO 3marcamos el error de campo null
                if(arr_txt[x].getText().equals(""))
                {
                    error_null = true;
                    JOptionPane.showMessageDialog(null, "Verifique que ningun campo de texto se encuentre vacio", "Campos de texto null", JOptionPane.ERROR_MESSAGE);
                    break;
                }
            }
        }
        return error_null;
    }
    
    //validacion para solo permitir cierta cantidadad de caracteres
    //recibiremos el EVENTO, COMPONENTE, y CANTIDAD de longitud a validar
    public void longitud(KeyEvent ev, JTextField txt, int cant)
    {
        //obtenemos la LONGITUD cada vez q se ingrese un caracter
        int lon = txt.getText().length();
        //si la LONGITUD es mayor o igual a la CANTIDAD permitida
        if( lon >= cant )
        {
            //no dejamos que INGRESE ningun caracter mas
            ev.consume();
        }
    }
    
    //validacion para solo permitir cierta cantidadad de caracteres
    //recibiremos el EVENTO, COMPONENTE, y CANTIDAD de longitud a validar
    public void longitud(KeyEvent ev, JTextArea txt, int cant)
    {
        //obtenemos la LONGITUD cada vez q se ingrese un caracter
        int lon = txt.getText().length();
        //si la LONGITUD es mayor o igual a la CANTIDAD permitida
        if( lon >= cant )
        {
            //no dejamos que INGRESE ningun caracter mas
            ev.consume();
        }
    }
    
    //validamos la longitud del costo, aumentando si contiene punto decimal 
    public void long_costo(KeyEvent ev, JTextField txt, int cant)
    {
        char c = ev.getKeyChar();
        //obtenemos la LONGITUD cada vez q se ingrese un caracter
        int lon = txt.getText().length();
        
        //si la cadena obtenida contiene un punto "."
        if( txt.getText().contains(".") )
        {   
            //si es el PRIMER PUNTO entrara
            if(punto == false)
            {
                // activamos la bandera de que se encontro un punto
                punto = true;
                long_max = lon + 2;  //la longitud maxima sera hasta que se agreguen SOLAMENTE 2 decimales
                
            }
            //si los decimales son mas de 2, que no agregue nada
            if( lon >= long_max )
            {
                ev.consume();
            }
        }
        //si la LONGITUD es mayor o igual a la CANTIDAD permitida (sin punto)
        else if( lon >= cant && c != '.' )
        {
            //no dejamos que INGRESE ningun caracter mas
            ev.consume();
        }
        else
        {
            //si se quita o no se encuentra ningun punto, desactivamos la bandera
            punto = false;
        }
    }
    
    //validacion para solo ACEPTAR LETRAS
    public void val_letra(KeyEvent ev)
    {
        //OBTENEMOS el caracter que se acaba de ingresar
        char c = ev.getKeyChar();
        //pondremos que "c" sea DIFERENTE a lo q queremos q se ingrese
        if( (c < 'a' || c > 'z' ) && (c < 'A' || c > 'Z' ) )
        {
            //si el "c" es diferente a una letra, NO PERMITIMOS que se ingrese
            ev.consume();
        }
    }
    
    //validacion para solo ACEPTAR LETRAS y ESPACIO
    //validacion para NOMBRES
    public void val_let_esp(KeyEvent ev)
    {
        //OBTENEMOS el caracter que se acaba de ingresar
        char c = ev.getKeyChar();
        //pondremos que "c" sea DIFERENTE a lo q queremos q se ingrese
        if( (c < 'a' || c > 'z' ) && (c < 'A' || c > 'Z' ) && (c != (char)KeyEvent.VK_SPACE) && (c != '.')) 
        {
            //si el "c" es diferente a una letra, NO PERMITIMOS que se ingrese
            ev.consume();
        }
    }
    
    //validacion para solo ACEPTAR LETRAS y NUMEROS
    public void val_LetrayNums(KeyEvent ev)
    {
        //OBTENEMOS el caracter que se acaba de ingresar
        char c = ev.getKeyChar();
        //pondremos que "c" sea DIFERENTE a lo q queremos q se ingrese
        if( (c < 'a' || c > 'z' ) && (c < 'A' || c > 'Z' ) && (c < '0' || c > '9' ) )
        {
            //si el "c" es diferente a una letra, NO PERMITIMOS que se ingrese
            ev.consume();
        }
    }
    
    //validacion para solo ACEPTAR LETRAS y NUMEROS
    //validacion para CALLE,COLONIA,NOM_INV
    public void LetraNumsPuntEsp(KeyEvent ev)
    {
        //OBTENEMOS el caracter que se acaba de ingresar
        char c = ev.getKeyChar();
        //pondremos que "c" sea DIFERENTE a lo q queremos q se ingrese
        if( (c < 'a' || c > 'z' ) && (c < 'A' || c > 'Z' ) && (c < '0' || c > '9' ) && (c != '.') && (c != (char)KeyEvent.VK_SPACE))
        {
            //si el "c" es diferente a una letra, NO PERMITIMOS que se ingrese
            ev.consume();
        }
    }
    
    //validacion para no aceptar caracteres especiales
    public void noCarEsp(KeyEvent ev)
    {
        //OBTENEMOS el caracter que se acaba de ingresar
        char c = ev.getKeyChar();
        //pondremos que "c" sea DIFERENTE a lo q queremos q se ingrese
        if( (c >= 33 && c <= 47 ) || (c < 'A' || c > 'Z' ) )
        {
            ev.consume();
        }
    }
    
    //validacion para solo ACEPTAR LETRAS, NUMEROS y :
    //validacion para Hora
    public void val_hora(KeyEvent ev)
    {
        //OBTENEMOS el caracter que se acaba de ingresar
        char c = ev.getKeyChar();
        //pondremos que "c" sea DIFERENTE a lo q queremos q se ingrese
        if( (c < 'a' || c > 'z' ) && (c < '0' || c > '9' ) && (c != ':') )
        {
            //si el "c" es diferente a una letra, NO PERMITIMOS que se ingrese
            ev.consume();
        }
    }
    
    //validacion para solo ACEPTAR NUMEROS con ESPACIOS
    public void val_nums_esp(KeyEvent ev)
    {
        //OBTENEMOS el caracter que se acaba de ingresar
        char c = ev.getKeyChar();       
        //pondremos que "c" sea DIFERENTE a lo q queremos q se ingrese
        if( (c < '0' || c > '9' ) && (c != (char)KeyEvent.VK_SPACE) ) 
        {
            //si el "c" es diferente a una letra, NO PERMITIMOS que se ingrese
            ev.consume();
        }
    }
    
    //validacion para solo ACEPTAR NUMEROS
    public void val_nums(KeyEvent ev)
    {
        //OBTENEMOS el caracter que se acaba de ingresar
        char c = ev.getKeyChar();       
        //pondremos que "c" sea DIFERENTE a lo q queremos q se ingrese
        if( (c < '0' || c > '9' ) ) 
        {
            //si el "c" es diferente a una letra, NO PERMITIMOS que se ingrese
            ev.consume();
        }
    }
    
    //validacion para solo ACEPTAR PRECIOS(con punto decimal, y que este no se repita)
    public void val_costo( JTextField txt, KeyEvent ev)
    {
        //OBTENEMOS el caracter que se acaba de ingresar
        char c = ev.getKeyChar();       
        //si es diferente de un numero y diferente de un '.' entra al if
        if( (c < '0' || c > '9' ) && (c != '.') ) 
        {
            //si el "c" es diferente a una letra, NO PERMITIMOS que se ingrese
            ev.consume();
        }
        //si recibe un '.' y ya esta en la cadena entra al if
        else if (c == '.' && txt.getText().contains(".")) //metodo contains(""), que retorna true si el string contiene la cadena especifcada
        {
            ev.consume();
        }
    }
    
    //validacion para aceptar CORREOS ELECTRONICOS
    public boolean val_email(String correo)
    {
        //validar con expresion regular
        Pattern pat = Pattern.compile("^[\\w\\\\\\+]+(\\.[\\w\\\\]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$");
        Matcher mat=pat.matcher(correo);
        if ( mat.matches() || correo.equals("") )
        {
            return false;
        }
        else 
        {
            JOptionPane.showMessageDialog(null, "Verifique que el correo este bien escrito", "Error en Correo", JOptionPane.ERROR_MESSAGE);
            return true;
        }
    }
    
    //validacion para aceptar el RFC correctos
    public boolean val_rfc(String rfc)
    {
        //validar con expresion regular
        Pattern pat = Pattern.compile("[a-zA-z]{4}[0-9]{6}[a-zA-z|0-9]{3}");
        Matcher mat=pat.matcher(rfc);
        if ( mat.matches() || rfc.equals("") )
        {
            return false;
        }
        else 
        {
            JOptionPane.showMessageDialog(null, "Verifique que el RFC sea correcto", "Error en RFC", JOptionPane.ERROR_MESSAGE);
            return true;
        }
    }
}