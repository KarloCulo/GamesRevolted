package com.example.testnizadatak.models;

public enum Modifier {
    BASIC(1,  0.1,   0.6),
    SILVER(2,       0.2,   0.7),
    GOLD(3,0.3,0.8);


    private final int id;
    private final double discountMode;
    private final double balanceMode;

    private Modifier(int id, double discountMode, double balanceMode) {
        this.id = id;
        this.discountMode = discountMode;
        this.balanceMode = balanceMode;
    }

    public int getId() {
        return id;
    }

    public double getDiscountMode() {
        return discountMode;
    }

    public double getBalanceMode(){
        return balanceMode;
    }
}
