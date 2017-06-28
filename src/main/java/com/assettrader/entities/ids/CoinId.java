package com.assettrader.entities.ids;

import java.io.Serializable;

import com.assettrader.model.utils.ExchangeName;

public class CoinId implements Serializable {

	private static final long serialVersionUID = 1L;

	private String marketName;
	private ExchangeName exchange;

	public CoinId() {

	}

	public CoinId(String marketName, ExchangeName exchange) {
		this.marketName = marketName;
		this.exchange = exchange;
	}

	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	public ExchangeName getExchange() {
		return exchange;
	}

	public void setExchange(ExchangeName exchange) {
		this.exchange = exchange;
	}

}
