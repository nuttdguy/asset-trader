package com.assettrader.api.bittrex.model.accountapi;

import java.io.Serializable;
import java.time.LocalDateTime;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.assettrader.model.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table( name = "WITHDRAWAL_HISTORY_ENTRY" )
public class WithdrawalHistoryEntry implements Serializable {

	private static final long serialVersionUID = 4908513899210242329L;

	@JsonIgnore
	@GeneratedValue( strategy = GenerationType.TABLE)
	@Column( name = "WITHDRAW_HISTORY_ID", columnDefinition="serial")
	private Long id;
	
	@Id
	@Column( name = "PAYMENT_UUID")
	private String paymentUuid;
	
	@Column( name = "CURRENCY")
	private String currency;
	
	@Column( name = "AMOUNT")
	private String amount;
	
	@Column( name = "ADDRESS")
	private String address;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column( name = "OPENED")
	private Date opened;
	
	@Column( name = "AUTHORIZED")
	private Boolean authorized;
	
	@Column( name = "PENDING_PAYMENT" )
	private Boolean pendingPayment;
	
	@Column( name = "TX_COST" )
	private Double txCost;
	
	@Column( name = "TX_ID")
	private String txId;
	
	@Column( name = "CANCELED" )
	private Boolean canceled;
	
	@Column( name = "INVALID_ADDRESS")
	private Boolean invalidAddress;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "CURRENCY", referencedColumnName="CURRENCY", insertable=false, updatable=false),
		@JoinColumn(name = "EXCHANGE_NAME", referencedColumnName="EXCHANGE_NAME", insertable=false, updatable=false),
		@JoinColumn(name = "USER_PROFILE_ID", referencedColumnName="USER_PROFILE_ID", insertable=false, updatable=false)})
	private Account account;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPaymentUuid() {
		return paymentUuid;
	}

	public void setPaymentUuid(String paymentUuid) {
		this.paymentUuid = paymentUuid;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getOpened() {
		return opened;
	}

	public void setOpened(Date opened) {
		this.opened = opened;
	}

	public Boolean getAuthorized() {
		return authorized;
	}

	public void setAuthorized(Boolean authorized) {
		this.authorized = authorized;
	}

	public Boolean getPendingPayment() {
		return pendingPayment;
	}

	public void setPendingPayment(Boolean pendingPayment) {
		this.pendingPayment = pendingPayment;
	}

	public Double getTxCost() {
		return txCost;
	}

	public void setTxCost(Double txCost) {
		this.txCost = txCost;
	}

	public String getTxId() {
		return txId;
	}

	public void setTxId(String txId) {
		this.txId = txId;
	}

	public Boolean getCanceled() {
		return canceled;
	}

	public void setCanceled(Boolean canceled) {
		this.canceled = canceled;
	}

	public Boolean getInvalidAddress() {
		return invalidAddress;
	}

	public void setInvalidAddress(Boolean invalidAddress) {
		this.invalidAddress = invalidAddress;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
