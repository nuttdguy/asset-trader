package com.assettrader.entities.ids;

import java.io.Serializable;

import com.assettrader.model.UserProfile;

public class AccountId implements Serializable {

	private static final long serialVersionUID = -5874619578460890796L;
	
	private String exchangeName;
	
	private String currency;
	
	
	public AccountId() {}

	public AccountId(String exchangeName, String currency) {
		this.exchangeName = exchangeName;
		this.currency = currency;
	}

	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
