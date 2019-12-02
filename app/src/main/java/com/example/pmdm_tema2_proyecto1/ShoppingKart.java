package com.example.pmdm_tema2_proyecto1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShoppingKart implements Serializable {


    private String clientName;
    private ArrayList<Menu> menuList;
    private Double totalPrice = 0.0;

    public ShoppingKart(){
        menuList = new ArrayList<>();
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public ArrayList<Menu> getMenuList() {
        return menuList;
    }


    public void addMenu(Menu m){
        menuList.add(m);
        recalcularTotal();
    }
    public void removeMenu(Menu m){
        menuList.remove(m);
        recalcularTotal();
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    private void recalcularTotal(){
    totalPrice = 0.0;
        for (Menu c: menuList) {
            totalPrice+=c.getTotalPrice();
        }

    }
}


