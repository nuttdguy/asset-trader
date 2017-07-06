package com.assettrader.entities.ids;

import java.io.Serializable;

import com.assettrader.model.utils.ExchangeName;

public class CoinId implements Serializable {

	private static final long serialVersionUID = 1L;

	private String marketName;
	private ExchangeName exchangeName;

	public CoinId() {

	}

	public CoinId(String marketName, ExchangeName exchange) {
		this.marketName = marketName;
		this.exchangeName = exchange;
	}

	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	public ExchangeName getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(ExchangeName exchange) {
		this.exchangeName = exchange;
	}

}
