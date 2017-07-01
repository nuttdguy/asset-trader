package com.assettrader.model.publicapi.DTO;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "success", "message", "result", "id" })
public class CoinDTO {

	private String MarketName;

	private String BaseCurrency;

	private String BaseCurrencyLong;

	private String MarketCurrency;

	private String MarketCurrencyLong;

	private boolean IsActive;

	private Date CreateDate;

	private String LogoUrl;

	private String MinTradeSize;

	private Date Created;

	private String Notice;

	private boolean IsSponsored;

	public String getMarketName() {
		return MarketName;
	}

	public void setMarketName(String marketName) {
		MarketName = marketName;
	}

	public String getBaseCurrency() {
		return BaseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		BaseCurrency = baseCurrency;
	}

	public String getBaseCurrencyLong() {
		return BaseCurrencyLong;
	}

	public void setBaseCurrencyLong(String baseCurrencyLong) {
		BaseCurrencyLong = baseCurrencyLong;
	}

	public String getMarketCurrency() {
		return MarketCurrency;
	}

	public void setMarketCurrency(String marketCurrency) {
		MarketCurrency = marketCurrency;
	}

	public String getMarketCurrencyLong() {
		return MarketCurrencyLong;
	}

	public void setMarketCurrencyLong(String marketCurrencyLong) {
		MarketCurrencyLong = marketCurrencyLong;
	}

	public boolean isIsActive() {
		return IsActive;
	}

	public void setIsActive(boolean isActive) {
		IsActive = isActive;
	}

	public Date getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}

	public String getLogoUrl() {
		return LogoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		LogoUrl = logoUrl;
	}

	public String getMinTradeSize() {
		return MinTradeSize;
	}

	public void setMinTradeSize(String minTradeSize) {
		MinTradeSize = minTradeSize;
	}

	public Date getCreated() {
		return Created;
	}

	public void setCreated(Date created) {
		Created = created;
	}

	public String getNotice() {
		return Notice;
	}

	public void setNotice(String notice) {
		Notice = notice;
	}

	public boolean isIsSponsored() {
		return IsSponsored;
	}

	public void setIsSponsored(boolean isSponsored) {
		IsSponsored = isSponsored;
	}

	@Override
	public String toString() {
		return "CoinDTO [MarketName=" + MarketName + ", BaseCurrency=" + BaseCurrency + ", BaseCurrencyLong="
				+ BaseCurrencyLong + ", MarketCurrency=" + MarketCurrency + ", MarketCurrencyLong=" + MarketCurrencyLong
				+ ", IsActive=" + IsActive + ", CreateDate=" + CreateDate + ", LogoUrl=" + LogoUrl + "]";
	}

}
