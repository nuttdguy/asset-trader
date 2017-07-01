package com.assettrader.model.publicapi.DTO;

public class OrderBookDTO {
	
	private BuyDTO[] buy;
	
	private SellDTO[] sell;

	public BuyDTO[] getBuy() {
		return buy;
	}

	public void setBuy(BuyDTO[] buy) {
		this.buy = buy;
	}

	public SellDTO[] getSell() {
		return sell;
	}

	public void setSell(SellDTO[] sell) {
		this.sell = sell;
	}
	
	

}
