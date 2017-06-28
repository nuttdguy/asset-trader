package com.assettrader.model.coin;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.assettrader.entities.ids.CoinId;
import com.assettrader.model.Favorite;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "COIN")
@IdClass(CoinId.class)
public class Coin {

	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "COIN_ID", columnDefinition = "serial")
	@JsonIgnore
	private Long id;

	@Id
	@Column(name = "MARKET_NAME")
	private String marketName;

	@Id
	@Column(name = "EXCHANGE")
	private String exchange;

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

	@Transient
	private Date createDate;

	@Column(name = "LOGO_URL")
	private String logoUrl;

	@Column(name = "MIN_TRADE_SIZE")
	private String MinTradeSize;

	@Column(name = "CREATED")
	private Date Created;

	@Column(name = "NOTICE")
	private String Notice;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "coin")
	private List<OrderBook> orderBook;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "coin")
	private List<Currency> currencies;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "coin")
	private List<MarketSummary> marketSummaries;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "coin")
	private List<MarketHistory> marketHistories;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "coin")
	private List<Ticker> tickers;

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable( name = "USER_COIN_FAVORITE",
				joinColumns={ @JoinColumn(name = "MARKET_NAME", insertable=true, updatable=true),
								@JoinColumn(name = "EXCHANGE", insertable=true, updatable=true) },
				inverseJoinColumns= @JoinColumn( name = "FAVORITE_ID"))
	private List<Favorite> userFavorites;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
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

	public List<OrderBook> getOrderBook() {
		return orderBook;
	}

	public void setOrderBook(List<OrderBook> orderBook) {
		this.orderBook = orderBook;
	}

	public List<Currency> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(List<Currency> currencies) {
		this.currencies = currencies;
	}

	public List<MarketSummary> getMarketSummaries() {
		return marketSummaries;
	}

	public void setMarketSummaries(List<MarketSummary> marketSummaries) {
		this.marketSummaries = marketSummaries;
	}

	public List<MarketHistory> getMarketHistories() {
		return marketHistories;
	}

	public void setMarketHistories(List<MarketHistory> marketHistories) {
		this.marketHistories = marketHistories;
	}

	public List<Ticker> getTickers() {
		return tickers;
	}

	public void setTickers(List<Ticker> tickers) {
		this.tickers = tickers;
	}

	public List<Favorite> getUserFavorites() {
		return userFavorites;
	}

	public void setUserFavorites(List<Favorite> userFavorites) {
		this.userFavorites = userFavorites;
	}



}
