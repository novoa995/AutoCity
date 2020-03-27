//GODWITHME
package JPanels;

import java.awt.Color;
import javax.swing.BorderFactory;
import Animacion.Animacion;
import JFrames.AdmExistencia;
import JFrames.Principal;
import java.awt.BorderLayout;
import javax.swing.JPanel;
        
public class Inventario extends javax.swing.JPanel {
    
    Principal pr;
    ConsProds cons = new ConsProds(pr);
    
    static boolean OpsExistencia;
    
    public Inventario(Principal pr) {
        initComponents();
       
        //desdebilitamos para que siempre que se abra la ventana mantenga escondido el panel opsExistencia al abrir esta ventana
        this.OpsExistencia = false;
        
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
    void setRegProd()
    {
       //ponemos borde al panel
        RegProd.setBorder(BorderFactory.createLineBorder(new Color(255,51,0)));
        //cambiamos a imagen oscuro
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/RegProd-oscuro.png")));
        //cambiamos a texto oscuro
        jLabel1.setForeground(new Color(255,51,0)); 
    }
    
    //ponemos COLOR CLARO al boton Registrar Producto
    void resetRegProd()
    {
        //ponemos borde al panel        
        RegProd.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
        //cambiamos a imagen oscuro        
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/RegProd-Claro.png")));
        //cambiamos a texto claro
        jLabel1.setForeground(new Color(255,102,51));
    }
    
    //ponemos en COLOR OSCURO el boton Consultar 
    void setConsultar()
    {
       //ponemos borde al panel
        Consultar.setBorder(BorderFactory.createLineBorder(new Color(255,51,0)));
        //cambiamos a imagen oscuro
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ConsProd-Oscuro.png")));
        //cambiamos a texto oscuro
        jLabel2.setForeground(new Color(255,51,0)); 
    }
    
    //ponemos COLOR CLARO al boton Registrar Producto
    void resetConsultar()
    {
        //ponemos borde al panel        
        Consultar.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
        //cambiamos a imagen oscuro        
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ConsProd-Claro.png")));
        //cambiamos a texto claro
        jLabel2.setForeground(new Color(255,102,51));
    }
    
    //ponemos en COLOR OSCURO el boton AdminExist
    void setExistencia()
    {
       //ponemos borde al panel
        AdminExist.setBorder(BorderFactory.createLineBorder(new Color(255,51,0)));
        //cambiamos a imagen oscuro
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/AdminExist-Oscuro2.png")));
        //cambiamos a texto oscuro
        jLabel5.setForeground(new Color(255,51,0)); 
    }
    
    //ponemos COLOR CLARO al boton Registrar Producto
    void resetExistencia()
    {
        //ponemos borde al panel        
        AdminExist.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
        //cambiamos a imagen oscuro        
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/AdminExist-Claro2.png")));
        //cambiamos a texto claro
        jLabel5.setForeground(new Color(255,102,51));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Titulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        RegProd = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Consultar = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        AdminExist = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        OpExist = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));
        setOpaque(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Titulo.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        Titulo.setForeground(new java.awt.Color(255, 51, 0));
        Titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titulo.setText("Inventario");
        add(Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 30, 690, -1));

