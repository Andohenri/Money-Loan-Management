package com.dojo.connection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
/**
 * @author Ando Henri
 */
public class DatabaseConnection {
    
    private static DatabaseConnection instance;
    private Connection connection;
    
    public static DatabaseConnection getInstance(){
        if (instance == null){
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
    private DatabaseConnection(){

    }
    public void ConnectionToDatabase() throws SQLException, ClassNotFoundException {
        String server = "localhost";
        String port = "5432";
        String database = "banque";
        String username = "postgres";
        String password = "3550010bona";
        connection = DriverManager.getConnection("jdbc:postgresql://" + server + ":" + port + "/" + database, username, password);
    }
    public Connection getConnection(){
        return connection;
    }
    public void setConnection(Connection connection){
        this.connection = connection;
    }
}