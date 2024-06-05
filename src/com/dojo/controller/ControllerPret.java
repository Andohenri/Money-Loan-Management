package com.dojo.controller;

import com.dojo.connection.DatabaseConnection;
import com.dojo.model.ModelPret;
import java.sql.*;

/**
 *
 * @author Ando Henri
 */
public class ControllerPret {

    private final Connection con;
    
    public ControllerPret() {
        con = DatabaseConnection.getInstance().getConnection();
    }
    
    public void insertPret(ModelPret pret)throws SQLException{
        transaction(pret.getNumCompte(), pret.getMontantPret(), Type.PLUS);
        try(PreparedStatement p = con.prepareStatement("INSERT INTO preter (num_compte, montant_preter) VALUES (?, ?)")){
            p.setString(1, pret.getNumCompte());
            p.setInt(2, pret.getMontantPret());
            p.executeUpdate();
            try(Statement s = con.createStatement()){
                double profit = pret.getMontantPret() / 10;
                s.executeUpdate("INSERT INTO profit (amount) VALUES ("+ profit +")");
            }
        }
    }
    
    public void updatePret(ModelPret pret, int id) throws SQLException{
        transaction(pret.getNumCompte(), pret.getMontantPret(), Type.PLUS);
        try(PreparedStatement p = con.prepareStatement("UPDATE preter SET num_compte = ?, montant_preter = ? WHERE num_pret = ?")){
            p.setString(1, pret.getNumCompte());
            p.setInt(2, pret.getMontantPret());
            p.setInt(3, id);
            p.executeUpdate();
        }
    }
    
    public void deletePret(int id) throws SQLException{
        try(PreparedStatement s = con.prepareStatement("DELETE FROM preter WHERE num_pret = ?")){
            s.setInt(1, id);
            s.executeUpdate();                
        }
    }
    public void transaction(String num1, int montant, Type status) throws SQLException{
        try(Statement s = con.createStatement()){
            if(status == Type.PLUS){
                s.executeUpdate("UPDATE client SET solde = solde + "+montant+" WHERE num_compte = '"+num1+"'");
            }else if (status == Type.MINUS){
                s.executeUpdate("UPDATE client SET solde = solde - "+montant+" WHERE num_compte = '"+num1+"'");
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
    public enum Type{
        PLUS, MINUS
    }
}
