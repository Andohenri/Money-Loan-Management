package com.dojo.controller;

import com.dojo.connection.DatabaseConnection;
import com.dojo.model.ModelClient;
import java.sql.*;
import java.time.LocalDateTime;

/**
 *
 * @author Ando Henri
 */
public class ControllerClient {

    private final Connection con;
    
    public ControllerClient() {
        con = DatabaseConnection.getInstance().getConnection();
    }
    
    public void insertClient(ModelClient client)throws SQLException{
        try(PreparedStatement p = con.prepareStatement("INSERT INTO client (num_compte, nom, prenom, tel, mail, solde) VALUES (?,?,?,?,?,?)")){
            String s = String.valueOf(LocalDateTime.now());
            String id = String.valueOf(s.hashCode()).replace("-", "");
            p.setString(1, id);
            p.setString(2, client.getNom());
            p.setString(3, client.getPrenom());
            p.setString(4, client.getTel());
            p.setString(5, client.getMail());
            p.setInt(6, client.getSolde());
            p.executeUpdate();
        }
    }
    
    public void updateClient(ModelClient client, String id) throws SQLException{
        try(PreparedStatement p = con.prepareStatement("UPDATE client SET nom = ?, prenom = ?, tel = ?, mail = ?, solde = ? WHERE num_compte = ?")){
            p.setString(1, client.getNom());
            p.setString(2, client.getPrenom());
            p.setString(3, client.getTel());
            p.setString(4, client.getMail());
            p.setInt(5, client.getSolde());
            p.setString(6, id);
            p.executeUpdate();
        }
    }
    
    public void deleteClient(String id) throws SQLException{
        try(PreparedStatement s = con.prepareStatement("DELETE FROM client WHERE num_compte = ?")){
            s.setString(1, id);
            s.executeUpdate();                
        }
    }
}
