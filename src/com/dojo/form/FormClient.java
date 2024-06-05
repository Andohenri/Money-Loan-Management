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
public class FormClient extends javax.swing.JPanel {

    public FormClient(Type type) {
        initComponents();
        setOpaque(false);
        if (type == Type.UPDATE){
            jLabel1.setText("Mettre à jour un client");
        }else{
            jLabel1.setText("Enregistrer un nouveau client");
            btnCliDelete.setVisible(false);
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
        txtNom = new com.dojo.component.TextField();
        txtPrenom = new com.dojo.component.TextField();
        txtPhone = new com.dojo.component.TextField();
        txtMail = new com.dojo.component.TextField();
        btnCliRegister = new com.k33ptoo.components.KButton();
        txtMail1 = new com.dojo.component.TextField();
        jLabel2 = new javax.swing.JLabel();
        btnCliDelete = new com.k33ptoo.components.KButton();

        setBackground(new java.awt.Color(226, 238, 242));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(67, 150, 165));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Enregistrer un nouveau client");

        txtNom.setBordColor(new java.awt.Color(129, 202, 178));
        txtNom.setHints("Nom");

        txtPrenom.setBordColor(new java.awt.Color(129, 202, 178));
        txtPrenom.setHints("Prénom");

        txtPhone.setBordColor(new java.awt.Color(129, 202, 178));
        txtPhone.setHints("Téléphone");

        txtMail.setBordColor(new java.awt.Color(129, 202, 178));
        txtMail.setHints("E-mail");

        btnCliRegister.setText("Enregistrer");
        btnCliRegister.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCliRegister.setkBorderRadius(8);
        btnCliRegister.setkEndColor(new java.awt.Color(67, 206, 162));
        btnCliRegister.setkHoverEndColor(new java.awt.Color(67, 206, 162));
        btnCliRegister.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        btnCliRegister.setkHoverStartColor(new java.awt.Color(24, 163, 249));
        btnCliRegister.setkPressedColor(new java.awt.Color(187, 215, 213));
        btnCliRegister.setkStartColor(new java.awt.Color(24, 163, 249));
        btnCliRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliRegisterActionPerformed(evt);
            }
        });

        txtMail1.setBordColor(new java.awt.Color(129, 202, 178));
        txtMail1.setHints("Solde");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dojo/icon/close.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        btnCliDelete.setText("Supprimer");
        btnCliDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCliDelete.setkEndColor(new java.awt.Color(255, 54, 32));
        btnCliDelete.setkHoverEndColor(new java.awt.Color(245, 78, 72));
        btnCliDelete.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        btnCliDelete.setkHoverStartColor(new java.awt.Color(227, 53, 70));
        btnCliDelete.setkStartColor(new java.awt.Color(242, 20, 48));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtMail1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCliDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCliRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtMail1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCliDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCliRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        GlassPanePopup.closePopupLast();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void btnCliRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliRegisterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCliRegisterActionPerformed

    public void eventAdd(ActionListener event){
        btnCliRegister.addActionListener(event);
    }
    public void eventUpdate(ActionListener event){
        btnCliRegister.addActionListener(event);
    }
    public void eventDelete(ActionListener event){
        btnCliDelete.addActionListener(event);
    }
    public String getNom(){
        return txtNom.getText();
    }
    public String getPrenom(){
        return txtPrenom.getText();
    }
    public String getMail(){
        return txtMail.getText();
    }
    public String getPhone(){
        return txtPhone.getText();
    }
    public String getSolde(){
        return txtMail1.getText();
    }
    
    public void setNom(String nom){
        txtNom.setText(nom);
    }
    public void setPrenom(String prenom){
        txtPrenom.setText(prenom);
    }
    public void setPhone(String phoneNumber){
        txtPhone.setText(phoneNumber);
    }
    public void setMail(String email){
        txtMail.setText(email);
    }
    public void setSolde(String solde){
        txtMail1.setText(solde);
    }
    
    public enum Type{
        UPDATE, INSERT
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.k33ptoo.components.KButton btnCliDelete;
    private com.k33ptoo.components.KButton btnCliRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private com.dojo.component.TextField txtMail;
    private com.dojo.component.TextField txtMail1;
    private com.dojo.component.TextField txtNom;
    private com.dojo.component.TextField txtPhone;
    private com.dojo.component.TextField txtPrenom;
    // End of variables declaration//GEN-END:variables
}