        jSeparator1.setBackground(new java.awt.Color(80, 80, 80));
        jSeparator1.setForeground(new java.awt.Color(80, 80, 80));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 620, 10));

        RegProd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        RegProd.setOpaque(false);
        RegProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RegProdMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                RegProdMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                RegProdMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                RegProdMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                RegProdMouseReleased(evt);
            }
        });
        RegProd.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 51));
        jLabel1.setText("Registrar Producto");
        RegProd.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/RegProd-Claro.png"))); // NOI18N
        RegProd.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 130, 110));

        add(RegProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 190, 180));

        Consultar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Consultar.setOpaque(false);
        Consultar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ConsultarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ConsultarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ConsultarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ConsultarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ConsultarMouseReleased(evt);
            }
        });
        Consultar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 51));
        jLabel2.setText("Consultar");
        Consultar.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, -1));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ConsProd-Claro.png"))); // NOI18N
        Consultar.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 130, 110));

        add(Consultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 140, 190, 180));

        AdminExist.setBackground(new java.awt.Color(0, 0, 0));
        AdminExist.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AdminExist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AdminExistMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AdminExistMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AdminExistMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                AdminExistMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                AdminExistMouseReleased(evt);
            }
        });
        AdminExist.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 102, 51));
        jLabel5.setText("Administrar Existencia");
        AdminExist.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/AdminExist-Claro2.png"))); // NOI18N
        AdminExist.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 130, 110));

        add(AdminExist, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, 190, 180));

        OpExist.setBackground(new java.awt.Color(51, 51, 51));
        OpExist.setOpaque(false);
        OpExist.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator2.setBackground(new java.awt.Color(255, 102, 51));
        jSeparator2.setForeground(new java.awt.Color(255, 102, 51));
        OpExist.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 170, -1));

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 102, 51));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/boton-de-suma.png"))); // NOI18N
        jLabel8.setText("      Aumentar");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.setOpaque(true);
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel8MouseExited(evt);
            }
        });
        OpExist.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 30));

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 102, 51));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/menos.png"))); // NOI18N
        jLabel7.setText("      Disminuir");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.setOpaque(true);
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel7MouseExited(evt);
            }
        });
        OpExist.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 32, 170, 30));

        add(OpExist, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 488, 170, 62));
    }// </editor-fold>//GEN-END:initComponents

    private void RegProdMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegProdMouseEntered
        setRegProd();
    }//GEN-LAST:event_RegProdMouseEntered

    private void RegProdMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegProdMouseExited
        resetRegProd();
    }//GEN-LAST:event_RegProdMouseExited

    private void RegProdMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegProdMouseReleased

    }//GEN-LAST:event_RegProdMouseReleased

    private void RegProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegProdMouseClicked
        RegProd rp = new RegProd(pr);
        mostrar_panel(rp);
    }//GEN-LAST:event_RegProdMouseClicked

    private void RegProdMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegProdMousePressed
        resetRegProd();
    }//GEN-LAST:event_RegProdMousePressed

    private void ConsultarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ConsultarMouseClicked
        mostrar_panel(cons);
    }//GEN-LAST:event_ConsultarMouseClicked

    private void ConsultarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ConsultarMouseEntered
        setConsultar();
    }//GEN-LAST:event_ConsultarMouseEntered

    private void ConsultarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ConsultarMouseExited
        resetConsultar();
    }//GEN-LAST:event_ConsultarMouseExited

    private void ConsultarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ConsultarMousePressed
        resetConsultar();
    }//GEN-LAST:event_ConsultarMousePressed

    private void ConsultarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ConsultarMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_ConsultarMouseReleased

    private void AdminExistMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AdminExistMouseClicked
        setExistencia();
        //abrimos la ventana de Administrar Existencia
        AdmExistencia aum = new AdmExistencia();
        aum.setVisible(true);
        
        //Movemos el panel de agregar/disminuir
        //si el panel de OpsExistencia ESTA ESCONDIDO..
        /*if(OpsExistencia == false)
        {
            Animacion.mover_derecha(90, 260, 1, 1, OpExist);
            OpsExistencia = true;
        }
        //si el panel de OpsExistencia ESTA VISIBLE...
        else
        {
            Animacion.mover_izquierda(260, 90, 1, 1, OpExist);
            OpsExistencia = false;
        }*/
    }//GEN-LAST:event_AdminExistMouseClicked

    private void AdminExistMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AdminExistMouseEntered
        setExistencia();
    }//GEN-LAST:event_AdminExistMouseEntered

    private void AdminExistMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AdminExistMouseExited
        resetExistencia();
    }//GEN-LAST:event_AdminExistMouseExited

    private void AdminExistMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AdminExistMousePressed
        resetExistencia();
    }//GEN-LAST:event_AdminExistMousePressed

    private void AdminExistMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AdminExistMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_AdminExistMouseReleased

    private void jLabel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseEntered
        //cunado se encime el raton que coloree gris
        jLabel8.setBackground(new Color (51,51,51));
    }//GEN-LAST:event_jLabel8MouseEntered

    private void jLabel8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseExited
        //cunado se salga el raton que coloree negro
        jLabel8.setBackground(new Color (0,0,0));
    }//GEN-LAST:event_jLabel8MouseExited

    private void jLabel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseEntered
        //cunado se encime el raton que coloree gris
        jLabel7.setBackground(new Color (51,51,51));
    }//GEN-LAST:event_jLabel7MouseEntered

    private void jLabel7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseExited
        //cunado salga el raton que coloree negro
        jLabel7.setBackground(new Color (0,0,0));
    }//GEN-LAST:event_jLabel7MouseExited

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        AdmExistencia aum = new AdmExistencia();
        aum.setVisible(true);
    }//GEN-LAST:event_jLabel8MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AdminExist;
    private javax.swing.JPanel Consultar;
    private javax.swing.JPanel OpExist;
    private javax.swing.JPanel RegProd;
    private javax.swing.JLabel Titulo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
