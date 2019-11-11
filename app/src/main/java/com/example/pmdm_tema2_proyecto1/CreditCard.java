package com.example.pmdm_tema2_proyecto1;

import android.os.SystemClock;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class CreditCard implements Serializable {


    private String numCard = null;
    private String ownerName = null;
    private String expireDate = null;
    private Integer cvv = null;
    private String type = null;



    public CreditCard(){

    }

    public boolean checkExpireDate(){
        Calendar fecha = Calendar.getInstance();
        String[] parts = expireDate.split("/");
        String anyoActual = String.valueOf(fecha.get(Calendar.MONTH)).substring(2,3);
        if(Integer.parseInt(anyoActual) < Integer.parseInt(parts[1])){
            return false;
        }else if(fecha.get(Calendar.DAY_OF_MONTH) < Integer.parseInt(parts[0])) {
            return false;
        }else {
            return true;
        }
    }
    public String getCensoredCreditCard(){
        return "**** **** **** " + numCard.substring(15);
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumCard() {
        return numCard;
    }

    public void setNumCard(String numCard) {
        this.numCard = numCard;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }



}
