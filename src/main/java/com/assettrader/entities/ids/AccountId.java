package com.assettrader.entities.ids;

import java.io.Serializable;

import com.assettrader.model.UserProfile;

public class AccountId implements Serializable {

	private static final long serialVersionUID = -5874619578460890796L;
	
	private String exchangeName;
	
	private String currency;
	
	private UserProfile userProfile;
	
	public AccountId() {}

	public AccountId(UserProfile userProfile, String exchangeName, String currency) {
		this.userProfile = userProfile;
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

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

}
