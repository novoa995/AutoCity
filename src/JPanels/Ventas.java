//GODWITHME
package JPanels;

import java.awt.Color;
import javax.swing.BorderFactory;
import JFrames.AdmExistencia;
import JFrames.Principal;
import java.awt.BorderLayout;
import javax.swing.JPanel;
        
public class Ventas extends javax.swing.JPanel {
    
    Principal pr;
    
    
    public Ventas(Principal pr) {
        initComponents();
       
        
        //igualamos el objeto de la clase principal recibido, para asi poder abrir ventanas desde esta
        this.pr = pr;
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
    
    //ponemos en COLOR OSCURO el boton Registrar Producto 
    void setRegVent()
    {
       //ponemos borde al panel
        RegVent.setBorder(BorderFactory.createLineBorder(new Color(255,51,0)));
        //cambiamos a imagen oscuro
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/bolsa-de-la-compra (1).png")));
        //cambiamos a texto oscuro
        jLabel1.setForeground(new Color(255,51,0)); 
    }
    
    //ponemos COLOR CLARO al boton Registrar Producto
    void resetRegVent()
    {
        //ponemos borde al panel        
        RegVent.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
        //cambiamos a imagen oscuro        
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/bolsa-de-la-compra.png")));
        //cambiamos a texto claro
        jLabel1.setForeground(new Color(255,102,51));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Titulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        RegVent = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));
        setOpaque(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Titulo.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        Titulo.setForeground(new java.awt.Color(255, 51, 0));
        Titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titulo.setText("Ventas");
        add(Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 30, 690, -1));

        jSeparator1.setBackground(new java.awt.Color(80, 80, 80));
        jSeparator1.setForeground(new java.awt.Color(80, 80, 80));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 620, 10));

        RegVent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        RegVent.setOpaque(false);
        RegVent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RegVentMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                RegVentMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                RegVentMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                RegVentMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                RegVentMouseReleased(evt);
            }
        });
        RegVent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Registrar Venta");
        RegVent.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 144, 130, -1));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/bolsa-de-la-compra.png"))); // NOI18N
        RegVent.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 130, 130));

        add(RegVent, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 190, 180));
    }// </editor-fold>//GEN-END:initComponents

    private void RegVentMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegVentMouseEntered
        setRegVent();
    }//GEN-LAST:event_RegVentMouseEntered

    private void RegVentMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegVentMouseExited
        resetRegVent();
    }//GEN-LAST:event_RegVentMouseExited

    private void RegVentMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegVentMouseReleased

    }//GEN-LAST:event_RegVentMouseReleased

    private void RegVentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegVentMouseClicked
        RegVentas vn = new RegVentas(pr);
        mostrar_panel(vn);
    }//GEN-LAST:event_RegVentMouseClicked

    private void RegVentMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegVentMousePressed
        resetRegVent();
    }//GEN-LAST:event_RegVentMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel RegVent;
    private javax.swing.JLabel Titulo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
