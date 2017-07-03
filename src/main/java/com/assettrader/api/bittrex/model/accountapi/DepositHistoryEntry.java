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
import javax.persistence.Transient;

import com.assettrader.model.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "DEPOSIT_HISTORY_ENTRY" )
public class DepositHistoryEntry implements Serializable {

	private static final long serialVersionUID = -8357674342951584523L;
	
	@Transient
	private String logoUrl;
	
	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	@GeneratedValue( strategy=GenerationType.TABLE)
	@Column( name = "DEPOSIT_HISTORY_ID", columnDefinition="SERIAL")
    private Long id;
	
	@Column( name = "CONFIRMATIONS" )
    private Integer confirmations;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column( name = "LAST_UPDATED" )
    private Date lastUpdated;
	
	@Column( name = "CURRENCY" )
    private String currency;
	
	@Column( name = "AMOUNT" )
    private Double amount;
	
	@Column( name = "CRYPTO_ADDRESS" )
    private String cryptoAddress;
		
	@Id
	@Column( name = "TX_ID" )
    private String txId;
		
	@JsonIgnore
	@Transient
    private String paymentUuid;
	
	@JsonIgnore
	@Transient
    private LocalDateTime opened;
	
	@JsonIgnore
	@Transient
    private Boolean authorized;
	
	@JsonIgnore
	@Transient
    private Boolean pendingPayment;
	
	@JsonIgnore
	@Transient
	private Double txCost;
	
	@JsonIgnore
	@Transient
    private Boolean canceled;
	
	@JsonIgnore
	@Transient
    private Boolean invalidAddress;
	
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

    public Integer getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(Integer confirmations) {
        this.confirmations = confirmations;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Boolean getInvalidAddress() {
        return invalidAddress;
    }

    public void setInvalidAddress(Boolean invalidAddress) {
        this.invalidAddress = invalidAddress;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCryptoAddress() {
        return cryptoAddress;
    }

    public void setCryptoAddress(String cryptoAddress) {
        this.cryptoAddress = cryptoAddress;
    }

    public LocalDateTime getOpened() {
        return opened;
    }

    public void setOpened(LocalDateTime opened) {
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

    @JsonIgnore
    public Boolean getIsInvalidAddress() {
        return invalidAddress;
    }

    public void setIsInvalidAddress(Boolean invalidAddress) {
        this.invalidAddress = invalidAddress;
    }

    @JsonIgnore
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
    
    
    
}
