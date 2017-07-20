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
import com.assettrader.model.utils.WalletOrigin;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "DEPOSIT_ADDRESS")
@IdClass(DepositAddressId.class)
public class DepositAddress {

	@GeneratedValue( strategy = GenerationType.TABLE )
	@Column( name = "DEPOSIT_ADDRESS_ID", columnDefinition="SERIAL")
	private Long id;
	
	@Id
	@Column(name = "CURRENCY")
    private String currency;
	
	@Id
	@Column(name = "WALLET_ADDRESS")
    private String address;
	
	@Transient
	private WalletOrigin walletOrigin;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "CURRENCY", referencedColumnName = "CURRENCY", insertable = false, updatable = false),
		@JoinColumn(name = "WALLET_ORIGIN", referencedColumnName = "WALLET_ORIGIN", insertable = false, updatable = false),
		@JoinColumn(name = "WALLET_PREFIX", referencedColumnName = "WALLET_PREFIX", insertable = false, updatable = false) })
	private Account account;

	
	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public WalletOrigin getWalletOrigin() {
		return walletOrigin;
	}

	public void setWalletOrigin(WalletOrigin walletOrigin) {
		this.walletOrigin = walletOrigin;
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