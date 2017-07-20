package com.assettrader.entities.ids;

import java.io.Serializable;

import com.assettrader.model.utils.WalletOrigin;

public class CoinId implements Serializable {

	private static final long serialVersionUID = 1L;

	private String marketName;
	private WalletOrigin exchangeName;

	public CoinId() {

	}

	public CoinId(String marketName, WalletOrigin exchange) {
		this.marketName = marketName;
		this.exchangeName = exchange;
	}

	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	public WalletOrigin getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(WalletOrigin exchange) {
		this.exchangeName = exchange;
	}

}
