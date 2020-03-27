//GODWITHME
package JFrames;

import Class.conectar;
import com.placeholder.PlaceHolder;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {
    
    //Instanciamos variables con la clase de conexion a la base de datos
    conectar cc = new conectar();
    Connection cn = cc.conexion();
    
    public Login() {
        /*try {
            UIManager.setLookAndFeel(new WindowsClassicLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        initComponents();
        
        this.setLocationRelativeTo(null); //posicionamos el programa al centro de la pantalla
        
        //PlaceHolder holder = new PlaceHolder(txtusuario, "Ingresa tu usuario");
        //holder.inicializar();
        
        btnenter.requestFocus(); //damos focus al boton enter
    }
    
    //comprobamos de que el usuario y contraseña se encuentren en la base de datos
    void acceder(String usuario, String pass) {
        boolean existente = false;
        String tipo = "";
        String sql = "SELECT * FROM cuentas WHERE usuario='" + usuario + "' && contraseña='" + pass + "'";
        
        try 
        {  
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
                        
            while(rs.next())
            {
               existente = true; 
               //tipo = rs.getString("tipo");
            }
            
            if(existente == false)  //comprobar si no existe la cuenta
            {
                JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrectos", "Datos no validos", JOptionPane.ERROR_MESSAGE);
            }
            else if(existente == true)
            {
                JOptionPane.showMessageDialog(null, "Bienvenido "+usuario, "Datos Correctos", JOptionPane.INFORMATION_MESSAGE);
                /*JFR_Principal pr = new JFR_Principal();
                pr.setVisible(true);
                pr.us.setText(usuario);*/
                this.dispose();
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Login = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtusuario = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        txtcontraseña = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnenter = new javax.swing.JButton();
        Background = new javax.swing.JPanel();
        btncerrar = new javax.swing.JLabel();
        btnminimizar = new javax.swing.JLabel();
        Logo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Login.setBackground(new java.awt.Color(51, 51, 51));
        Login.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/avatar.png"))); // NOI18N
        Login.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 120, 140));

        jLabel2.setFont(new java.awt.Font("Browallia New", 0, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 0));
        jLabel2.setText("Contraseña:");
        Login.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, -1));

        jLabel3.setFont(new java.awt.Font("Browallia New", 0, 30)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 51, 0));
        jLabel3.setText("Usuario:");
        Login.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, -1));

        txtusuario.setFont(new java.awt.Font("Browallia New", 0, 22)); // NOI18N
        txtusuario.setForeground(new java.awt.Color(153, 153, 153));
        txtusuario.setText("Ingrese nombre de usuario");
        txtusuario.setBorder(null);
        txtusuario.setCaretColor(new java.awt.Color(255, 255, 255));
        txtusuario.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        txtusuario.setOpaque(false);
        txtusuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtusuarioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtusuarioFocusLost(evt);
            }
        });
        txtusuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtusuarioMouseClicked(evt);
            }
        });
        txtusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusuarioActionPerformed(evt);
            }
        });
        txtusuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtusuarioKeyTyped(evt);
            }
        });
        Login.add(txtusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 210, 30));

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        Login.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 250, 10));

        txtcontraseña.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txtcontraseña.setForeground(new java.awt.Color(153, 153, 153));
        txtcontraseña.setText("jPasswordField1");
        txtcontraseña.setBorder(null);
        txtcontraseña.setCaretColor(new java.awt.Color(255, 255, 255));
        txtcontraseña.setOpaque(false);
        txtcontraseña.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtcontraseñaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtcontraseñaFocusLost(evt);
            }
        });
        txtcontraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcontraseñaKeyPressed(evt);
            }
        });
        Login.add(txtcontraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, 210, 30));

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        Login.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 250, 20));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_customer_32px_1.png"))); // NOI18N
        jLabel4.setText("jLabel4");
        Login.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 30, 30));
        Login.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 345, 30, 20));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Key_32px.png"))); // NOI18N
        Login.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, 30));

        btnenter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Enter_OFF.png"))); // NOI18N
        btnenter.setBorder(null);
        btnenter.setBorderPainted(false);
        btnenter.setContentAreaFilled(false);
        btnenter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnenter.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Enter_ON.png"))); // NOI18N
        btnenter.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Enter_ON.png"))); // NOI18N
        btnenter.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Enter_ON.png"))); // NOI18N
        btnenter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnenterActionPerformed(evt);
            }
        });
        Login.add(btnenter, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 420, 100, 40));

        getContentPane().add(Login, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 330, 500));

        Background.setBackground(new java.awt.Color(0, 0, 0));
        Background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btncerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cerrar.png"))); // NOI18N
        btncerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btncerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btncerrarMouseClicked(evt);
            }
        });
        Background.add(btncerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(805, 15, -1, 20));

        btnminimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/puntero-hacia-abajo.png"))); // NOI18N
        btnminimizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnminimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnminimizarMouseClicked(evt);
            }
        });
        Background.add(btnminimizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(765, 15, -1, 20));

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Logo2.jpeg"))); // NOI18N
        Background.add(Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 550, 380));

        getContentPane().add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtusuarioActionPerformed

    private void btnminimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnminimizarMouseClicked
        //this.setState(this.ICONIFIED);
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_btnminimizarMouseClicked

    private void btncerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncerrarMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btncerrarMouseClicked

    private void txtusuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtusuarioMouseClicked
     
    }//GEN-LAST:event_txtusuarioMouseClicked

    private void txtusuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtusuarioFocusLost
        //cuando pierda el focus
        //si txt usuario esta vacio....
        if(txtusuario.getText().isEmpty())
        {
            //colocamos el texto ingrese nombre de usuario
            txtusuario.setText("Ingrese nombre de usuario");
            //le ponemos color RGB gris
            txtusuario.setForeground(new Color(153, 153,153));
        }
    }//GEN-LAST:event_txtusuarioFocusLost

    private void txtusuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtusuarioKeyTyped
        
    }//GEN-LAST:event_txtusuarioKeyTyped

    private void txtusuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtusuarioFocusGained
        //cuando gane el focus
        //si el texto que tiene este es ingrese nommbre de usuario....
        if(txtusuario.getText().equals("Ingrese nombre de usuario"))
        {
            //quitamos el texto
            txtusuario.setText("");
            //ponemos de color blanco para cuando escriba el usuario
            txtusuario.setForeground(Color.white);
        }
    }//GEN-LAST:event_txtusuarioFocusGained

    private void txtcontraseñaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcontraseñaFocusGained
        //cuando gane el focus
        //si el texto que tiene este es ingrese su contraseña....
        if(new String(txtcontraseña.getPassword()).equals("jPasswordField1"))
        {
            //quitamos el texto
            txtcontraseña.setText("");
            //ponemos de color blanco para cuando escriba la contraseña
            txtcontraseña.setForeground(Color.white);
        }
    }//GEN-LAST:event_txtcontraseñaFocusGained

    private void txtcontraseñaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcontraseñaFocusLost
        //cuando pierda el focus
        //si txtcontraseña esta vacio....
        if(txtcontraseña.getText().isEmpty())
        {
            //colocamos el texto ingrese nombre de usuario
            txtcontraseña.setText("jPasswordField1");
            //le ponemos color RGB gris
            txtcontraseña.setForeground(new Color(153, 153,153));
        }
    }//GEN-LAST:event_txtcontraseñaFocusLost

    private void btnenterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnenterActionPerformed
        String usu = txtusuario.getText();
        String pas = new String(txtcontraseña.getPassword());
        //lo cmparamos con esto ya que son sus placeholder
        if(usu.equals("Ingrese nombre de usuario")&& pas.equals("jPasswordField1"))
        {
            JOptionPane.showMessageDialog(null, "Ingrese sus datos de acceso", "Campos Incompletos", JOptionPane.ERROR_MESSAGE);
        }
        else if(usu.equals("Ingrese nombre de usuario"))
        {
            JOptionPane.showMessageDialog(null, "Ingrese su usuario", "Campos Incompletos", JOptionPane.ERROR_MESSAGE);
        }
          else if (pas.equals("jPasswordField1"))
        {
            JOptionPane.showMessageDialog(null, "Ingrese su contraseña", "Campos Incompletos", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            acceder(usu, pas);
        }
    }//GEN-LAST:event_btnenterActionPerformed

    private void txtcontraseñaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcontraseñaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) 
        {
            String usu = txtusuario.getText();
            String pas = new String(txtcontraseña.getPassword());
            //lo cmparamos con esto ya que son sus placeholder
            if(usu.equals("Ingrese nombre de usuario")&& pas.equals("jPasswordField1") || pas.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Ingrese sus datos de acceso", "Campos Incompletos", JOptionPane.ERROR_MESSAGE);
            }
            else if(usu.equals("Ingrese nombre de usuario"))
            {
                JOptionPane.showMessageDialog(null, "Ingrese su usuario", "Campos Incompletos", JOptionPane.ERROR_MESSAGE);
            }
              else if (pas.equals("jPasswordField1") || pas.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Ingrese su contraseña", "Campos Incompletos", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                acceder(usu, pas);
            }
        }
    }//GEN-LAST:event_txtcontraseñaKeyPressed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
       /* try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    private javax.swing.JPanel Login;
    private javax.swing.JLabel Logo;
    private javax.swing.JLabel btncerrar;
    private javax.swing.JButton btnenter;
    private javax.swing.JLabel btnminimizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPasswordField txtcontraseña;
    private javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables
}
