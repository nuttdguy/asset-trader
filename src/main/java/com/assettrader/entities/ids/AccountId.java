package com.assettrader.entities.ids;

import java.io.Serializable;

public class AccountId implements Serializable {

	private static final long serialVersionUID = -5874619578460890796L;
	
	private String walletOrigin;
	
	private String currency;
	
	private String walletPrefix;
	
	public AccountId() {}

	public AccountId(String exchangeName, String currency) {
		this.walletOrigin = exchangeName;
		this.currency = currency;
	}

	public String getWalletOrigin() {
		return walletOrigin;
	}

	public void setWalletOrigin(String exchangeName) {
		this.walletOrigin = exchangeName;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getWalletPrefix() {
		return walletPrefix;
	}

	public void setWalletPrefix(String exchangeSuffix) {
		this.walletPrefix = exchangeSuffix;
	}

}
