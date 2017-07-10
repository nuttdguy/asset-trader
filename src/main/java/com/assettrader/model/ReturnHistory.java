package com.assettrader.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RETURN_HISTORY")
public class ReturnHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RETURN_HISTORY_ID")
	private Long id;

	@Column(name = "TOTAL_DEPOSIT")
	private Double totalDeposits;

	@Column(name = "TOTAL_WITHDRAWAL")
	private Double totalWithdrawals;

	@Column(name = "TOTAL_BALANCE")
	private Double totalBalance;

	@Column(name = "ENTRY_DATE")
	private Date entryDate;
	
	@Column(name = "BTC_PRICE")
	private Double btcPrice;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn( name = "USER_PROFILE_ID" )
	private UserProfile userProfile;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getBtcPrice() {
		return btcPrice;
	}

	public void setBtcPrice(Double btcPrice) {
		this.btcPrice = btcPrice;
	}

	public Double getTotalDeposits() {
		return totalDeposits;
	}

	public void setTotalDeposits(Double totalDeposits) {
		this.totalDeposits = totalDeposits;
	}

	public Double getTotalWithdrawals() {
		return totalWithdrawals;
	}

	public void setTotalWithdrawals(Double totalWithdrawals) {
		this.totalWithdrawals = totalWithdrawals;
	}

	public Double getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(Double totalBalance) {
		this.totalBalance = totalBalance;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

}
