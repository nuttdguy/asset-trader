package com.assettrader.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.assettrader.model.coinmarket.Coin;

@Entity
@Table(name = "FAVORITE")
public class Favorite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FAVORITE_ID")
	private long id;

	@Column(name = "DESCRIPTION")
	private String description;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "USER_COIN_FAVORITE", 
			joinColumns = @JoinColumn(name = "USER_PROFILE_ID", insertable = true, updatable = true),
			inverseJoinColumns = {
				@JoinColumn(name = "MARKET_NAME", referencedColumnName="MARKET_NAME", insertable=true, updatable=false),
				@JoinColumn(name = "EXCHANGE", referencedColumnName="EXCHANGE", insertable=true, updatable=false) })
	private List<Coin> coinFavorites;
	
	@ManyToOne
	@JoinColumn( name = "USER_PROFILE_ID")
	private UserProfile userProfile;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Coin> getCoinFavorites() {
		return coinFavorites;
	}

	public void setCoinFavorites(List<Coin> coinFavorites) {
		this.coinFavorites = coinFavorites;
	}

}
