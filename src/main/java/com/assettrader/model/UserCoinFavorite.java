package com.assettrader.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.assettrader.model.coinmarket.Coin;

@Entity
@Table(name = "USER_COIN_FAVORITE")
public class UserCoinFavorite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_COIN_FAVORITE_ID")
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumns({
		@JoinColumn(name = "MARKET_NAME", referencedColumnName="MARKET_NAME", insertable=true, updatable=true),
		@JoinColumn(name = "EXCHANGE_NAME", referencedColumnName="EXCHANGE_NAME", insertable=true, updatable=true) })
	private Coin coin;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn( name = "USER_PROFILE_ID", referencedColumnName="USER_PROFILE_ID", insertable=true, updatable=true)
	private UserProfile userProfile;

	@Column( name = "ADD_DATE" )
	private Date addDate;
	
	@Column( name = "ACTIVE" )
	private boolean active;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public Coin getCoin() {
		return coin;
	}

	public void setCoin(Coin coin) {
		this.coin = coin;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	

}
