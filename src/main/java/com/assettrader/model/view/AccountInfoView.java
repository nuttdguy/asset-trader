package com.assettrader.model.view;

public class AccountInfoView {

	
	private Long id; // IN COIN TABLE
	private String logo; // IN COIN TABLE
	private String marketName; // IN COIN TABLE
	private String marketCurrencyLong; // IN COIN TABLE
	private String marketCurrency; // IN COIN TABLE
	
	private double volume; // IN MARKET-SUMMARY
	private double buyOrders; // IN MARKET-SUMMARY
	private double sellOrders; // IN MARKET-SUMMARY

	private double high; // IN MARKET-SUMMARY
	private double low; // IN MARKET-SUMMARY
	
	private double price; // IN M-HISTORY
	private double last; // TICKER
	
	private double changePercent; // 
	// need to perform calculation on volume per day


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	public String getMarketCurrencyLong() {
		return marketCurrencyLong;
	}

	public void setMarketCurrencyLong(String marketCurrencyLong) {
		this.marketCurrencyLong = marketCurrencyLong;
	}

	public String getMarketCurrency() {
		return marketCurrency;
	}

	public void setMarketCurrency(String marketCurrency) {
		this.marketCurrency = marketCurrency;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getBuyOrders() {
		return buyOrders;
	}

	public void setBuyOrders(double buyOrders) {
		this.buyOrders = buyOrders;
	}

	public double getSellOrders() {
		return sellOrders;
	}

	public void setSellOrders(double sellOrders) {
		this.sellOrders = sellOrders;
	}

	public double getChangePercent() {
		return changePercent;
	}

	public void setChangePercent(double changePercent) {
		this.changePercent = changePercent;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getLast() {
		return last;
	}

	public void setLast(double last) {
		this.last = last;
	}

}
