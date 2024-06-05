package com.dojo.component;

import java.awt.Color;
import javax.swing.JDialog;

/**
 *
 * @author Ando Henri
 */
public class SplashScreen extends javax.swing.JDialog {

    /**
     * Creates new form SplashScreen
     * @param parent
     * @param modal
     */
    public SplashScreen(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        getContentPane().setBackground(new Color(255,255,255,125));
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        curvesPanel1 = new com.dojo.swing.CurvesPanel();
        lblDescription = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        progressBar = new com.dojo.swing.ProgressBarCustom();
        lblStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(189, 189, 189));
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        lblDescription.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblDescription.setForeground(new java.awt.Color(255, 255, 255));
        lblDescription.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDescription.setText("Merci d'utiliser avec plaisir cette application");

        lblTitle.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Welcome");

        lblStatus.setFont(new java.awt.Font("Segoe Print", 0, 14)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(255, 255, 255));
        lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStatus.setText("Status");

        javax.swing.GroupLayout curvesPanel1Layout = new javax.swing.GroupLayout(curvesPanel1);
        curvesPanel1.setLayout(curvesPanel1Layout);
        curvesPanel1Layout.setHorizontalGroup(
            curvesPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(curvesPanel1Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(curvesPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescription)
                    .addComponent(lblTitle)
                    .addComponent(lblStatus))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        curvesPanel1Layout.setVerticalGroup(
            curvesPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(curvesPanel1Layout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addComponent(lblTitle)
                .addGap(18, 18, 18)
                .addComponent(lblDescription)
                .addGap(64, 64, 64)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStatus)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(curvesPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(curvesPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        new Thread(() -> {
            try{
                doTask("Connect to database", 10);
                doTask("Task 1", 30);
                doTask("Task 2", 50);
                doTask("Task 3", 80);
                doTask("wait...", 90);
                doTask("Done!", 100);
                dispose();
                curvesPanel1.stop();
            }catch(InterruptedException e){
            }
        }).start();
    }//GEN-LAST:event_formWindowOpened

    private void doTask(String taskName, int progress) throws InterruptedException{
        lblStatus.setText(taskName);
        Thread.sleep(700);
        progressBar.setValue(progress);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            SplashScreen dialog = new SplashScreen(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.dojo.swing.CurvesPanel curvesPanel1;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTitle;
    private com.dojo.swing.ProgressBarCustom progressBar;
    // End of variables declaration//GEN-END:variables
}
