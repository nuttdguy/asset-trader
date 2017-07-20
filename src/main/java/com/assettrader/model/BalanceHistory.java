package com.assettrader.model;

import java.util.Date;

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
@Table(name = "BALANCE_HISTORY")
public class BalanceHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BALANCE_HISTORY_ID")
	private Long id;

	@Column(name = "BALANCE_AMOUNT")
	private Double balanceAmount;

	@Column(name = "BALANCE_DATE_TIME")
	private Date balanceDateTime;
	
	@Column(name = "BTC_PRICE")
	private Double btcPrice;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "CURRENCY", referencedColumnName = "CURRENCY", insertable = false, updatable = false),
		@JoinColumn(name = "WALLET_ORIGIN", referencedColumnName = "WALLET_ORIGIN", insertable = false, updatable = false),
		@JoinColumn(name = "WALLET_PREFIX", referencedColumnName = "WALLET_PREFIX", insertable = false, updatable = false) })
	private Account account;

	public Long getId() {
		return id;
	}

	public void setId(Long balanceHistoryId) {
		this.id = balanceHistoryId;
	}

	public Double getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(Double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public Date getBalanceDateTime() {
		return balanceDateTime;
	}

	public void setBalanceDateTime(Date balanceDateTime) {
		this.balanceDateTime = balanceDateTime;
	}

	public Double getBtcPrice() {
		return btcPrice;
	}

	public void setBtcPrice(Double btcPrice) {
		this.btcPrice = btcPrice;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}


}
