package com.assettrader.model.coin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CURRENCY")
public class Currency {

	@Id
	@Column(name = "CURRENCY_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private long id;

	@Column(name = "CURRENCY")
	private String currency;

	@Column(name = "CURRENCY_LONG")
	private String currencyLong;

	@Column(name = "MIN_CONFIRMATION")
	private short minConfirmation;

	@Column(name = "TX_FEE")
	private double txFee;

	@Column(name = "IS_ACTIVE")
	private boolean isActive;

	@Column(name = "COIN_TYPE")
	private String coinType;

	@Column(name = "BASE_ADDRESS")
	private String baseAddress;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MARKET_NAME", insertable=true, updatable=false)
	private Coin coin;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCurrencyLong() {
		return currencyLong;
	}

	public void setCurrencyLong(String currencyLong) {
		this.currencyLong = currencyLong;
	}

	public short getMinConfirmation() {
		return minConfirmation;
	}

	public void setMinConfirmation(short minConfirmation) {
		this.minConfirmation = minConfirmation;
	}

	public double getTxFee() {
		return txFee;
	}

	public void setTxFee(double txFee) {
		this.txFee = txFee;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getCoinType() {
		return coinType;
	}

	public void setCoinType(String coinType) {
		this.coinType = coinType;
	}

	public String getBaseAddress() {
		return baseAddress;
	}

	public void setBaseAddress(String baseAddress) {
		this.baseAddress = baseAddress;
	}

	public Coin getCoin() {
		return coin;
	}

	public void setCoin(Coin coin) {
		this.coin = coin;
	}

}
