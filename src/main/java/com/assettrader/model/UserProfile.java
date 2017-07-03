package com.assettrader.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.assettrader.model.coinmarket.Coin;

@Entity
@Table(name = "USER_PROFILE")
public class UserProfile extends Person {

	@Id
	@Column(name = "USER_PROFILE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DELETE_DATE")
	private Date deletedDate;

	@Column(name = "IS_ACTIVE")
	private boolean isActive;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "userProfile")
	private Credential credentials;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userProfile")
	private List<Account> account = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userProfile")
	private List<Favorite> coinFavorites = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userProfile")
	private List<Address> address = new ArrayList<>();
	
	public UserProfile() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getDeletedDate() {
		return deletedDate;
	}

	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Credential getCredentials() {
		return credentials;
	}

	public void setCredentials(Credential credentials) {
		this.credentials = credentials;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public List<Favorite> getCoinFavorites() {
		return coinFavorites;
	}

	public void setCoinFavorites(List<Favorite> coinFavorites) {
		this.coinFavorites = coinFavorites;
	}

	public List<Account> getAccount() {
		return account;
	}

	public void setAccount(List<Account> account) {
		this.account = account;
	}

}

