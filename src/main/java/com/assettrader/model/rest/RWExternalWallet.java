package com.assettrader.model.rest;

import com.assettrader.model.utils.ExchangeName;

public class RWExternalWallet {

	private Long id;
	private Long userId;
	private Long walletId;
	private String exchangeSuffix;
	private String coinName;
	private Double coinBalance;
	private String coinDepositAddress;
	private String coinWithdrawalAddress;
	private ExchangeName exchangeName;

	public Long getWalletId() {
		return walletId;
	}

	public void setWalletId(Long walletId) {
		this.walletId = walletId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getExchangeSuffix() {
		return exchangeSuffix;
	}

	public void setExchangeSuffix(String exchangeSuffix) {
		this.exchangeSuffix = exchangeSuffix;
	}

	public ExchangeName getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(ExchangeName exchangeName) {
		this.exchangeName = exchangeName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCoinName() {
		return coinName;
	}

	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}

	public Double getCoinBalance() {
		return coinBalance;
	}

	public void setCoinBalance(Double coinBalance) {
		this.coinBalance = coinBalance;
	}

	public String getCoinDepositAddress() {
		return coinDepositAddress;
	}

	public void setCoinDepositAddress(String coinDepositAddress) {
		this.coinDepositAddress = coinDepositAddress;
	}

	public String getCoinWithdrawalAddress() {
		return coinWithdrawalAddress;
	}

	public void setCoinWithdrawalAddress(String coinWithdrawalAddress) {
		this.coinWithdrawalAddress = coinWithdrawalAddress;
	}

}
