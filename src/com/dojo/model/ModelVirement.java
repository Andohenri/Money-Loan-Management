/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dojo.model;

/**
 *
 * @author Ando Henri
 */
public class ModelVirement {
    private String num1;
    private String num2;
    private int montant;

    public ModelVirement() {
    }

    public ModelVirement(String num1, String num2, int montant) {
        this.num1 = num1;
        this.num2 = num2;
        this.montant = montant;
    }

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }
}
