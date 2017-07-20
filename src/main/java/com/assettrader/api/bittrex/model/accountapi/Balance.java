package com.assettrader.api.bittrex.model.accountapi;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.assettrader.entities.ids.BalanceId;
import com.assettrader.model.Account;
import com.assettrader.model.utils.WalletOrigin;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "BALANCE")
@IdClass(BalanceId.class)
public class Balance implements Serializable {

	private static final long serialVersionUID = -2617907704243030480L;

	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "BALANCE_ID", columnDefinition = "SERIAL")
	@JsonIgnore
	private Double id;

	@Id
	@Column(name = "CURRENCY_NAME")
	private String currency;

	@Column(name = "ACCOUNT_BALANCE")
	private Double balance;

	@Column(name = "AVAILABLE")
	private Double available;

	@Column(name = "PENDING")
	private Double pending;

	@Id
	@Column(name = "CRYPTO_ADDRESS")
	private String cryptoAddress;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BALANCE_DATE")
	private Date balanceDate;

	@JsonIgnore
	@Transient
	private Boolean requested;

	@JsonIgnore
	@Transient
	private String uuid;

	@Transient
	@Enumerated(EnumType.STRING)
	private WalletOrigin walletOrigin;

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "CURRENCY", referencedColumnName = "CURRENCY", insertable = false, updatable = false),
		@JoinColumn(name = "WALLET_ORIGIN", referencedColumnName = "WALLET_ORIGIN", insertable = false, updatable = false),
		@JoinColumn(name = "WALLET_PREFIX", referencedColumnName = "WALLET_PREFIX", insertable = false, updatable = false) })
	private Account account;

	@Transient
	private String logoUrl;

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public WalletOrigin getWalletOrigin() {
		return walletOrigin;
	}

	public void setWalletOrigin(WalletOrigin walletOrigin) {
		this.walletOrigin = walletOrigin;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getAvailable() {
		return available;
	}

	public void setAvailable(Double available) {
		this.available = available;
	}

	public Double getPending() {
		return pending;
	}

	public void setPending(Double pending) {
		this.pending = pending;
	}

	public String getCryptoAddress() {
		return cryptoAddress;
	}

	public void setCryptoAddress(String cryptoAddress) {
		this.cryptoAddress = cryptoAddress;
	}

	public Boolean getRequested() {
		return requested;
	}

	public void setRequested(Boolean requested) {
		this.requested = requested;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Double getId() {
		return id;
	}

	public void setId(Double id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Date getBalanceDate() {
		return balanceDate;
	}

	public void setBalanceDate(Date balanceDate) {
		this.balanceDate = balanceDate;
	}

}