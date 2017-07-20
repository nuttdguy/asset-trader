package com.assettrader.api.bittrex.model.publicapi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.assettrader.entities.ids.CoinId;


public class Currency {
	
	private Long id;

	private String currency;
	
	private String currencyLong;
	
	private Integer minConfirmation;
	
	private Double txFee;
	
	private Boolean isActive;
	
	private String coinType;
	
	private String baseAddress;
	
	private String notice;
	
	
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

	public Integer getMinConfirmation() {
		return minConfirmation;
	}

	public void setMinConfirmation(Integer minConfirmation) {
		this.minConfirmation = minConfirmation;
	}

	public Double getTxFee() {
		return txFee;
	}

	public void setTxFee(Double txFee) {
		this.txFee = txFee;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean active) {
		isActive = active;
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

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}
}
