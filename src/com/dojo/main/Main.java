package com.dojo.main;

import com.dojo.component.AvisDeVirement;
import com.dojo.component.Situation;
import com.dojo.form.FormClient;
import com.dojo.connection.DatabaseConnection;
import com.dojo.controller.ControllerClient;
import com.dojo.controller.ControllerMail;
import com.dojo.controller.ControllerPret;
import com.dojo.controller.ControllerRendu;
import com.dojo.controller.ControllerVirement;
import com.dojo.form.FormPret;
import com.dojo.form.FormRendu;
import com.dojo.form.FormVirement;
import com.dojo.model.ModelClient;
import com.dojo.model.ModelPret;
import com.dojo.model.ModelRendu;
import com.dojo.model.ModelVirement;
import com.dojo.swing.GlassPanePopup;
import com.dojo.swing.Notification;
import com.dojo.swing.SearchOptinEvent;
import com.dojo.swing.SearchOption;
import com.raven.swing.ScrollBar;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import javax.mail.MessagingException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ando Henri
 */
public class Main extends javax.swing.JFrame {

    public Main() {
        GlassPanePopup.install(this);
        setPreferredSize(new Dimension(1000, 600));
        initComponents();
        spClient.setVerticalScrollBar(new ScrollBar());
        spVirement.setVerticalScrollBar(new ScrollBar());
        spPret.setVerticalScrollBar(new ScrollBar());
        spRendre.setVerticalScrollBar(new ScrollBar());
        
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        
        spClient.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        spVirement.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        spPret.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        spRendre.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        
        init();
    }
    
    private void init(){
        ClientPanel.setVisible(false);
        VirementPanel.setVisible(false);
        PretPanel.setVisible(false);
        RendrePanel.setVisible(false);
        
        
        
        txtSearch.addEventOptionSelected(new SearchOptinEvent() {
            @Override
            public void optionSelected(SearchOption option, int index) {
                txtSearch.setHint("Recherche par "+ option.getName()+ "...");
            }
        });
        txtSearch.addOption(new SearchOption("n° de compte", new ImageIcon(getClass().getResource("/com/dojo/icon/number.png"))));
        txtSearch.addOption(new SearchOption("nom", new ImageIcon(getClass().getResource("/com/dojo/icon/name.png"))));
        txtSearch.setSelectedIndex(0);
        
        txtSearch1.addEventOptionSelected(new SearchOptinEvent() {
            @Override
            public void optionSelected(SearchOption option, int index) {
                txtSearch1.setHint("Recherche par "+ option.getName()+ "...");
            }
        });
        txtSearch1.addOption(new SearchOption("n° de virement", new ImageIcon(getClass().getResource("/com/dojo/icon/number.png"))));
        txtSearch1.setSelectedIndex(0);
        
        txtSearch3.addEventOptionSelected(new SearchOptinEvent() {
            @Override
            public void optionSelected(SearchOption option, int index) {
                txtSearch3.setHint("Recherche par "+ option.getName()+ "...");
            }
        });
        txtSearch3.addOption(new SearchOption("n°de prêt", new ImageIcon(getClass().getResource("/com/dojo/icon/number.png"))));
        txtSearch3.addOption(new SearchOption("n°de compte", new ImageIcon(getClass().getResource("/com/dojo/icon/name.png"))));
        txtSearch3.setSelectedIndex(1);
        
        txtSearch4.addEventOptionSelected(new SearchOptinEvent() {
            @Override
            public void optionSelected(SearchOption option, int index) {
                txtSearch4.setHint("Recherche par "+ option.getName()+ "...");
            }
        });
        txtSearch4.addOption(new SearchOption("n°de rendu", new ImageIcon(getClass().getResource("/com/dojo/icon/number.png"))));
        txtSearch4.addOption(new SearchOption("n°de prêt", new ImageIcon(getClass().getResource("/com/dojo/icon/name.png"))));
        txtSearch4.addOption(new SearchOption("situation", new ImageIcon(getClass().getResource("/com/dojo/icon/name.png"))));
        txtSearch4.setSelectedIndex(2);
        loadData("");
    }
    
