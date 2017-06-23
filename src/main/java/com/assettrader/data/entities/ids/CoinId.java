package com.assettrader.data.entities.ids;

import java.io.Serializable;

public class CoinId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String marketName;

	public CoinId() {
		
	}
	
	public CoinId(Long id, String marketName) {
		this.id = id;
		this.marketName = marketName;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}
	
	
}
