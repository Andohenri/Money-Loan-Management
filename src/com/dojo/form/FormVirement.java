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
public class FormVirement extends javax.swing.JPanel {

    public FormVirement(Type type) {
        initComponents();
        setOpaque(false);
        if (type == Type.UPDATE){
            jLabel1.setText("Mettre à jour un virement");
        }else{
            jLabel1.setText("Enregistrer un virement");
            btnVirDelete.setVisible(false);
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
        txtNum1 = new com.dojo.component.TextField();
        txtNum2 = new com.dojo.component.TextField();
        txtMontant = new com.dojo.component.TextField();
        jLabel2 = new javax.swing.JLabel();
        btnVirDelete = new com.k33ptoo.components.KButton();
        btnVirRegister = new com.k33ptoo.components.KButton();

        setBackground(new java.awt.Color(226, 238, 242));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(67, 150, 165));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Enregistrer un nouveau virement");

        txtNum1.setBordColor(new java.awt.Color(129, 202, 178));
        txtNum1.setHints("N°compte débiteur");

        txtNum2.setBordColor(new java.awt.Color(129, 202, 178));
        txtNum2.setHints("N°compte créditeur");

        txtMontant.setBordColor(new java.awt.Color(129, 202, 178));
        txtMontant.setHints("Montant (Ariary)");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dojo/icon/close.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        btnVirDelete.setText("Supprimer");
        btnVirDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVirDelete.setkEndColor(new java.awt.Color(255, 54, 32));
        btnVirDelete.setkHoverEndColor(new java.awt.Color(245, 78, 72));
        btnVirDelete.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        btnVirDelete.setkHoverStartColor(new java.awt.Color(227, 53, 70));
        btnVirDelete.setkStartColor(new java.awt.Color(242, 20, 48));

        btnVirRegister.setText("Enregistrer");
        btnVirRegister.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVirRegister.setkEndColor(new java.awt.Color(47, 190, 190));
        btnVirRegister.setkHoverEndColor(new java.awt.Color(60, 154, 190));
        btnVirRegister.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        btnVirRegister.setkHoverStartColor(new java.awt.Color(60, 154, 190));
        btnVirRegister.setkStartColor(new java.awt.Color(47, 190, 190));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtMontant, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtNum1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNum2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnVirDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                        .addGap(26, 26, 26)
                        .addComponent(btnVirRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addComponent(txtNum1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtNum2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtMontant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVirRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVirDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        GlassPanePopup.closePopupLast();
    }//GEN-LAST:event_jLabel2MouseClicked

    public void eventAdd(ActionListener event){
        btnVirRegister.addActionListener(event);
    }
    public void eventUpdate(ActionListener event){
        btnVirRegister.addActionListener(event);
    }
    public void eventDelete(ActionListener event){
        btnVirDelete.addActionListener(event);
    }
    public String getNum1(){
        return txtNum1.getText();
    }
    public String getNum2(){
        return txtNum2.getText();
    }
    public String getMontant(){
        return txtMontant.getText();
    }
    public void setNum1(String nom){
        txtNum1.setText(nom);
    }
    public void setNum2(String prenom){
        txtNum2.setText(prenom);
    }
    public void setMontant(String phoneNumber){
        txtMontant.setText(phoneNumber);
    }
    
    public enum Type{
        UPDATE, INSERT
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.k33ptoo.components.KButton btnVirDelete;
    private com.k33ptoo.components.KButton btnVirRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private com.dojo.component.TextField txtMontant;
    private com.dojo.component.TextField txtNum1;
    private com.dojo.component.TextField txtNum2;
    // End of variables declaration//GEN-END:variables
}
