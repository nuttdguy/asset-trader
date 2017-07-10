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
@Table(name = "BALANCE_HISTORY")
public class BalanceHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BALANCE_HISTORY_ID")
	private Long balanceHistoryId;

	@Column(name = "BALANCE_AMOUNT")
	private Double balanceAmount;

	@Column(name = "BALANCE_DATE_TIME")
	private Date balanceDateTime;
	
	@Column(name = "BTC_PRICE")
	private Double btcPrice;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn( name = "USER_PROFILE_ID" )
	private UserProfile userProfile;

	public Long getBalanceHistoryId() {
		return balanceHistoryId;
	}

	public void setBalanceHistoryId(Long balanceHistoryId) {
		this.balanceHistoryId = balanceHistoryId;
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

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

}
