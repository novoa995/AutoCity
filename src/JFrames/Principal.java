//GODWITHME
package JFrames;

import JPanels.*;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import Animacion.Animacion;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;

public class Principal extends javax.swing.JFrame {
    
    //banderas para controlar los colores de botones cunado se den click
    boolean click_inv = false, click_herr = false, click_vent=false;

    Home hm = new Home();
    
    public Principal() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/Images/icono.jpeg")));
        this.setLocationRelativeTo(null); //posicionamos el frame al centro de la pantalla
        mostrar_panel(hm);
    } 
    
    //mostramos el panel a la ventana principal
    //Recibimos el panel que se quiere mostrar en la ventana principal
    void mostrar_panel(JPanel p)
    {
        p.setSize(700,600);
        p.setLocation(0, 0);
        
        Ventana.removeAll(); //eliminamos todos los componentes dentro del JPanel Ventana
        Ventana.add(p,BorderLayout.CENTER); //Agregamos el panel recibido a la ventana princcipal (JPanel Ventana)
        Ventana.revalidate();
        Ventana.repaint(); //repinta (actualiza, vuelve a pintar) la Ventana principal 
    }
    
    //agregamos animacion de color gris claro al pasar mouse
    void setGris(JPanel panel)
    {
        panel.setBackground(new Color(80,80,80));
    }
    
    void resetGris(JPanel panel)
    {
       panel.setBackground(new Color(51,51,51)); 
    }
    
     public void goToURL(String URL){
           if (java.awt.Desktop.isDesktopSupported()) {
            java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

            if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
                try {
                    java.net.URI uri = new java.net.URI(URL);
                    desktop.browse(uri);
                } catch (URISyntaxException | IOException ex) {
                    
                }
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Minimizar = new javax.swing.JLabel();
        Cerrar = new javax.swing.JLabel();
        PanelP = new javax.swing.JPanel();
        Ventas = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Inventario = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Herramientas = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Logout = new javax.swing.JLabel();
        Facebook = new javax.swing.JLabel();
        Logo = new javax.swing.JLabel();
        Background_logo = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        PHerramientas = new javax.swing.JPanel();
        Menu = new javax.swing.JLabel();
        Ag_Exist = new javax.swing.JLabel();
        Dism_Exist = new javax.swing.JLabel();
        Ag_Prod = new javax.swing.JLabel();
        Ventana = new javax.swing.JPanel();
        Background = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(950, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Minimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/puntero-hacia-abajo.png"))); // NOI18N
        Minimizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Minimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MinimizarMouseClicked(evt);
            }
        });
        getContentPane().add(Minimizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(875, 8, -1, -1));

        Cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cerrar.png"))); // NOI18N
        Cerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Cerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CerrarMouseClicked(evt);
            }
        });
        getContentPane().add(Cerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 10, -1, 20));

        PanelP.setBackground(new java.awt.Color(51, 51, 51));
        PanelP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Ventas.setBackground(new java.awt.Color(51, 51, 51));
        Ventas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Ventas.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                VentasMouseMoved(evt);
            }
        });
        Ventas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VentasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                VentasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                VentasMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                VentasMousePressed(evt);
            }
        });
        Ventas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 153, 51));
        jLabel7.setText("Ventas");
        Ventas.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/dolar.png"))); // NOI18N
        Ventas.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 40));

        PanelP.add(Ventas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 250, 40));

        Inventario.setBackground(new java.awt.Color(51, 51, 51));
        Inventario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Inventario.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                InventarioMouseMoved(evt);
            }
        });
        Inventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InventarioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                InventarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                InventarioMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                InventarioMousePressed(evt);
            }
        });
        Inventario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 153, 51));
        jLabel2.setText("Inventario");
        Inventario.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/shopping-list.png"))); // NOI18N
        Inventario.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 40));

        PanelP.add(Inventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 250, 40));

        Herramientas.setBackground(new java.awt.Color(51, 51, 51));
        Herramientas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Herramientas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HerramientasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                HerramientasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                HerramientasMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                HerramientasMousePressed(evt);
            }
        });
        Herramientas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 153, 51));
        jLabel1.setText("Herramientas");
        Herramientas.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ajustes.png"))); // NOI18N
        Herramientas.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        PanelP.add(Herramientas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 250, 40));

        Logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logout-Oscuro.png"))); // NOI18N
        Logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LogoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LogoutMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                LogoutMousePressed(evt);
            }
        });
        PanelP.add(Logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 550, 40, 30));

        Facebook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/facebook.png"))); // NOI18N
        Facebook.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Facebook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FacebookMouseClicked(evt);
            }
        });
        PanelP.add(Facebook, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 500, 40, 40));

        Logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Logo Panel.jpeg"))); // NOI18N
        Logo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Logo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogoMouseClicked(evt);
            }
        });
        PanelP.add(Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, -20, 160, -1));

        Background_logo.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout Background_logoLayout = new javax.swing.GroupLayout(Background_logo);
        Background_logo.setLayout(Background_logoLayout);
        Background_logoLayout.setHorizontalGroup(
            Background_logoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        Background_logoLayout.setVerticalGroup(
            Background_logoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );

        PanelP.add(Background_logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 140));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/conectado.png"))); // NOI18N
        PanelP.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 545, 30, 40));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 153, 51));
        jLabel6.setText("Usuario");
        PanelP.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 552, 60, 30));

        getContentPane().add(PanelP, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 600));

        PHerramientas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PHerramientas.setOpaque(false);
        PHerramientas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PHerramientasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PHerramientasMouseExited(evt);
            }
        });
        PHerramientas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/menu.png"))); // NOI18N
        Menu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PHerramientas.add(Menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 30, 30));

        Ag_Exist.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/signo-de-suma-matematico.png"))); // NOI18N
        Ag_Exist.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PHerramientas.add(Ag_Exist, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, 30));

        Dism_Exist.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/simbolo-de-resta (1).png"))); // NOI18N
        Dism_Exist.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PHerramientas.add(Dism_Exist, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, -1, 30));

        Ag_Prod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/tienda-online.png"))); // NOI18N
        Ag_Prod.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PHerramientas.add(Ag_Prod, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 6, -1, 20));

        getContentPane().add(PHerramientas, new org.netbeans.lib.awtextra.AbsoluteConstraints(923, 570, 150, 30));

        Ventana.setOpaque(false);

        javax.swing.GroupLayout VentanaLayout = new javax.swing.GroupLayout(Ventana);
        Ventana.setLayout(VentanaLayout);
        VentanaLayout.setHorizontalGroup(
            VentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );
        VentanaLayout.setVerticalGroup(
            VentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        getContentPane().add(Ventana, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 700, 600));

        Background.setBackground(new java.awt.Color(0, 0, 0));
        Background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void InventarioMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InventarioMouseMoved
        //evento que ocurre mientras mueves el cursor dentro del componente
    }//GEN-LAST:event_InventarioMouseMoved

    private void HerramientasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HerramientasMouseEntered
        //evento que ocurre cuando ingresas el cursor dentro del componente
        
        //Habilitamos color
        setGris(Herramientas);
    }//GEN-LAST:event_HerramientasMouseEntered

    private void InventarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InventarioMouseEntered
        //evento que ocurre cuando ingresas el cursor dentro del componente
        
        //cambiamos a gris claro el background
        setGris(Inventario);
    }//GEN-LAST:event_InventarioMouseEntered

    private void InventarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InventarioMouseExited
        //evento que ocurre cuando sacas el cursor fuera del componente
        //si NO esta presionado el boton inventario...
        if(click_inv == false)
        {
            //desabilitamos color
            resetGris(Inventario);
        }
    }//GEN-LAST:event_InventarioMouseExited

    private void HerramientasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HerramientasMouseExited
        //evento que ocurre cuando sacas el cursor fuera del componente
        
        //si NO esta presionado un boton
        if(click_herr == false)
        {
            //desabilitamos color
            resetGris(Herramientas);
        }
    }//GEN-LAST:event_HerramientasMouseExited

    private void InventarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InventarioMousePressed
        //evento que se activa cuando se presiona click sin soltar
        //desabilitamos color
        resetGris(Inventario);
    }//GEN-LAST:event_InventarioMousePressed

    private void InventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InventarioMouseClicked
        //evento que se activa cuando se presiona y se suelta el click          
        
        //activamos la bandera de que se dio click en inventario y desabilitamos las otras
        click_inv = true;
        click_herr = false;
        click_vent = false;
        
        //habilitamos color        
        setGris(Inventario);
        //desabilitamos color
        resetGris(Herramientas);
        resetGris(Ventas);
        
        //mandamos a mostrar el Jpanel inventario
        //new Inventario(this);
        //pasamos como parametro el obj de esta clase
        Inventario inv = new Inventario(this);
        mostrar_panel(inv);
    }//GEN-LAST:event_InventarioMouseClicked

    private void HerramientasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HerramientasMousePressed
        //evento que se activa cuando se presiona click sin soltar
        
        //desabilitamos color
        resetGris(Herramientas);
    }//GEN-LAST:event_HerramientasMousePressed

    private void HerramientasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HerramientasMouseClicked
        //evento que se activa cuando se presiona y se suelta el click   
        
        //activamos la bandera de que se dio click en herramientas y desabilitamos las otras
        click_herr = true;
        click_inv = false;
        click_vent = false;
        
        //habilitamos color
        setGris(Herramientas);
        //desabilitamos color
        resetGris(Inventario);
        resetGris(Ventas);

    }//GEN-LAST:event_HerramientasMouseClicked

    private void CerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CerrarMouseClicked
        System.exit(0);
    }//GEN-LAST:event_CerrarMouseClicked

    private void MinimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinimizarMouseClicked
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_MinimizarMouseClicked

    private void LogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutMouseEntered
        //ponemos la imagen de logout claro
        Logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logout-Claro.png")));
    }//GEN-LAST:event_LogoutMouseEntered

    private void LogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutMouseExited
        //ponemos la imagen de logout Oscuro
        Logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logout-Oscuro.png")));
    }//GEN-LAST:event_LogoutMouseExited

    private void LogoutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutMousePressed
        //ponemos la imagen de logout Oscuro
        Logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logout-Oscuro.png")));
    }//GEN-LAST:event_LogoutMousePressed

    private void LogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutMouseClicked
        //ponemos la imagen de logout claro
        Logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logout-Claro.png")));
    }//GEN-LAST:event_LogoutMouseClicked

    private void LogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoMouseClicked
        resetGris(Inventario);        
        mostrar_panel(hm);
    }//GEN-LAST:event_LogoMouseClicked

    private void PHerramientasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PHerramientasMouseEntered
        Animacion.mover_izquierda(923, 800, 2, 1, PHerramientas);
    }//GEN-LAST:event_PHerramientasMouseEntered

    private void PHerramientasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PHerramientasMouseExited
        Animacion.mover_derecha(800, 923, 2, 1, PHerramientas);
    }//GEN-LAST:event_PHerramientasMouseExited

    private void VentasMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VentasMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_VentasMouseMoved

    private void VentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VentasMouseClicked
        //evento que se activa cuando se presiona y se suelta el click          
        
        //activamos la bandera de que se dio click en inventario y desabilitamos las otras
        click_vent = true;        
        click_inv = false;
        click_herr = false;
        //habilitamos color        
        setGris(Ventas);
        //desabilitamos color
        resetGris(Inventario);
        resetGris(Herramientas);
        //pasamos como parametro el obj de esta clase
        Ventas vent = new Ventas(this);
        mostrar_panel(vent);
    }//GEN-LAST:event_VentasMouseClicked

    private void VentasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VentasMouseEntered
        //evento que ocurre cuando ingresas el cursor dentro del componente
        
        //cambiamos a gris claro el background
        setGris(Ventas);
    }//GEN-LAST:event_VentasMouseEntered

    private void VentasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VentasMouseExited
        //evento que ocurre cuando sacas el cursor fuera del componente
        //si NO esta presionado el boton ventas...
        if(click_vent == false)
        {
            //desabilitamos color
            resetGris(Ventas);
        }
    }//GEN-LAST:event_VentasMouseExited

    private void VentasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VentasMousePressed
        //evento que se activa cuando se presiona click sin soltar
        //desabilitamos color
        resetGris(Ventas);        
    }//GEN-LAST:event_VentasMousePressed

    private void FacebookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FacebookMouseClicked
        goToURL("https://www.facebook.com/Autocity-Luces-y-Accesorios-133853987263515/?ref=br_rs");
    }//GEN-LAST:event_FacebookMouseClicked


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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Ag_Exist;
    private javax.swing.JLabel Ag_Prod;
    private javax.swing.JPanel Background;
    private javax.swing.JPanel Background_logo;
    private javax.swing.JLabel Cerrar;
    private javax.swing.JLabel Dism_Exist;
    private javax.swing.JLabel Facebook;
    private javax.swing.JPanel Herramientas;
    private javax.swing.JPanel Inventario;
    private javax.swing.JLabel Logo;
    private javax.swing.JLabel Logout;
    private javax.swing.JLabel Menu;
    private javax.swing.JLabel Minimizar;
    private javax.swing.JPanel PHerramientas;
    private javax.swing.JPanel PanelP;
    public static javax.swing.JPanel Ventana;
    private javax.swing.JPanel Ventas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    // End of variables declaration//GEN-END:variables
}