    private void loadData(String where, Object ...search){
        Connection con = DatabaseConnection.getInstance().getConnection();
        DefaultTableModel model = (DefaultTableModel) tableClient.getModel();
        model.setRowCount(0);
        try{
            try(PreparedStatement p = con.prepareStatement("SELECT * FROM client " + where)){
                for(int i = 0; i < search.length; i++){
                    p.setObject(i+1, search[i]);
                }
                try(ResultSet r = p.executeQuery()){
                    while (r.next()) {
                        String id = r.getString("num_compte");
                        String nom = r.getString("nom");
                        String prenom = r.getString("prenom");
                        String tel = r.getString("tel");
                        String mail = r.getString("mail");
                        int solde = r.getInt("solde");
                        
                        model.addRow(new Object[] {id, nom, prenom, tel, mail, solde});
                    }
                }
            }
            try(Statement s = con.createStatement()){
                try(ResultSet r1 = s.executeQuery("SELECT count(*) FROM client")){
                    r1.next();
                    jLabel6.setText(r1.getInt(1) + " personnes");
                }
            }
        }catch(SQLException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void loadVirement(String where, Object ...search){
        Connection con = DatabaseConnection.getInstance().getConnection();
        DefaultTableModel model = (DefaultTableModel) tableVirement.getModel();
        model.setRowCount(0);
        try{
            try(PreparedStatement p = con.prepareStatement("SELECT * FROM virement " + where)){
                for(int i = 0; i < search.length; i++){
                    p.setObject(i+1, search[i]);
                }
                try(ResultSet r = p.executeQuery()){
                    while (r.next()) {
                        String num1 = r.getString("num_compte1");
                        String num2 = r.getString("num_compte2");
                        String montant = r.getString("montant");
                        int id = r.getInt("num_virement");
                        Timestamp dateTransfert = r.getTimestamp("date_transfert");                      
                        model.addRow(new Object[] {num1, num2, montant, dateTransfert, id});
                    }
                }
            }
        }catch(SQLException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void loadPret(String where, Object ...search){
        Connection con = DatabaseConnection.getInstance().getConnection();
        DefaultTableModel model = (DefaultTableModel) tablePret.getModel();
        model.setRowCount(0);
        try{
            try(PreparedStatement p = con.prepareStatement("SELECT * FROM preter " + where)){
                for(int i = 0; i < search.length; i++){
                    p.setObject(i+1, search[i]);
                }
                try(ResultSet r = p.executeQuery()){
                    while (r.next()) {
                        int numPret = r.getInt("num_pret");
                        String numCompte = r.getString("num_compte");
                        String montant = r.getString("montant_preter");
                        Timestamp datePret = r.getTimestamp("date_pret");                      
                        model.addRow(new Object[] {numPret, numCompte, montant, datePret});
                    }
                }
            }
            try(Statement s = con.createStatement()){
                try(ResultSet r1 = s.executeQuery("SELECT COUNT(*) FROM preter")){
                    r1.next();
                    jLabel12.setText(r1.getInt(1) + " prêt");
                    try(ResultSet r2 = s.executeQuery("SELECT SUM(amount) FROM profit")){
                        r2.next();
                        jLabel21.setText(r2.getInt(1) + "");
                    }
                }
            }
        }catch(SQLException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void loadRendu(String where, Object ...search){
        Connection con = DatabaseConnection.getInstance().getConnection();
        DefaultTableModel model = (DefaultTableModel) tableRendre.getModel();
        model.setRowCount(0);
        try{
            try(PreparedStatement p = con.prepareStatement("SELECT * FROM rendre " + where)){
                for(int i = 0; i < search.length; i++){
                    p.setObject(i+1, search[i]);
                }
                try(ResultSet r = p.executeQuery()){
                    while (r.next()) {
                        int numRendu = r.getInt("num_rendu");
                        int numPret = r.getInt("num_pret");
                        String situation = r.getString("situation");
                        int restPaye = r.getInt("rest_paye");
                        Timestamp dateRendu = r.getTimestamp("date_rendu");
                        model.addRow(new Object[] {numRendu, numPret, situation, restPaye, dateRendu});
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

        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        loginPanel = new com.k33ptoo.components.KGradientPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtUsername = new com.dojo.component.TextField();
        txtPassword = new com.dojo.component.TextPassword();
        jLabel1 = new javax.swing.JLabel();
        btnConnecter = new com.k33ptoo.components.KButton();
        mainPanel = new com.k33ptoo.components.KGradientPanel();
        MenuPanel = new com.k33ptoo.components.KGradientPanel();
        menu1 = new com.dojo.component.Menu();
        jLabel2 = new javax.swing.JLabel();
        menu2 = new com.dojo.component.Menu();
        jLabel3 = new javax.swing.JLabel();
        menu3 = new com.dojo.component.Menu();
        jLabel4 = new javax.swing.JLabel();
        menu4 = new com.dojo.component.Menu();
        jLabel5 = new javax.swing.JLabel();
        ContentPanel = new com.k33ptoo.components.KGradientPanel();
        ClientPanel = new com.k33ptoo.components.KGradientPanel();
        kGradientPanel1 = new com.k33ptoo.components.KGradientPanel();
        txtSearch = new com.dojo.swing.TextFieldSearchOption();
        jLabel8 = new javax.swing.JLabel();
        kGradientPanel3 = new com.k33ptoo.components.KGradientPanel();
        btnNewClient = new com.k33ptoo.components.KButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        spClient = new javax.swing.JScrollPane();
        tableClient = new com.dojo.component.Table();
        VirementPanel = new com.k33ptoo.components.KGradientPanel();
        kGradientPanel2 = new com.k33ptoo.components.KGradientPanel();
        txtSearch1 = new com.dojo.swing.TextFieldSearchOption();
        jLabel9 = new javax.swing.JLabel();
        kGradientPanel4 = new com.k33ptoo.components.KGradientPanel();
        btnNewVirement = new com.k33ptoo.components.KButton();
        btnAvis = new com.k33ptoo.components.KButton();
        spVirement = new javax.swing.JScrollPane();
        tableVirement = new com.dojo.component.Table();
        PretPanel = new com.k33ptoo.components.KGradientPanel();
        kGradientPanel7 = new com.k33ptoo.components.KGradientPanel();
        txtSearch3 = new com.dojo.swing.TextFieldSearchOption();
        jLabel10 = new javax.swing.JLabel();
        kGradientPanel8 = new com.k33ptoo.components.KGradientPanel();
        btnNewPret = new com.k33ptoo.components.KButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        kGradientPanel5 = new com.k33ptoo.components.KGradientPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        btnNewPret1 = new com.k33ptoo.components.KButton();
        spPret = new javax.swing.JScrollPane();
        tablePret = new com.dojo.component.Table();
        RendrePanel = new com.k33ptoo.components.KGradientPanel();
        kGradientPanel9 = new com.k33ptoo.components.KGradientPanel();
        txtSearch4 = new com.dojo.swing.TextFieldSearchOption();
        jLabel17 = new javax.swing.JLabel();
        kGradientPanel10 = new com.k33ptoo.components.KGradientPanel();
        btnNewRendu = new com.k33ptoo.components.KButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        spRendre = new javax.swing.JScrollPane();
        tableRendre = new com.dojo.component.Table();

        jFormattedTextField1.setText("jFormattedTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.OverlayLayout(getContentPane()));

        loginPanel.setkBorderRadius(0);
        loginPanel.setkEndColor(new java.awt.Color(24, 90, 157));
        loginPanel.setkGradientFocus(700);
        loginPanel.setkStartColor(new java.awt.Color(67, 206, 162));
        loginPanel.setLayout(new java.awt.GridLayout(1, 0));

        jPanel2.setOpaque(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 465, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 544, Short.MAX_VALUE)
        );

        loginPanel.add(jPanel2);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setOpaque(false);

        txtUsername.setHints("Identifiant");

        txtPassword.setHints("Mot de passe");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Connecter-vous");

        btnConnecter.setText("Se connecter");
        btnConnecter.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnConnecter.setkBorderRadius(8);
        btnConnecter.setkEndColor(new java.awt.Color(67, 206, 162));
        btnConnecter.setkHoverEndColor(new java.awt.Color(67, 206, 162));
        btnConnecter.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        btnConnecter.setkHoverStartColor(new java.awt.Color(24, 163, 249));
        btnConnecter.setkPressedColor(new java.awt.Color(187, 215, 213));
        btnConnecter.setkStartColor(new java.awt.Color(24, 163, 249));
        btnConnecter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnecterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(124, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                    .addComponent(btnConnecter, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(125, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(180, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnConnecter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(190, Short.MAX_VALUE))
        );

        loginPanel.add(jPanel1);

        getContentPane().add(loginPanel);

        mainPanel.setkBorderRadius(0);
        mainPanel.setkEndColor(new java.awt.Color(24, 90, 157));
        mainPanel.setkGradientFocus(700);
        mainPanel.setkStartColor(new java.awt.Color(67, 206, 162));
        mainPanel.setLayout(new java.awt.BorderLayout());

        MenuPanel.setkBorderRadius(0);
        MenuPanel.setkEndColor(new java.awt.Color(0, 154, 178));
        MenuPanel.setkStartColor(new java.awt.Color(0, 154, 178));
        MenuPanel.setPreferredSize(new java.awt.Dimension(170, 543));

        menu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menu1MousePressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dojo/icon/customer.png"))); // NOI18N
        jLabel2.setText("Client");

        javax.swing.GroupLayout menu1Layout = new javax.swing.GroupLayout(menu1);
        menu1.setLayout(menu1Layout);
        menu1Layout.setHorizontalGroup(
            menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menu1Layout.setVerticalGroup(
            menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menu1Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(16, 16, 16))
        );

        menu2.setOpaque(true);
        menu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menu2MousePressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dojo/icon/virement.png"))); // NOI18N
        jLabel3.setText("Virement");

        javax.swing.GroupLayout menu2Layout = new javax.swing.GroupLayout(menu2);
        menu2.setLayout(menu2Layout);
        menu2Layout.setHorizontalGroup(
            menu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menu2Layout.setVerticalGroup(
            menu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menu2Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(16, 16, 16))
        );

        menu3.setOpaque(true);
        menu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menu3MousePressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dojo/icon/pret.png"))); // NOI18N
        jLabel4.setText("Preter");

        javax.swing.GroupLayout menu3Layout = new javax.swing.GroupLayout(menu3);
        menu3.setLayout(menu3Layout);
        menu3Layout.setHorizontalGroup(
            menu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel4)
                .addContainerGap(64, Short.MAX_VALUE))
        );
        menu3Layout.setVerticalGroup(
            menu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menu3Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(16, 16, 16))
        );

        menu4.setOpaque(true);
        menu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menu4MousePressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dojo/icon/transaction.png"))); // NOI18N
        jLabel5.setText("Rendre");

        javax.swing.GroupLayout menu4Layout = new javax.swing.GroupLayout(menu4);
        menu4.setLayout(menu4Layout);
        menu4Layout.setHorizontalGroup(
            menu4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu4Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menu4Layout.setVerticalGroup(
            menu4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menu4Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout MenuPanelLayout = new javax.swing.GroupLayout(MenuPanel);
        MenuPanel.setLayout(MenuPanelLayout);
        MenuPanelLayout.setHorizontalGroup(
            MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menu2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menu3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menu4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MenuPanelLayout.setVerticalGroup(
            MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menu3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menu4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(248, Short.MAX_VALUE))
        );

        mainPanel.add(MenuPanel, java.awt.BorderLayout.LINE_START);

        ContentPanel.setkBorderRadius(0);
        ContentPanel.setkEndColor(new java.awt.Color(255, 255, 255));
        ContentPanel.setkFillBackground(false);
        ContentPanel.setkStartColor(new java.awt.Color(204, 255, 255));
        ContentPanel.setOpaque(false);
        ContentPanel.setLayout(new javax.swing.OverlayLayout(ContentPanel));

        ClientPanel.setkBorderRadius(0);
        ClientPanel.setkFillBackground(false);
        ClientPanel.setOpaque(false);
        ClientPanel.setLayout(new java.awt.BorderLayout());

        kGradientPanel1.setkBorderRadius(0);
        kGradientPanel1.setkEndColor(new java.awt.Color(51, 153, 255));
        kGradientPanel1.setkStartColor(new java.awt.Color(0, 65, 126));
        kGradientPanel1.setPreferredSize(new java.awt.Dimension(761, 40));

        txtSearch.setColorOverlay2(new java.awt.Color(0, 65, 126));
        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSearch.setHint("Recherche...");
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("CLIENT");

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 393, Short.MAX_VALUE)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8))
        );

        ClientPanel.add(kGradientPanel1, java.awt.BorderLayout.PAGE_START);

        kGradientPanel3.setkBorderRadius(0);
        kGradientPanel3.setkEndColor(new java.awt.Color(51, 153, 255));
        kGradientPanel3.setkStartColor(new java.awt.Color(0, 65, 126));
        kGradientPanel3.setPreferredSize(new java.awt.Dimension(761, 100));

        btnNewClient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dojo/icon/add_User.png"))); // NOI18N
        btnNewClient.setText("Ajouter un client");
        btnNewClient.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNewClient.setIconTextGap(10);
        btnNewClient.setkBorderRadius(8);
        btnNewClient.setkEndColor(new java.awt.Color(0, 65, 126));
        btnNewClient.setkHoverEndColor(new java.awt.Color(0, 65, 126));
        btnNewClient.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        btnNewClient.setkHoverStartColor(new java.awt.Color(51, 102, 255));
        btnNewClient.setkPressedColor(new java.awt.Color(187, 215, 213));
        btnNewClient.setkStartColor(new java.awt.Color(51, 102, 255));
        btnNewClient.setMargin(new java.awt.Insets(4, 14, 4, 14));
        btnNewClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewClientActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("100 personnes");

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nombre des clients :");

        javax.swing.GroupLayout kGradientPanel3Layout = new javax.swing.GroupLayout(kGradientPanel3);
        kGradientPanel3.setLayout(kGradientPanel3Layout);
        kGradientPanel3Layout.setHorizontalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel3Layout.createSequentialGroup()
                        .addContainerGap(570, Short.MAX_VALUE)
                        .addComponent(btnNewClient, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(kGradientPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        kGradientPanel3Layout.setVerticalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel3Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNewClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        ClientPanel.add(kGradientPanel3, java.awt.BorderLayout.CENTER);

        spClient.setBackground(new java.awt.Color(255, 255, 255));
        spClient.setBorder(null);
        spClient.setPreferredSize(new java.awt.Dimension(452, 400));

        tableClient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nom", "Prenom", "Telephone", "E-mail", "Solde"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableClient.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        tableClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableClientMousePressed(evt);
            }
        });
        spClient.setViewportView(tableClient);
        if (tableClient.getColumnModel().getColumnCount() > 0) {
            tableClient.getColumnModel().getColumn(5).setPreferredWidth(50);
        }

        ClientPanel.add(spClient, java.awt.BorderLayout.SOUTH);

        ContentPanel.add(ClientPanel);

        VirementPanel.setkBorderRadius(0);
        VirementPanel.setkEndColor(new java.awt.Color(255, 255, 255));
        VirementPanel.setkStartColor(new java.awt.Color(204, 255, 255));
        VirementPanel.setLayout(new java.awt.BorderLayout());

        kGradientPanel2.setkBorderRadius(0);
        kGradientPanel2.setkEndColor(new java.awt.Color(51, 153, 255));
        kGradientPanel2.setkStartColor(new java.awt.Color(0, 65, 126));
        kGradientPanel2.setPreferredSize(new java.awt.Dimension(761, 40));

        txtSearch1.setColorOverlay2(new java.awt.Color(0, 65, 126));
        txtSearch1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSearch1.setHint("Recherche...");
        txtSearch1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearch1KeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("VIREMENT");

        javax.swing.GroupLayout kGradientPanel2Layout = new javax.swing.GroupLayout(kGradientPanel2);
        kGradientPanel2.setLayout(kGradientPanel2Layout);
        kGradientPanel2Layout.setHorizontalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 364, Short.MAX_VALUE)
                .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        kGradientPanel2Layout.setVerticalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtSearch1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9))
        );

        VirementPanel.add(kGradientPanel2, java.awt.BorderLayout.PAGE_START);

        kGradientPanel4.setkBorderRadius(0);
        kGradientPanel4.setkEndColor(new java.awt.Color(51, 153, 255));
        kGradientPanel4.setkStartColor(new java.awt.Color(0, 65, 126));
        kGradientPanel4.setPreferredSize(new java.awt.Dimension(761, 100));

        btnNewVirement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dojo/icon/add_virement.png"))); // NOI18N
        btnNewVirement.setText("Faire un virement");
        btnNewVirement.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNewVirement.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnNewVirement.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNewVirement.setIconTextGap(10);
        btnNewVirement.setkBorderRadius(8);
        btnNewVirement.setkEndColor(new java.awt.Color(0, 65, 126));
        btnNewVirement.setkHoverEndColor(new java.awt.Color(0, 65, 126));
        btnNewVirement.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        btnNewVirement.setkHoverStartColor(new java.awt.Color(51, 102, 255));
        btnNewVirement.setkPressedColor(new java.awt.Color(187, 215, 213));
        btnNewVirement.setkStartColor(new java.awt.Color(51, 102, 255));
        btnNewVirement.setMargin(new java.awt.Insets(4, 14, 4, 14));
        btnNewVirement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewVirementActionPerformed(evt);
            }
        });

