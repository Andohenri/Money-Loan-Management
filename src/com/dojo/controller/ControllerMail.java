/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dojo.controller;

import com.dojo.connection.DatabaseConnection;
import com.dojo.main.Main;
import com.dojo.swing.Notification;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 *
 * @author Ando Henri
 */
public class ControllerMail {
        
    public static void sendMail(int amount, String numCompte, Main aThis) throws MessagingException, SQLException{
        String address = "andohenrirazafinatoandro@gmail.com";
        String addressTo = "";
        int aPaye = amount + amount/10;
        Connection con = DatabaseConnection.getInstance().getConnection();
        try (PreparedStatement p = con.prepareStatement("SELECT mail FROM client WHERE num_compte = ?")) {
            p.setString(1, numCompte);
            try (ResultSet r = p.executeQuery()) {
                if(r.next()){
                    addressTo = r.getString("mail");
                }
            }
        }
        String password = "xdbqouvttubypljz";
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(address, password);
            }
        });
        Calendar cal = Calendar.getInstance();
        Date dateRendu = cal.getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd MMMM Y");
        String date = format.format(dateRendu);
        
        Message message = new MimeMessage(session);
        message.setSubject("Reste à payé");
        message.setContent("Vous avez fait un prêt de "+ amount +".\nIl vous reste "+ aPaye +" Ar à payé jusqu'à "+ date + " avec un taux de 10%", "text/plain");
        message.setFrom(new InternetAddress(address));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(addressTo));
        message.setSentDate(new Date());
        Transport.send(message);
        Notification panel = new Notification(aThis, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "E-mail bien envoyé.");                            
        panel.showNotification();
    }
}
