/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dojo.model;

import java.security.Timestamp;

/**
 *
 * @author Ando Henri
 */
public class ModelRendu {
    private int numRendu;
    private int numPret;
    private String status;
    private int rest;
    private int soldeARendre;
    private Timestamp dateRendu;

    public ModelRendu() {
    }
    
    public ModelRendu(int numPret, int solde) {
        this.numPret = numPret;
        this.soldeARendre = solde;
    }

    public ModelRendu(int numRendu, int numPret, String status, int rest,int solde, Timestamp dateRendu) {
        this.numRendu = numRendu;
        this.numPret = numPret;
        this.status = status;
        this.rest = rest;
        this.soldeARendre = solde;
        this.dateRendu = dateRendu;
    }
    
    

    public int getNumRendu() {
        return numRendu;
    }

    public void setNumRendu(int numRendu) {
        this.numRendu = numRendu;
    }

    public int getNumPret() {
        return numPret;
    }

    public void setNumPret(int numPret) {
        this.numPret = numPret;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRest() {
        return rest;
    }

    public void setRest(int rest) {
        this.rest = rest;
    }

    public Timestamp getDateRendu() {
        return dateRendu;
    }

    public void setDateRendu(Timestamp dateRendu) {
        this.dateRendu = dateRendu;
    }
    public int getSoldeARendre() {
        return soldeARendre;
    }

    public void setSoldeARendre(int soldeARendre) {
        this.soldeARendre = soldeARendre;
    }
}
