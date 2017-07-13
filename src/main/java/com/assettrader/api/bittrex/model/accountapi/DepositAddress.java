package com.assettrader.api.bittrex.model.accountapi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.assettrader.entities.ids.DepositAddressId;
import com.assettrader.model.Account;
import com.assettrader.model.utils.ExchangeName;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "DEPOSIT_ADDRESS")
@IdClass(DepositAddressId.class)
public class DepositAddress {

	@GeneratedValue( strategy = GenerationType.TABLE )
	@Column( name = "DEPOSIT_ADDRESS_ID", columnDefinition="serial")
	private Long id;
	
	@Id
	@Column(name = "CURRENCY")
    private String currency;
	
	@Id
	@Column(name = "ADDRESS")
    private String address;
	
	@JsonIgnore
	@Transient
	private ExchangeName exchangeName;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "CURRENCY", referencedColumnName = "CURRENCY", insertable = false, updatable = false),
		@JoinColumn(name = "EXCHANGE_NAME", referencedColumnName = "EXCHANGE_NAME", insertable = false, updatable = false),
		@JoinColumn(name = "EXCHANGE_SUFFIX", referencedColumnName = "EXCHANGE_SUFFIX", insertable = false, updatable = false) })
	private Account account;

	
	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ExchangeName getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(ExchangeName exchangeName) {
		this.exchangeName = exchangeName;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}