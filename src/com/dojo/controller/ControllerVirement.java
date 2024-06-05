/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dojo.controller;

import com.dojo.connection.DatabaseConnection;
import com.dojo.model.ModelVirement;
import java.sql.Connection;
import java.sql.*;
/**
 *
 * @author Ando Henri
 */
public class ControllerVirement {

    private final Connection con;

    public ControllerVirement() {
        con = DatabaseConnection.getInstance().getConnection();
    }
    
    public void updateVirement(ModelVirement virement ,int id) throws SQLException{
        transaction(virement.getNum1(), virement.getNum2(), virement.getMontant());
        try(PreparedStatement p = con.prepareStatement("UPDATE virement SET num_compte1 = ?, num_compte2 = ?, montant =  ? WHERE num_virement = ?")){
            p.setString(1, virement.getNum1());
            p.setString(2, virement.getNum2());
            p.setInt(3, virement.getMontant());
            p.setInt(4, id);
            p.executeUpdate();
        }
    }
    public void insertVirement(ModelVirement virement) throws SQLException{
        transaction(virement.getNum1(), virement.getNum2(), virement.getMontant());
        try(PreparedStatement p = con.prepareStatement("INSERT INTO virement VALUES (?, ?, ?)")){
            p.setString(1, virement.getNum1());
            p.setString(2, virement.getNum2());
            p.setInt(3, virement.getMontant());
            p.executeUpdate();
        }
    }
    public void deleteVirement(int id)throws SQLException {
        try(PreparedStatement s = con.prepareStatement("DELETE FROM virement WHERE num_virement = ?")){
            s.setInt(1, id);
            s.executeUpdate();                
        }
    }
    public boolean isValidAmount(String num, int solde) throws SQLException{
        try(Statement s = con.createStatement()){
            try(ResultSet r = s.executeQuery("SELECT solde FROM client WHERE num_compte = '"+num+"' AND solde > "+solde)){
                return r.next();
            }
        }
    }
    public boolean numExiste(String num) throws SQLException{
        try(Statement s = con.createStatement()){
            try(ResultSet r = s.executeQuery("SELECT * FROM client WHERE num_compte = '"+num+"'")){
                return r.next();
            }
        }
    }
    public void transaction(String num1, String num2, int montant) throws SQLException{
        try(Statement s = con.createStatement()){
            s.executeUpdate("UPDATE client SET solde = solde - "+montant+" WHERE num_compte = '"+num1+"'");
            s.executeUpdate("UPDATE client SET solde = solde + "+montant+" WHERE num_compte = '"+num2+"'");
        }                
    }
}
