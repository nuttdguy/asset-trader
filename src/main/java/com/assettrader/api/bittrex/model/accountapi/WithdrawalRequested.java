package com.assettrader.api.bittrex.model.accountapi;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.assettrader.model.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table( name = "WITHDRAWAL_REQUESTED")
public class WithdrawalRequested implements Serializable {

	private static final long serialVersionUID = -1607642597902365036L;

	@Id
	@Column( name = "UUID")
    private String uuid;

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "CURRENCY", referencedColumnName = "CURRENCY", insertable = false, updatable = false),
		@JoinColumn(name = "WALLET_ORIGIN", referencedColumnName = "WALLET_ORIGIN", insertable = false, updatable = false),
		@JoinColumn(name = "WALLET_PREFIX", referencedColumnName = "WALLET_PREFIX", insertable = false, updatable = false) })
	private Account account;
	
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
    
}
