package com.assettrader.model.DTO;

import java.util.Date;

public class CoinDTO {

	private String marketName;

	private String baseCurrency;

	private String baseCurrencyLong;

	private String marketCurrency;

	private String marketCurrencyLong;

	private boolean isActive;

	private Date createDate;

	private String logoUrl;

	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public String getBaseCurrencyLong() {
		return baseCurrencyLong;
	}

	public void setBaseCurrencyLong(String baseCurrencyLong) {
		this.baseCurrencyLong = baseCurrencyLong;
	}

	public String getMarketCurrency() {
		return marketCurrency;
	}

	public void setMarketCurrency(String marketCurrency) {
		this.marketCurrency = marketCurrency;
	}

	public String getMarketCurrencyLong() {
		return marketCurrencyLong;
	}

	public void setMarketCurrencyLong(String marketCurrencyLong) {
		this.marketCurrencyLong = marketCurrencyLong;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

}
