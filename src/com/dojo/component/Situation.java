/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.dojo.component;

import com.dojo.connection.DatabaseConnection;
import com.dojo.main.Main;
import com.raven.swing.ScrollBar;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class Situation extends javax.swing.JPanel {

    public Situation() {
        initComponents();
        loadSituation();
        init();
    }
    private void init(){
        jScrollPane1.setVerticalScrollBar(new ScrollBar());
        jScrollPane2.setVerticalScrollBar(new ScrollBar());
        
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        
        jScrollPane1.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        jScrollPane2.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
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
    private void loadSituation(){
        Connection con = DatabaseConnection.getInstance().getConnection();
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        DefaultTableModel model1 = (DefaultTableModel) table2.getModel();
        model.setRowCount(0);
        model1.setRowCount(0);
        try{
            try(PreparedStatement p = con.prepareStatement("SELECT prenom, situation, rest_paye FROM preter JOIN rendre ON preter.num_pret = rendre.num_pret JOIN client ON client.num_compte = preter.num_compte WHERE situation = 'PAYE'")){
                try(ResultSet r = p.executeQuery()){
                    while (r.next()) {
                        String prenom = r.getString(1);
                        String status = r.getString(2);
                        int rest = r.getInt(3);
                        
                        
                        model.addRow(new Object[] {prenom,status, rest});
                    }
                }
            }
            try(PreparedStatement p = con.prepareStatement("SELECT prenom, situation, rest_paye FROM preter JOIN rendre ON preter.num_pret = rendre.num_pret JOIN client ON client.num_compte = preter.num_compte WHERE situation = 'nonPAYE'")){
                try(ResultSet r = p.executeQuery()){
                    while (r.next()) {
                        String prenom = r.getString(1);
                        String status = r.getString(2);
                        int rest = r.getInt(3);
                        
                        
                        model1.addRow(new Object[] {prenom,status, rest});
                    }
                }
            }
        }catch(SQLException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kGradientPanel1 = new com.k33ptoo.components.KGradientPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        kGradientPanel2 = new com.k33ptoo.components.KGradientPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new com.dojo.component.Table();
        jScrollPane2 = new javax.swing.JScrollPane();
        table2 = new com.dojo.component.Table();

        setLayout(new java.awt.BorderLayout());

        kGradientPanel1.setkBorderRadius(0);
        kGradientPanel1.setkStartColor(new java.awt.Color(51, 102, 255));
        kGradientPanel1.setMinimumSize(new java.awt.Dimension(100, 50));
        kGradientPanel1.setPreferredSize(new java.awt.Dimension(731, 50));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Liste des prêts non payés");

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Liste des prêts payés");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dojo/icon/close.png"))); // NOI18N

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(45, 45, 45)
                .addComponent(jLabel3))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(10, Short.MAX_VALUE))
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        add(kGradientPanel1, java.awt.BorderLayout.NORTH);

        kGradientPanel2.setkBorderRadius(0);
        kGradientPanel2.setkEndColor(new java.awt.Color(0, 65, 126));
        kGradientPanel2.setkStartColor(new java.awt.Color(51, 102, 255));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nom", "Situation", "Reste à payé"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table1);

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nom", "Situation", "Reste à payé"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(table2);

        javax.swing.GroupLayout kGradientPanel2Layout = new javax.swing.GroupLayout(kGradientPanel2);
        kGradientPanel2.setLayout(kGradientPanel2Layout);
        kGradientPanel2Layout.setHorizontalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        kGradientPanel2Layout.setVerticalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        add(kGradientPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.k33ptoo.components.KGradientPanel kGradientPanel1;
    private com.k33ptoo.components.KGradientPanel kGradientPanel2;
    private com.dojo.component.Table table1;
    private com.dojo.component.Table table2;
    // End of variables declaration//GEN-END:variables
}
