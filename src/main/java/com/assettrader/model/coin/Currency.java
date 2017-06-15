package com.assettrader.model.coin;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Currency {

	@Id
	private long id;
	private double currencyLong;
	private short minConfirmation;
	private double txFee;
	private boolean isActive;
	private String coinType;
	private double baseAddress;

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

}
