package com.assettrader.model.coin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "CURRENCY")
public class Currency {

	@Id
	@Column( name = "CURRENCY_ID")
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;
	
	@Column( name = "CURRENCY_LONG")
	private double currencyLong;
	
	@Column( name = "MIN_CONFIRMATION")
	private short minConfirmation;
	
	@Column( name = "TX_FEE")
	private double txFee;
	
	@Column( name = "IS_ACTIVE")
	private boolean isActive;
	
	@Column( name = "COIN_TYPE")
	private String coinType;
	
	@Column( name = "BASE_ADDRESS")
	private double baseAddress;

	@ManyToOne
	@JoinColumns({ 
		@JoinColumn(name = "COIN_ID"),
		@JoinColumn(name = "MARKET_NAME")})
	private Coin coin;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getCurrencyLong() {
		return currencyLong;
	}

	public void setCurrencyLong(double currencyLong) {
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

	public double getBaseAddress() {
		return baseAddress;
	}

	public void setBaseAddress(double baseAddress) {
		this.baseAddress = baseAddress;
	}

	public Coin getCoin() {
		return coin;
	}

	public void setCoin(Coin coin) {
		this.coin = coin;
	}

	
}
