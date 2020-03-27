//GODWITHME
package JPanels;

import JFrames.Principal;
import javax.swing.JPanel;

public class RegVentas extends javax.swing.JPanel {

    public RegVentas(Principal pr) {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Titulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        Codigo1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        InfoProd = new javax.swing.JPanel();
        lblProveedor = new javax.swing.JLabel();
        txtProv = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        lblExistencia = new javax.swing.JLabel();
        txtExistencia = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        txtDescripcion = new javax.swing.JTextArea();
        lblDescripcion = new javax.swing.JLabel();
        txtExistencia1 = new javax.swing.JTextField();
        lblExistencia1 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(700, 570));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Titulo.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        Titulo.setForeground(new java.awt.Color(255, 51, 0));
        Titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titulo.setText("Registrar Ventas");
        add(Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 700, 40));

        jSeparator1.setBackground(new java.awt.Color(80, 80, 80));
        jSeparator1.setForeground(new java.awt.Color(80, 80, 80));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 620, 10));

        Codigo1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Codigo1.setForeground(new java.awt.Color(255, 153, 51));
        Codigo1.setText("Código del producto:");
        add(Codigo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 150, -1));

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
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });
        add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 200, 20));

        jSeparator2.setBackground(new java.awt.Color(255, 51, 0));
        jSeparator2.setForeground(new java.awt.Color(255, 51, 0));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, 200, 10));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/codigo-de-barras.png"))); // NOI18N
        jLabel1.setToolTipText("Buscar producto");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, -1, -1));

        InfoProd.setBackground(new java.awt.Color(255, 51, 0));
        InfoProd.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 2), " Información del producto ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16), new java.awt.Color(204, 204, 204))); // NOI18N
        InfoProd.setForeground(new java.awt.Color(255, 51, 0));
        InfoProd.setOpaque(false);
        InfoProd.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblProveedor.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblProveedor.setForeground(new java.awt.Color(255, 153, 51));
        lblProveedor.setText("Proveedor:");
        InfoProd.add(lblProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 30, 77, -1));

        txtProv.setEditable(false);
        txtProv.setFont(new java.awt.Font("Browallia New", 0, 20)); // NOI18N
        txtProv.setForeground(new java.awt.Color(255, 255, 255));
        txtProv.setBorder(null);
        txtProv.setCaretColor(new java.awt.Color(255, 255, 255));
        txtProv.setOpaque(false);
        txtProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProvActionPerformed(evt);
            }
        });
        InfoProd.add(txtProv, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 120, 20));

        jSeparator5.setBackground(new java.awt.Color(255, 51, 0));
        jSeparator5.setForeground(new java.awt.Color(255, 51, 0));
        InfoProd.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 120, 10));

        lblExistencia.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblExistencia.setForeground(new java.awt.Color(255, 153, 51));
        lblExistencia.setText("Existencia:");
        InfoProd.add(lblExistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 70, 70, -1));

        txtExistencia.setEditable(false);
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
        InfoProd.add(txtExistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 70, 20));

        jSeparator4.setBackground(new java.awt.Color(255, 51, 0));
        jSeparator4.setForeground(new java.awt.Color(255, 51, 0));
        InfoProd.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 70, 10));

        txtDescripcion.setEditable(false);
        txtDescripcion.setColumns(20);
        txtDescripcion.setFont(new java.awt.Font("Browallia New", 0, 20)); // NOI18N
        txtDescripcion.setForeground(new java.awt.Color(255, 255, 255));
        txtDescripcion.setRows(5);
        txtDescripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 0)));
        txtDescripcion.setCaretColor(new java.awt.Color(255, 255, 255));
        txtDescripcion.setOpaque(false);
        InfoProd.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 210, 90));

        lblDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblDescripcion.setForeground(new java.awt.Color(255, 153, 51));
        lblDescripcion.setText("Descripción:");
        InfoProd.add(lblDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 90, -1));

        txtExistencia1.setEditable(false);
        txtExistencia1.setFont(new java.awt.Font("Browallia New", 0, 20)); // NOI18N
        txtExistencia1.setForeground(new java.awt.Color(255, 255, 255));
        txtExistencia1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtExistencia1.setBorder(null);
        txtExistencia1.setCaretColor(new java.awt.Color(255, 255, 255));
        txtExistencia1.setOpaque(false);
        txtExistencia1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtExistencia1ActionPerformed(evt);
            }
        });
        InfoProd.add(txtExistencia1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 70, 20));

        lblExistencia1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblExistencia1.setForeground(new java.awt.Color(255, 153, 51));
        lblExistencia1.setText("Precio:             $");
        InfoProd.add(lblExistencia1, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 110, 110, -1));

        jSeparator6.setBackground(new java.awt.Color(255, 51, 0));
        jSeparator6.setForeground(new java.awt.Color(255, 51, 0));
        InfoProd.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 70, 10));

        add(InfoProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 480, 150));
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        //val.longitud(evt, txtCodigo, 50);
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        //lblDisminuir.setOpaque(false);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void txtProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProvActionPerformed

    private void txtExistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtExistenciaActionPerformed

    }//GEN-LAST:event_txtExistenciaActionPerformed

    private void txtExistencia1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtExistencia1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtExistencia1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Codigo1;
    private javax.swing.JPanel InfoProd;
    private javax.swing.JLabel Titulo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblExistencia;
    private javax.swing.JLabel lblExistencia1;
    private javax.swing.JLabel lblProveedor;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtExistencia;
    private javax.swing.JTextField txtExistencia1;
    private javax.swing.JTextField txtProv;
    // End of variables declaration//GEN-END:variables
}
