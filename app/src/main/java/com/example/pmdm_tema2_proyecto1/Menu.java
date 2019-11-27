package com.example.pmdm_tema2_proyecto1;


import java.io.Serializable;
public class Menu implements Serializable {
    private String clientName;
    private Item pizza;
    private int nPizzas = 1;
    private Item drink;
    private double totalPrice;

    public Menu(){
    pizza= new Item("",0);
    drink = new Item("",0);
    clientName="";

    }


    public double getTotalPrice() {
        return totalPrice;
    }

    public int getnPizzas() {
        return nPizzas;
    }

    public Item getDrink() {
        return drink;
    }
    public Item getPizza() {
        return pizza;
    }

    public String getClientName() {
        return clientName;
    }

    public void setPizza(Item pizza) {
        this.pizza = pizza;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setDrink(Item drink) {
        this.drink = drink;
    }

    public void setnPizzas(int nPizzas) {
        this.nPizzas = nPizzas;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