        btnAvis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dojo/icon/pdf.png"))); // NOI18N
        btnAvis.setText("Avis de virement");
        btnAvis.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAvis.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnAvis.setIconTextGap(10);
        btnAvis.setkBorderRadius(8);
        btnAvis.setkEndColor(new java.awt.Color(0, 65, 126));
        btnAvis.setkHoverEndColor(new java.awt.Color(0, 65, 126));
        btnAvis.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        btnAvis.setkHoverStartColor(new java.awt.Color(51, 102, 255));
        btnAvis.setkPressedColor(new java.awt.Color(187, 215, 213));
        btnAvis.setkStartColor(new java.awt.Color(51, 102, 255));
        btnAvis.setMargin(new java.awt.Insets(4, 14, 4, 14));
        btnAvis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel4Layout = new javax.swing.GroupLayout(kGradientPanel4);
        kGradientPanel4.setLayout(kGradientPanel4Layout);
        kGradientPanel4Layout.setHorizontalGroup(
            kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel4Layout.createSequentialGroup()
                .addContainerGap(570, Short.MAX_VALUE)
                .addGroup(kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNewVirement, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAvis, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        kGradientPanel4Layout.setVerticalGroup(
            kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAvis, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNewVirement, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        VirementPanel.add(kGradientPanel4, java.awt.BorderLayout.CENTER);

        spVirement.setBorder(null);
        spVirement.setPreferredSize(new java.awt.Dimension(452, 400));

        tableVirement.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°compte débiteur", "N°compte créditeur", "Montant", "Date de transfert", "N° de virement"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableVirement.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        tableVirement.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableVirementMousePressed(evt);
            }
        });
        spVirement.setViewportView(tableVirement);
        if (tableVirement.getColumnModel().getColumnCount() > 0) {
            tableVirement.getColumnModel().getColumn(4).setPreferredWidth(20);
            tableVirement.getColumnModel().getColumn(4).setHeaderValue("N° de virement");
        }

