package com.assettrader.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "success", "message", "result", "Notice" })
public class CurrencyDTO {

	private String Currency;

	private String CurrencyLong;

	private short MinConfirmation;

	private double TxFee;

	private boolean IsActive;

	private String CoinType;

	private String BaseAddress;

	public String getCurrency() {
		return Currency;
	}

	public void setCurrency(String currency) {
		Currency = currency;
	}

	public String getCurrencyLong() {
		return CurrencyLong;
	}

	public void setCurrencyLong(String currencyLong) {
		CurrencyLong = currencyLong;
	}

	public short getMinConfirmation() {
		return MinConfirmation;
	}

	public void setMinConfirmation(short minConfirmation) {
		MinConfirmation = minConfirmation;
	}

	public double getTxFee() {
		return TxFee;
	}

	public void setTxFee(double txFee) {
		TxFee = txFee;
	}

	public boolean isIsActive() {
		return IsActive;
	}

	public void setIsActive(boolean isActive) {
		IsActive = isActive;
	}

	public String getCoinType() {
		return CoinType;
	}

	public void setCoinType(String coinType) {
		CoinType = coinType;
	}

	public String getBaseAddress() {
		return BaseAddress;
	}

	public void setBaseAddress(String baseAddress) {
		BaseAddress = baseAddress;
	}
	
}
