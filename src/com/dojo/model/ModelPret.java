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
public class ModelPret {
    private int numPret;
    private String numCompte;
    private int montantPret;
    private Timestamp datePret;

    public ModelPret() {
    }

    public ModelPret(int numPret, String numCompte, int montantPret, Timestamp datePret) {
        this.numPret = numPret;
        this.numCompte = numCompte;
        this.montantPret = montantPret;
        this.datePret = datePret;
    }
    
    public ModelPret(String numCompte, int montantPret) {
        this.numCompte = numCompte;
        this.montantPret = montantPret;
    }

    public int getNumPret() {
        return numPret;
    }

    public void setNumPret(int numPret) {
        this.numPret = numPret;
    }

    public String getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(String numCompte) {
        this.numCompte = numCompte;
    }

    public int getMontantPret() {
        return montantPret;
    }

    public void setMontantPret(int montantPret) {
        this.montantPret = montantPret;
    }

    public Timestamp getDatePret() {
        return datePret;
    }

    public void setDatePret(Timestamp datePret) {
        this.datePret = datePret;
    }
    
}