        VirementPanel.add(spVirement, java.awt.BorderLayout.SOUTH);

        ContentPanel.add(VirementPanel);

        PretPanel.setkBorderRadius(0);
        PretPanel.setkEndColor(new java.awt.Color(255, 255, 255));
        PretPanel.setkStartColor(new java.awt.Color(204, 255, 255));
        PretPanel.setLayout(new java.awt.BorderLayout());

        kGradientPanel7.setkBorderRadius(0);
        kGradientPanel7.setkEndColor(new java.awt.Color(51, 153, 255));
        kGradientPanel7.setkStartColor(new java.awt.Color(0, 65, 126));
        kGradientPanel7.setPreferredSize(new java.awt.Dimension(761, 40));

        txtSearch3.setColorOverlay2(new java.awt.Color(0, 65, 126));
        txtSearch3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSearch3.setHint("Recherche...");
        txtSearch3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearch3KeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("PRÊT");

        javax.swing.GroupLayout kGradientPanel7Layout = new javax.swing.GroupLayout(kGradientPanel7);
        kGradientPanel7.setLayout(kGradientPanel7Layout);
        kGradientPanel7Layout.setHorizontalGroup(
            kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel7Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 594, Short.MAX_VALUE)
                .addComponent(txtSearch3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        kGradientPanel7Layout.setVerticalGroup(
            kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtSearch3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10))
        );

        PretPanel.add(kGradientPanel7, java.awt.BorderLayout.PAGE_START);

        kGradientPanel8.setkBorderRadius(0);
        kGradientPanel8.setkEndColor(new java.awt.Color(51, 153, 255));
        kGradientPanel8.setkStartColor(new java.awt.Color(0, 65, 126));
        kGradientPanel8.setPreferredSize(new java.awt.Dimension(761, 100));

        btnNewPret.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dojo/icon/refund.png"))); // NOI18N
        btnNewPret.setText("Faire un prêt");
        btnNewPret.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNewPret.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnNewPret.setIconTextGap(10);
        btnNewPret.setkBorderRadius(8);
        btnNewPret.setkEndColor(new java.awt.Color(0, 65, 126));
        btnNewPret.setkHoverEndColor(new java.awt.Color(0, 65, 126));
        btnNewPret.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        btnNewPret.setkHoverStartColor(new java.awt.Color(51, 102, 255));
        btnNewPret.setkPressedColor(new java.awt.Color(187, 215, 213));
        btnNewPret.setkStartColor(new java.awt.Color(51, 102, 255));
        btnNewPret.setMargin(new java.awt.Insets(4, 14, 4, 14));
        btnNewPret.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewPretActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Nombre des prêt :");

        jLabel12.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("10 prêts");

        jLabel13.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Nombre des prêt PAYE :");

        jLabel14.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Nombre des prêt NON PAYE :");

        jLabel15.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("10");

        jLabel16.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("10");

        kGradientPanel5.setkBorderRadius(8);
        kGradientPanel5.setkEndColor(new java.awt.Color(0, 65, 126));
        kGradientPanel5.setkStartColor(new java.awt.Color(51, 102, 255));
        kGradientPanel5.setOpaque(false);

        jLabel20.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Benefices :");

        jLabel21.setFont(new java.awt.Font("Segoe UI Semibold", 0, 36)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("1100");

        jLabel22.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Ar");

        javax.swing.GroupLayout kGradientPanel5Layout = new javax.swing.GroupLayout(kGradientPanel5);
        kGradientPanel5.setLayout(kGradientPanel5Layout);
        kGradientPanel5Layout.setHorizontalGroup(
            kGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addComponent(jLabel22)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        kGradientPanel5Layout.setVerticalGroup(
            kGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addGap(25, 25, 25)
                .addGroup(kGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        btnNewPret1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dojo/icon/refund.png"))); // NOI18N
        btnNewPret1.setText("Prêt par situation");
        btnNewPret1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNewPret1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnNewPret1.setIconTextGap(10);
        btnNewPret1.setkBorderRadius(8);
        btnNewPret1.setkEndColor(new java.awt.Color(0, 65, 126));
        btnNewPret1.setkHoverEndColor(new java.awt.Color(0, 65, 126));
        btnNewPret1.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        btnNewPret1.setkHoverStartColor(new java.awt.Color(51, 102, 255));
        btnNewPret1.setkPressedColor(new java.awt.Color(187, 215, 213));
        btnNewPret1.setkStartColor(new java.awt.Color(51, 102, 255));
        btnNewPret1.setMargin(new java.awt.Insets(4, 14, 4, 14));
        btnNewPret1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewPret1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel8Layout = new javax.swing.GroupLayout(kGradientPanel8);
        kGradientPanel8.setLayout(kGradientPanel8Layout);
        kGradientPanel8Layout.setHorizontalGroup(
            kGradientPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel8Layout.createSequentialGroup()
                .addGroup(kGradientPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel8Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(kGradientPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(kGradientPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel16))
                            .addGroup(kGradientPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel15))
                            .addGroup(kGradientPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 524, Short.MAX_VALUE)
                        .addComponent(kGradientPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel8Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNewPret1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNewPret, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        kGradientPanel8Layout.setVerticalGroup(
            kGradientPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel8Layout.createSequentialGroup()
                .addGroup(kGradientPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel8Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(kGradientPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(kGradientPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(kGradientPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel8Layout.createSequentialGroup()
                        .addContainerGap(36, Short.MAX_VALUE)
                        .addComponent(kGradientPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(kGradientPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNewPret, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNewPret1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        PretPanel.add(kGradientPanel8, java.awt.BorderLayout.CENTER);

        spPret.setBorder(null);
        spPret.setPreferredSize(new java.awt.Dimension(452, 400));

        tablePret.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°prêt", "N°de compte", "Montant prêter", "Date de prêt"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablePret.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        tablePret.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablePretMousePressed(evt);
            }
        });
        spPret.setViewportView(tablePret);

        PretPanel.add(spPret, java.awt.BorderLayout.SOUTH);

        ContentPanel.add(PretPanel);

        RendrePanel.setkBorderRadius(0);
        RendrePanel.setkEndColor(new java.awt.Color(255, 255, 255));
        RendrePanel.setkStartColor(new java.awt.Color(204, 255, 255));
        RendrePanel.setLayout(new java.awt.BorderLayout());

        kGradientPanel9.setkBorderRadius(0);
        kGradientPanel9.setkEndColor(new java.awt.Color(51, 153, 255));
        kGradientPanel9.setkStartColor(new java.awt.Color(0, 65, 126));
        kGradientPanel9.setPreferredSize(new java.awt.Dimension(761, 40));

        txtSearch4.setColorOverlay2(new java.awt.Color(0, 65, 126));
        txtSearch4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSearch4.setHint("Recherche...");
        txtSearch4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearch4KeyReleased(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("RENDRE");

        javax.swing.GroupLayout kGradientPanel9Layout = new javax.swing.GroupLayout(kGradientPanel9);
        kGradientPanel9.setLayout(kGradientPanel9Layout);
        kGradientPanel9Layout.setHorizontalGroup(
            kGradientPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 388, Short.MAX_VALUE)
                .addComponent(txtSearch4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        kGradientPanel9Layout.setVerticalGroup(
            kGradientPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtSearch4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17))
        );

        RendrePanel.add(kGradientPanel9, java.awt.BorderLayout.PAGE_START);

        kGradientPanel10.setkBorderRadius(0);
        kGradientPanel10.setkEndColor(new java.awt.Color(51, 153, 255));
        kGradientPanel10.setkStartColor(new java.awt.Color(0, 65, 126));
        kGradientPanel10.setPreferredSize(new java.awt.Dimension(761, 100));

        btnNewRendu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dojo/icon/give.png"))); // NOI18N
        btnNewRendu.setText("Rembourser un prêt");
        btnNewRendu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNewRendu.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnNewRendu.setIconTextGap(10);
        btnNewRendu.setkBorderRadius(8);
        btnNewRendu.setkEndColor(new java.awt.Color(0, 65, 126));
        btnNewRendu.setkHoverEndColor(new java.awt.Color(0, 65, 126));
        btnNewRendu.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        btnNewRendu.setkHoverStartColor(new java.awt.Color(51, 102, 255));
        btnNewRendu.setkPressedColor(new java.awt.Color(187, 215, 213));
        btnNewRendu.setkStartColor(new java.awt.Color(51, 102, 255));
        btnNewRendu.setMargin(new java.awt.Insets(4, 14, 4, 14));
        btnNewRendu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewRenduActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("10");

        jLabel19.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Nombre des prêt remboursés :");

        javax.swing.GroupLayout kGradientPanel10Layout = new javax.swing.GroupLayout(kGradientPanel10);
        kGradientPanel10.setLayout(kGradientPanel10Layout);
        kGradientPanel10Layout.setHorizontalGroup(
            kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNewRendu, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(kGradientPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addContainerGap(532, Short.MAX_VALUE))
        );
        kGradientPanel10Layout.setVerticalGroup(
            kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel10Layout.createSequentialGroup()
                .addContainerGap(74, Short.MAX_VALUE)
                .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNewRendu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        RendrePanel.add(kGradientPanel10, java.awt.BorderLayout.CENTER);

        spRendre.setBorder(null);
        spRendre.setPreferredSize(new java.awt.Dimension(452, 400));

        tableRendre.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°de rendu", "N°prêt", "situation", "Reste à payé", "Date de rendu"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableRendre.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        tableRendre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableRendreMousePressed(evt);
            }
        });
        spRendre.setViewportView(tableRendre);

        RendrePanel.add(spRendre, java.awt.BorderLayout.SOUTH);

        ContentPanel.add(RendrePanel);

        mainPanel.add(ContentPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(mainPanel);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //Connexion
    private void btnConnecterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnecterActionPerformed
        String username = txtUsername.getText();
        String password = String.valueOf( txtPassword.getPassword());
        
        Connection con = DatabaseConnection.getInstance().getConnection();
        
        if(!username.isEmpty() && !password.isEmpty()){
            try{
                try(PreparedStatement p = con.prepareStatement("SELECT * FROM users")) {
                    try(ResultSet r = p.executeQuery()) {
                        r.next();
                        if(r.getString(2).equals(username) && r.getString(3).equals(password)){
                            Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Vous-êtes connecté.");
                            panel.showNotification();

                            //faire apparaitre un nouveau window
                            loginPanel.setVisible(false);
                            mainPanel.setVisible(true);
                            ClientPanel.setVisible(true);

                            txtUsername.setText("");
                            txtPassword.setText("");

                        }else{
                            Notification panel = new Notification(this, Notification.Type.ERROR, Notification.Location.TOP_CENTER, "Identifiant ou mot de passe incorrect.");
                            panel.showNotification();
                        }
                    }
                }
            }catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            Notification panel = new Notification(this, Notification.Type.ERROR, Notification.Location.TOP_CENTER, "Remplir les champs avant de connecter.");
            panel.showNotification();
        }
    }//GEN-LAST:event_btnConnecterActionPerformed

    //Navigation
    private void menu1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu1MousePressed
        menu1.setOpaque(false);
        menu2.setOpaque(true);
        menu3.setOpaque(true);
        menu4.setOpaque(true);
        
        loadData("");
        
        ClientPanel.setVisible(true);
        VirementPanel.setVisible(false);
        PretPanel.setVisible(false);
        RendrePanel.setVisible(false);
        
        repaint();
    }//GEN-LAST:event_menu1MousePressed

    private void menu2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu2MousePressed
        menu2.setOpaque(false);
        menu1.setOpaque(true);
        menu3.setOpaque(true);
        menu4.setOpaque(true);
        
        loadVirement("");
        
        ClientPanel.setVisible(false);
        VirementPanel.setVisible(true);
        PretPanel.setVisible(false);
        RendrePanel.setVisible(false);
        
        repaint();
    }//GEN-LAST:event_menu2MousePressed

    private void menu3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu3MousePressed
        menu3.setOpaque(false);
        menu1.setOpaque(true);
        menu2.setOpaque(true);
        menu4.setOpaque(true);
        
        loadPret("");
        
        ClientPanel.setVisible(false);
        VirementPanel.setVisible(false);
        PretPanel.setVisible(true);
        RendrePanel.setVisible(false);
        
        repaint();
    }//GEN-LAST:event_menu3MousePressed

    private void menu4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu4MousePressed
        menu4.setOpaque(false);
        menu1.setOpaque(true);
        menu2.setOpaque(true);
        menu3.setOpaque(true);
        
        loadRendu("");
        
        ClientPanel.setVisible(false);
        VirementPanel.setVisible(false);
        PretPanel.setVisible(false);
        RendrePanel.setVisible(true);
        
        repaint();
    }//GEN-LAST:event_menu4MousePressed

    //CLIENTS
    private void btnNewClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewClientActionPerformed
        // TODO add your handling code here:
        
            FormClient form = new FormClient(FormClient.Type.INSERT);
            ControllerClient serviceClient = new ControllerClient();

            form.eventAdd(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!form.getNom().isEmpty() && !form.getPrenom().isEmpty() && !form.getPhone().isEmpty() && !form.getMail().isEmpty() && !form.getSolde().isEmpty()){
                        int solde = Integer.parseInt(form.getSolde());
                        ModelClient client = new ModelClient(form.getNom(), form.getPrenom(), form.getPhone(), form.getMail(), solde);
                        try{
                            serviceClient.insertClient(client);
                            Notification panel = new Notification(Main.this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Insertion reussi");                            
                            panel.showNotification();
                            loadData("");
                            GlassPanePopup.closePopupLast();
                        }catch(SQLException ex){
                            Notification panel = new Notification(Main.this, Notification.Type.ERROR, Notification.Location.TOP_CENTER, "Il ya un erreur sur l'ajout, veuillez verifier.");
                            panel.showNotification();
                        }
                    }else{
                        Notification panel = new Notification(Main.this, Notification.Type.INFO, Notification.Location.TOP_CENTER, "Veuillez remplir tous les champs d'abords.");
                        panel.showNotification();
                    }
                }
            });
            GlassPanePopup.showPopup(form);
    }//GEN-LAST:event_btnNewClientActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        if(txtSearch.isSelected()){
            int option = txtSearch.getSelectedIndex();
            String text = "%" + txtSearch.getText().trim() + "%";
            if (option == 0){
                loadData("WHERE num_compte LIKE ?", text);
            }else if (option == 1){
                loadData("WHERE nom LIKE ?", text);
            }
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private void tableClientMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableClientMousePressed
        
        int row = tableClient.getSelectedRow();
        String id = (String) tableClient.getValueAt(row, 0);
       
        Connection con = DatabaseConnection.getInstance().getConnection();
        
        FormClient form = new FormClient(FormClient.Type.UPDATE);
        try{
            try(PreparedStatement p = con.prepareStatement("SELECT * FROM client WHERE num_compte = ?")){
                p.setString(1, id);
                try(ResultSet r = p.executeQuery()){
                    while(r.next()){
                        form.setNom(r.getString("nom").trim());
                        form.setPrenom(r.getString("prenom").trim());
                        form.setPhone(r.getString("tel").trim());
                        form.setMail(r.getString("mail").trim());
                        form.setSolde(r.getString("solde").trim());
                    }
                }
            } 
        }catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ControllerClient serviceClient = new ControllerClient();
        
        form.eventUpdate(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!form.getNom().isEmpty() && !form.getPrenom().isEmpty() && !form.getPhone().isEmpty() && !form.getMail().isEmpty() && !form.getSolde().isEmpty()){
                    int solde = Integer.parseInt(form.getSolde());
                    ModelClient client = new ModelClient(form.getNom(), form.getPrenom(), form.getPhone(), form.getMail(), solde);                 
                    try{
                        serviceClient.updateClient(client, id);
                        loadData("");
                        Notification panel = new Notification(Main.this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Modification réussi");
                        panel.showNotification();
                        GlassPanePopup.closePopupLast();
                    }catch(SQLException ex){
                        Notification panel = new Notification(Main.this, Notification.Type.ERROR, Notification.Location.TOP_CENTER, "il y a un probleme sur le serveur.");
                        panel.showNotification();
                    }
                }else{
                    Notification panel = new Notification(Main.this, Notification.Type.INFO, Notification.Location.TOP_CENTER, "Veuillez remplir tous les champs d'abords");
                    panel.showNotification();
                }
            }
            
        });
        
        form.eventDelete(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{    
                    serviceClient.deleteClient(id);
                    Notification panel = new Notification(Main.this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Suppression réussi");
                    panel.showNotification();
                    loadData("");
                    GlassPanePopup.closePopupLast();
                }catch (SQLException ex) {
                    Notification panel = new Notification(Main.this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, ex.getMessage());
                    panel.showNotification();
                }
            }
        });
        GlassPanePopup.showPopup(form);
    }//GEN-LAST:event_tableClientMousePressed

    //VIREMENT
    private void txtSearch1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearch1KeyReleased
        if(txtSearch1.isSelected()){
            int option = txtSearch1.getSelectedIndex();
            String text = "%" + Integer.valueOf(txtSearch1.getText().trim()) + "%";
            if (option == 0){
                loadVirement("WHERE num_virement LIKE ?", text);
            }
        }
    }//GEN-LAST:event_txtSearch1KeyReleased

    private void btnNewVirementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewVirementActionPerformed
        // TODO add your handling code here:
        FormVirement form = new FormVirement(FormVirement.Type.INSERT);
        ControllerVirement serviceVirement = new ControllerVirement();

        form.eventAdd(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!form.getNum1().isEmpty() && !form.getNum2().isEmpty() && !form.getMontant().isEmpty()){
                    int montant = Integer.parseInt(form.getMontant());
                    ModelVirement virement = new ModelVirement(form.getNum1(), form.getNum2(), montant);
                    try{
                        if(serviceVirement.numExiste(virement.getNum1()) && serviceVirement.numExiste(virement.getNum2())){                  
                            if (serviceVirement.isValidAmount(virement.getNum1(), virement.getMontant())){
                                serviceVirement.insertVirement(virement);
                                Notification panel = new Notification(Main.this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Insertion reussi");                            
                                panel.showNotification();
                                loadData("");
                                loadVirement("");
                                GlassPanePopup.closePopupLast();
                            }else{
                                Notification notif = new Notification(Main.this, Notification.Type.ERROR, Notification.Location.TOP_CENTER, "Solde insuffisant.");
                                notif.showNotification();
                            }
                        }else{
                            Notification notif = new Notification(Main.this, Notification.Type.ERROR, Notification.Location.TOP_CENTER, "Numero de compte innexistant.");
                            notif.showNotification();
                        }
                    }catch(SQLException ex){
                        Notification panel = new Notification(Main.this, Notification.Type.ERROR, Notification.Location.TOP_CENTER, "Il ya un erreur sur l'ajout, veuillez verifier.");
                        panel.showNotification();
                        System.out.println(ex);
                    }
                }else{
                    Notification panel = new Notification(Main.this, Notification.Type.INFO, Notification.Location.TOP_CENTER, "Veuillez remplir tous les champs d'abords.");
                    panel.showNotification();
                }
            }
        });
        GlassPanePopup.showPopup(form);
    }//GEN-LAST:event_btnNewVirementActionPerformed

    private void tableVirementMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableVirementMousePressed
        // TODO add your handling code here:
        Connection con = DatabaseConnection.getInstance().getConnection();
        int row = tableVirement.getSelectedRow();
        int id =  (int) tableVirement.getValueAt(row, 4);
        
        FormVirement form = new FormVirement(FormVirement.Type.UPDATE);
        try{
            try(PreparedStatement p = con.prepareStatement("SELECT * FROM virement WHERE num_virement = ?")){
                p.setInt(1, id);
                try(ResultSet r = p.executeQuery()){
                    while(r.next()){
                        form.setNum1(r.getString("num_compte1").trim());
                        form.setNum2(r.getString("num_compte2").trim());
                        form.setMontant(r.getString("montant").trim());
                    }
                }
            } 
        }catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ControllerVirement serviceVirement = new ControllerVirement();
        String debit = form.getNum1();
        String credit = form.getNum2();
        int solde = Integer.parseInt(form.getMontant());
        
        form.eventUpdate(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    serviceVirement.transaction(credit, debit, solde);
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(!form.getNum1().isEmpty() && !form.getNum2().isEmpty() && !form.getMontant().isEmpty()){
                    int montant = Integer.parseInt(form.getMontant());
                    ModelVirement virement = new ModelVirement(form.getNum1(), form.getNum2(), montant);                 
                    try{
                        if(serviceVirement.numExiste(virement.getNum1()) && serviceVirement.numExiste(virement.getNum2())){                  
                            if (serviceVirement.isValidAmount(virement.getNum1(), virement.getMontant())){
                                serviceVirement.updateVirement(virement, id);
                                Notification panel = new Notification(Main.this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Modification réussi.");                            
                                panel.showNotification();
                                loadData("");
                                loadVirement("");
                                GlassPanePopup.closePopupLast();
                            }else{
                                Notification notif = new Notification(Main.this, Notification.Type.ERROR, Notification.Location.TOP_CENTER, "Solde insuffisant.");
                                notif.showNotification();
                            }
                        }else{
                            Notification notif = new Notification(Main.this, Notification.Type.ERROR, Notification.Location.TOP_CENTER, "Numero de compte innexistant.");
                            notif.showNotification();
                        }
                    }catch(SQLException ex){
                        Notification panel = new Notification(Main.this, Notification.Type.ERROR, Notification.Location.TOP_CENTER, "il y a un probleme sur le serveur.");
                        panel.showNotification();
                    }
                }else{
                    Notification panel = new Notification(Main.this, Notification.Type.INFO, Notification.Location.TOP_CENTER, "Veuillez remplir tous les champs d'abords");
                    panel.showNotification();
                }
            }
        });
        form.eventDelete(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{    
                    serviceVirement.deleteVirement(id);
                    Notification panel = new Notification(Main.this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Suppression réussi");
                    panel.showNotification();
                    loadVirement("");
                    GlassPanePopup.closePopupLast();
                }catch (SQLException ex) {
                    Notification panel = new Notification(Main.this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, ex.getMessage());
                    panel.showNotification();
                }
            } 
        });
        
        GlassPanePopup.showPopup(form);
    }//GEN-LAST:event_tableVirementMousePressed

    //PRÊT
    private void txtSearch3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearch3KeyReleased
        // TODO add your handling code here:
        if(txtSearch3.isSelected()){
            int option = txtSearch3.getSelectedIndex();
            String text = "%" + txtSearch3.getText().trim() + "%";
            if (option == 0){
                loadPret("WHERE num_pret LIKE ?", text);
            }else if (option == 1){
                loadPret("WHERE num_compte LIKE ?", text);
            }
        }
    }//GEN-LAST:event_txtSearch3KeyReleased

    private void btnNewPretActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewPretActionPerformed
        // TODO add your handling code here:
        FormPret form = new FormPret(FormPret.Type.INSERT);
        ControllerPret servicePret = new ControllerPret();
        
        form.eventAdd(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!form.getNumComptePret().isEmpty() && !form.getMontant().isEmpty()){
                        ModelPret pret = new ModelPret(form.getNumComptePret(), Integer.parseInt(form.getMontant()));
                        try{
                            if(servicePret.numExiste(pret.getNumCompte())){                                
                                servicePret.insertPret(pret);
                                Notification panel = new Notification(Main.this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Nouvelle prêt enregistré.");                            
                                panel.showNotification();
                                loadPret("");
                                GlassPanePopup.closePopupLast();
                                ControllerMail.sendMail(pret.getMontantPret(), pret.getNumCompte(), Main.this);
                            }else{
                                Notification notif = new Notification(Main.this, Notification.Type.ERROR, Notification.Location.TOP_CENTER, "Numero de compte innéxistant.");
                                notif.showNotification();
                            }
                        }catch(SQLException ex){
                            Notification panel = new Notification(Main.this, Notification.Type.ERROR, Notification.Location.TOP_CENTER, "Il ya un erreur sur l'ajout, veuillez verifier.");
                            panel.showNotification();
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (MessagingException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                            Notification panel = new Notification(Main.this, Notification.Type.ERROR, Notification.Location.TOP_CENTER, "E-mail non envoyé, verifier votre connexion.");
                            panel.showNotification();
                        }
                    }else{
                        Notification panel = new Notification(Main.this, Notification.Type.INFO, Notification.Location.TOP_CENTER, "Veuillez remplir tous les champs d'abords.");
                        panel.showNotification();
                    }
                }
            });
            GlassPanePopup.showPopup(form);
        
    }//GEN-LAST:event_btnNewPretActionPerformed

    private void tablePretMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePretMousePressed
        // TODO add your handling code here:
        int row = tablePret.getSelectedRow();
        int id =  (int) tablePret.getValueAt(row, 0);
       
        Connection con = DatabaseConnection.getInstance().getConnection();
        
        FormPret form = new FormPret(FormPret.Type.UPDATE);
        try{
            try(PreparedStatement p = con.prepareStatement("SELECT * FROM preter WHERE num_pret = ?")){
                p.setInt(1, id);
                try(ResultSet r = p.executeQuery()){
                    while(r.next()){
                        form.setNumComptePret(r.getString("num_compte").trim());
                        form.setMontant(r.getInt(3));
                    }
                }
            } 
        }catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ControllerPret servicePret = new ControllerPret();
        String numCompte = form.getNumComptePret();
        int montant = Integer.parseInt(form.getMontant());
        form.eventUpdate(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!form.getNumComptePret().isEmpty() && !form.getMontant().isEmpty()){
                    try {
                        servicePret.transaction(numCompte, montant, ControllerPret.Type.MINUS);
                    }catch (SQLException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ModelPret pret = new ModelPret(form.getNumComptePret(), Integer.parseInt(form.getMontant()));                 
                    try{
                        if (servicePret.numExiste(pret.getNumCompte())){
                            servicePret.updatePret(pret, id);
                            loadData("");
                            Notification panel = new Notification(Main.this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Mis à jour de prêt réussi");
                            panel.showNotification();
                            loadPret("");
                            GlassPanePopup.closePopupLast();
                        }else{
                            Notification notif = new Notification(Main.this, Notification.Type.ERROR, Notification.Location.TOP_CENTER, "Numero de compte innéxistant.");
                            notif.showNotification();
                        }
                    }catch(SQLException ex){
                        Notification panel = new Notification(Main.this, Notification.Type.ERROR, Notification.Location.TOP_CENTER, "il y a un probleme sur le serveur.");
                        panel.showNotification();
                    }
                }else{
                    Notification panel = new Notification(Main.this, Notification.Type.INFO, Notification.Location.TOP_CENTER, "Veuillez remplir tous les champs d'abords");
                    panel.showNotification();
                }
            }
            
        });
        form.eventDelete(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{    
                    servicePret.deletePret(id);
                    Notification panel = new Notification(Main.this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Suppression d'un prêt réussi");
                    panel.showNotification();
                    loadPret("");
                    GlassPanePopup.closePopupLast();
                }catch (SQLException ex) {
                    Notification panel = new Notification(Main.this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, ex.getMessage());
                    panel.showNotification();
                }
            }
        });
        
        GlassPanePopup.showPopup(form);
    }//GEN-LAST:event_tablePretMousePressed

    private void btnAvisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvisActionPerformed
        // TODO add your handling code here:
        AvisDeVirement pdf = new AvisDeVirement();
        GlassPanePopup.showPopup(pdf);
    }//GEN-LAST:event_btnAvisActionPerformed

    //RENDRE
    private void txtSearch4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearch4KeyReleased
        // TODO add your handling code here:
        if(txtSearch4.isSelected()){
            int option = txtSearch4.getSelectedIndex();
            String text = "%" + txtSearch4.getText().trim() + "%";
            switch (option) {
                case 0 -> loadRendu("WHERE num_rendu LIKE ?", text);
                case 1 -> loadRendu("WHERE num_pret LIKE ?", text);
                case 2 -> loadRendu("WHERE situation LIKE ?", text);
                default -> {
                }
            }
        }
    }//GEN-LAST:event_txtSearch4KeyReleased

    private void btnNewRenduActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewRenduActionPerformed
        // TODO add your handling code here:
        FormRendu form = new FormRendu(FormRendu.Type.INSERT);
        ControllerRendu serviceRendu = new ControllerRendu();
        
        form.eventAdd(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    if (!form.getNumPret().isEmpty() && !form.getMontant().isEmpty()){
                        ModelRendu rendu = new ModelRendu(Integer.parseInt(form.getNumPret()), Integer.parseInt(form.getMontant()));
                        try{
                            if(serviceRendu.numPretExiste(rendu.getNumPret())){
                                if(serviceRendu.existPret(rendu.getNumPret())){
                                    serviceRendu.update(rendu, Main.this);
                                }else{
                                    serviceRendu.insertRendu(rendu);
                                    Notification panel = new Notification(Main.this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Vous avez payé une part de votre prêt.");                            
                                    panel.showNotification();
                                }
                                
                                loadRendu("");
                                GlassPanePopup.closePopupLast();
                            }else{
                                Notification notif = new Notification(Main.this, Notification.Type.ERROR, Notification.Location.TOP_CENTER, "Numéro de prêt innéxistant.");
                                notif.showNotification();
                            }
                        }catch(SQLException ex){
                            Notification panel = new Notification(Main.this, Notification.Type.ERROR, Notification.Location.TOP_CENTER, "Il ya une erreur sur le payment, veuillez verifier.");
                            panel.showNotification();
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{
                        Notification panel = new Notification(Main.this, Notification.Type.INFO, Notification.Location.TOP_CENTER, "Veuillez remplir tous les champs d'abords.");
                        panel.showNotification();
                    }
                }
        });
        GlassPanePopup.showPopup(form);
    }//GEN-LAST:event_btnNewRenduActionPerformed

    private void tableRendreMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableRendreMousePressed
        // TODO add your handling code here:
        int row = tableRendre.getSelectedRow();
        int id =  (int) tableRendre.getValueAt(row, 0);
       
        Connection con = DatabaseConnection.getInstance().getConnection();
        
        FormRendu form = new FormRendu(FormRendu.Type.UPDATE);
        try{
            try(PreparedStatement p = con.prepareStatement("SELECT * FROM rendre WHERE num_rendu = ?")){
                p.setInt(1, id);
                try(ResultSet r = p.executeQuery()){
                    while(r.next()){
                        form.setNumPret(r.getString(2).trim());
                        form.setMontant(r.getInt(4));
                    }
                }
            } 
        }catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ControllerRendu serviceRendu = new ControllerRendu();
        
        form.eventUpdate(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!form.getNumPret().isEmpty() && !form.getMontant().isEmpty()){
                        int numPret = Integer.parseInt(form.getNumPret());
                        int rest = Integer.parseInt(form.getMontant());
                    ModelRendu rendu = new ModelRendu(numPret, rest);                 
                    try{
                        if (serviceRendu.numPretExiste(rendu.getNumPret())){
                            serviceRendu.updateRendu(rendu, id, Main.this);
                            loadRendu("");
                            Notification panel = new Notification(Main.this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Mis à jour de prêt réussi");
                            panel.showNotification();
                            GlassPanePopup.closePopupLast();
                        }else{
                            Notification notif = new Notification(Main.this, Notification.Type.ERROR, Notification.Location.TOP_CENTER, "Numero de compte innéxistant.");
                            notif.showNotification();
                        }
                    }catch(SQLException ex){
                        Notification panel = new Notification(Main.this, Notification.Type.ERROR, Notification.Location.TOP_CENTER, "il y a un probleme sur le serveur.");
                        panel.showNotification();
                    }
                }else{
                    Notification panel = new Notification(Main.this, Notification.Type.INFO, Notification.Location.TOP_CENTER, "Veuillez remplir tous les champs d'abords");
                    panel.showNotification();
                }
            }
            
        });
        form.eventDelete(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{    
                    serviceRendu.deleteRendu(id);
                    Notification panel = new Notification(Main.this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Suppression d'un prêt réussi");
                    panel.showNotification();
                    loadRendu("");
                    GlassPanePopup.closePopupLast();
                }catch (SQLException ex) {
                    Notification panel = new Notification(Main.this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, ex.getMessage());
                    panel.showNotification();
                }
            }
        });
        
        GlassPanePopup.showPopup(form);
    }//GEN-LAST:event_tableRendreMousePressed

    private void btnNewPret1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewPret1ActionPerformed
        // TODO add your handling code here:
        Situation s = new Situation();
        GlassPanePopup.showPopup(s);
    }//GEN-LAST:event_btnNewPret1ActionPerformed

    
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                DatabaseConnection.getInstance().ConnectionToDatabase();
                System.out.println("Database connected");
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            new com.dojo.component.SplashScreen(null, true).setVisible(true);
            new Main().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.k33ptoo.components.KGradientPanel ClientPanel;
    private com.k33ptoo.components.KGradientPanel ContentPanel;
    private com.k33ptoo.components.KGradientPanel MenuPanel;
    private com.k33ptoo.components.KGradientPanel PretPanel;
    private com.k33ptoo.components.KGradientPanel RendrePanel;
    private com.k33ptoo.components.KGradientPanel VirementPanel;
    private com.k33ptoo.components.KButton btnAvis;
    private com.k33ptoo.components.KButton btnConnecter;
    private com.k33ptoo.components.KButton btnNewClient;
    private com.k33ptoo.components.KButton btnNewPret;
    private com.k33ptoo.components.KButton btnNewPret1;
    private com.k33ptoo.components.KButton btnNewRendu;
    private com.k33ptoo.components.KButton btnNewVirement;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private com.k33ptoo.components.KGradientPanel kGradientPanel1;
    private com.k33ptoo.components.KGradientPanel kGradientPanel10;
    private com.k33ptoo.components.KGradientPanel kGradientPanel2;
    private com.k33ptoo.components.KGradientPanel kGradientPanel3;
    private com.k33ptoo.components.KGradientPanel kGradientPanel4;
    private com.k33ptoo.components.KGradientPanel kGradientPanel5;
    private com.k33ptoo.components.KGradientPanel kGradientPanel7;
    private com.k33ptoo.components.KGradientPanel kGradientPanel8;
    private com.k33ptoo.components.KGradientPanel kGradientPanel9;
    private com.k33ptoo.components.KGradientPanel loginPanel;
    private com.k33ptoo.components.KGradientPanel mainPanel;
    private com.dojo.component.Menu menu1;
    private com.dojo.component.Menu menu2;
    private com.dojo.component.Menu menu3;
    private com.dojo.component.Menu menu4;
    private javax.swing.JScrollPane spClient;
    private javax.swing.JScrollPane spPret;
    private javax.swing.JScrollPane spRendre;
    private javax.swing.JScrollPane spVirement;
    private com.dojo.component.Table tableClient;
    private com.dojo.component.Table tablePret;
    private com.dojo.component.Table tableRendre;
    private com.dojo.component.Table tableVirement;
    private com.dojo.component.TextPassword txtPassword;
    private com.dojo.swing.TextFieldSearchOption txtSearch;
    private com.dojo.swing.TextFieldSearchOption txtSearch1;
    private com.dojo.swing.TextFieldSearchOption txtSearch3;
    private com.dojo.swing.TextFieldSearchOption txtSearch4;
    private com.dojo.component.TextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
