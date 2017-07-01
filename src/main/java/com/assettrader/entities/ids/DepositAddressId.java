package com.assettrader.entities.ids;

import java.io.Serializable;

public class DepositAddressId implements Serializable  {

	private static final long serialVersionUID = -213741964356633946L;

	private String currency;
	
    private String address;
    
    public DepositAddressId() {}

	public DepositAddressId(String currency, String address) {
		this.currency = currency;
		this.address = address;
	}

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
