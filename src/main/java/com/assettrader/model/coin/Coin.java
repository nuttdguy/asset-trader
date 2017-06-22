package com.assettrader.model.coin;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "COIN")
public class Coin {

	@Column(name = "COIN_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Id
	@Column(name = "MARKET_NAME")
	private String marketName;

	@Column(name = "BASE_CURRENCY")
	private String baseCurrency;

	@Column(name = "BASE_CURRENCY_LONG")
	private String baseCurrencyLong;

	@Column(name = "MARKET_CURRENCY")
	private String marketCurrency;

	@Column(name = "MARKET_CURRENCY_LONG")
	private String marketCurrencyLong;

	@Column(name = "IS_ACTIVE")
	private boolean isActive;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE")
	private Date createDate;

	@Column(name = "LOGO_URL")
	private String logoUrl;


	@OneToMany(cascade = CascadeType.ALL, mappedBy = "coin")
	private List<OrderBook> orderBook;

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

	public List<OrderBook> getOrderBook() {
		return orderBook;
	}

	public void setOrderBook(List<OrderBook> orderBook) {
		this.orderBook = orderBook;
	}

}
