package com.assettrader.api.bittrex.model.accountapi;


public class DepositAddress {

    private String currency;
    private String address;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
