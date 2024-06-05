/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.dojo.form;

import com.dojo.swing.GlassPanePopup;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;

/**
 *
 * @author Ando Henri
 */
public class FormRendu extends javax.swing.JPanel {

    public FormRendu(Type type) {
        initComponents();
        setOpaque(false);
        if (type == Type.UPDATE){
            jLabel1.setText("Mettre à jour un payment d'un prêt");
        }else{
            jLabel1.setText("Payé un prêt");
            btnPreDelete.setVisible(false);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D)g.create();
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setColor(getBackground());
        g2D.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        g2D.dispose();
        super.paintComponent(g);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtNumPret = new com.dojo.component.TextField();
        txtMontantARendre = new com.dojo.component.TextField();
        jLabel2 = new javax.swing.JLabel();
        btnPreDelete = new com.k33ptoo.components.KButton();
        btnPreRegister = new com.k33ptoo.components.KButton();

        setBackground(new java.awt.Color(226, 238, 242));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(67, 150, 165));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Enregistrer un nouveau prêt");

        txtNumPret.setBordColor(new java.awt.Color(129, 202, 178));
        txtNumPret.setHints("N°prêt");

        txtMontantARendre.setBordColor(new java.awt.Color(129, 202, 178));
        txtMontantARendre.setHints("Montant à rendre (Ariary)");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dojo/icon/close.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        btnPreDelete.setText("Supprimer");
        btnPreDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPreDelete.setkEndColor(new java.awt.Color(255, 54, 32));
        btnPreDelete.setkHoverEndColor(new java.awt.Color(245, 78, 72));
        btnPreDelete.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        btnPreDelete.setkHoverStartColor(new java.awt.Color(227, 53, 70));
        btnPreDelete.setkStartColor(new java.awt.Color(242, 20, 48));

        btnPreRegister.setText("Enregistrer");
        btnPreRegister.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPreRegister.setkEndColor(new java.awt.Color(47, 190, 190));
        btnPreRegister.setkHoverEndColor(new java.awt.Color(60, 154, 190));
        btnPreRegister.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        btnPreRegister.setkHoverStartColor(new java.awt.Color(60, 154, 190));
        btnPreRegister.setkStartColor(new java.awt.Color(47, 190, 190));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                        .addComponent(txtMontantARendre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNumPret, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnPreDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPreRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtNumPret, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 27, Short.MAX_VALUE)
                .addComponent(txtMontantARendre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPreRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPreDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        GlassPanePopup.closePopupLast();
    }//GEN-LAST:event_jLabel2MouseClicked

    public void eventAdd(ActionListener event){
        btnPreRegister.addActionListener(event);
    }
    public void eventUpdate(ActionListener event){
        btnPreRegister.addActionListener(event);
    }
    public void eventDelete(ActionListener event){
        btnPreDelete.addActionListener(event);
    }
    
    public String getNumPret(){
        return txtNumPret.getText();
    }
    public String getMontant(){
        return txtMontantARendre.getText();
    }
    public void setNumPret(String numPret){
        txtNumPret.setText(numPret);
    }
    public void setMontant(int montant){
        txtMontantARendre.setText(String.valueOf(montant));
    }
    
    public enum Type{
        UPDATE, INSERT
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.k33ptoo.components.KButton btnPreDelete;
    private com.k33ptoo.components.KButton btnPreRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private com.dojo.component.TextField txtMontantARendre;
    private com.dojo.component.TextField txtNumPret;
    // End of variables declaration//GEN-END:variables
}
