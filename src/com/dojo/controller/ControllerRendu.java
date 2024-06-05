/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dojo.controller;

import com.dojo.connection.DatabaseConnection;
import com.dojo.main.Main;
import com.dojo.model.ModelRendu;
import com.dojo.swing.Notification;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ando Henri
 */
public class ControllerRendu {
    private final Connection con;
    private int solde;
    private int restAPaye;
    private int numRendu;
    private String situation;
    
    public ControllerRendu() {
        con = DatabaseConnection.getInstance().getConnection();
    }
    
    public void insertRendu(ModelRendu rendu)throws SQLException{
        transaction(rendu.getNumPret(), rendu.getSoldeARendre(), Type.MINUS);
        try(PreparedStatement p = con.prepareStatement("SELECT montant_preter FROM preter WHERE num_pret = ?")){
            p.setInt(1, rendu.getNumPret());
            try(ResultSet r = p.executeQuery()){
                if(r.next()){
                    solde = r.getInt("montant_preter");
                }
            }
        }
        restAPaye = solde + (solde/10) - rendu.getSoldeARendre();
        try(PreparedStatement p = con.prepareStatement("INSERT INTO rendre (num_pret, situation, rest_paye) VALUES (?, ?, ?)")){
            if(restAPaye > 0){
                situation = Status.nonPAYE.toString();   
            } else {
                situation = Status.PAYE.toString();
            }
            p.setInt(1, rendu.getNumPret());
            p.setString(2, situation);
            p.setInt(3, restAPaye);
            p.executeUpdate();
        }
    }
    
    public void updateRendu(ModelRendu rendu, int id, Main aThis) throws SQLException{
        if(rendu.getSoldeARendre() == 0){
            Notification panel = new Notification(aThis, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Le prêt que vous essayez de rendre est déja payé totalement.");                            
            panel.showNotification();
        }else{
            try(PreparedStatement s = con.prepareStatement("UPDATE rendre SET situation = ?, rest_paye = ?  WHERE num_rendu = ?")){
                if(rendu.getSoldeARendre() > 0){
                    situation = Status.nonPAYE.toString();   
                } else {
                    situation = Status.PAYE.toString();
                }
                s.setString(1, situation);
                s.setInt(2, rendu.getSoldeARendre());
                s.setInt(3, id);
                s.executeUpdate();
            }
        }
    }
    
    public void deleteRendu(int id) throws SQLException{
        try(PreparedStatement s = con.prepareStatement("DELETE FROM rendre WHERE num_rendu = ?")){
            s.setInt(1, id);
            s.executeUpdate();                
        }
    }
    public void transaction(int num1, int montant, Type status) throws SQLException{
        try(Statement s = con.createStatement()){
            if(status == Type.PLUS){
                s.executeUpdate("UPDATE client SET solde = solde + "+montant+" WHERE num_compte = (SELECT num_compte FROM preter WHERE num_pret = "+num1+")");
                s.executeUpdate("UPDATE rendre SET rest_paye = rest_paye + "+montant+", situation = 'nonPAYE' WHERE num_rendu = (SELECT num_rendu FROM rendre WHERE num_pret = "+num1+")");
            }else if (status == Type.MINUS){
                s.executeUpdate("UPDATE client SET solde = solde - "+montant+" WHERE num_compte = (SELECT num_compte FROM preter WHERE num_pret = "+num1+")");
            }
        }                
    }
    public boolean numPretExiste(int num) throws SQLException{
        try(Statement s = con.createStatement()){
            try(ResultSet r = s.executeQuery("SELECT * FROM preter WHERE num_pret = " + num)){
                return r.next();
            }
        }
    }
    public boolean existPret(int numPret) throws SQLException{
        try(Statement s = con.createStatement()){
            try(ResultSet r = s.executeQuery("SELECT * FROM rendre WHERE num_pret = " + numPret)){
                return r.next();
            }
        }
    }
    public void update(ModelRendu rendu, Main aThis) throws SQLException{
        try(PreparedStatement p = con.prepareStatement("SELECT num_rendu, rest_paye FROM rendre WHERE num_pret = ?")){
            p.setInt(1, rendu.getNumPret());
            try(ResultSet r = p.executeQuery()){
                if(r.next()){
                    numRendu = r.getInt("num_rendu");
                    restAPaye = r.getInt("rest_paye");
                }
                if(restAPaye == 0){
                    Notification panel = new Notification(aThis, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Le prêt que vous essayez de rendre est déja payé totalement.");                            
                    panel.showNotification();
                }else{
                    try(PreparedStatement s = con.prepareStatement("UPDATE rendre SET situation = ?, rest_paye = ?  WHERE num_rendu = ?")){
                        restAPaye = restAPaye - rendu.getSoldeARendre();
                        System.out.println(restAPaye);
                        if(restAPaye > 0){
                            situation = Status.nonPAYE.toString();   
                        } else {
                            situation = Status.PAYE.toString();
                        }
                        s.setString(1, situation);
                        s.setInt(2, restAPaye);
                        s.setInt(3, numRendu);
                        s.executeUpdate();
                    }
                }
            }   
        }     
    }
    public enum Type{
        PLUS, MINUS
    }
    public enum Status{
        PAYE, nonPAYE 
    }
}
