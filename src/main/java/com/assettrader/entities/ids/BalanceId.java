package com.assettrader.entities.ids;

import java.io.Serializable;

public class BalanceId implements Serializable {

	private static final long serialVersionUID = -2741632730731334929L;

	private String currency;
	
	private String cryptoAddress;

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCryptoAddress() {
		return cryptoAddress;
	}

	public void setCryptoAddress(String cryptoAddress) {
		this.cryptoAddress = cryptoAddress;
	}
	
}
