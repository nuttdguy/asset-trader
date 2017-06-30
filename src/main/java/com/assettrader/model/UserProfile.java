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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.assettrader.model.coinmarket.Coin;

@Entity
@Table(name = "USER_PROFILE")
public class UserProfile extends Person {

	@Id
	@Column(name = "USER_PROFILE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DELETE_DATE")
	private Date deletedDate;

	@Column(name = "IS_ACTIVE")
	private boolean isActive;
	
	// TODO -- CHANGE MAPPING TO @MANYTOMANY USER_COIN_FAVORITE TABLE
	@OneToMany(cascade = CascadeType.ALL, mappedBy="userProfile")
	private List<Favorite> coinFavorites = new ArrayList<>();

	// non-owning-side, requires mappedby field name and cascade all
	@OneToMany(cascade = CascadeType.ALL, mappedBy="userProfile")
	private List<Address> address = new ArrayList<>();

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


}



//@JoinTable(name = "ARC_EMPLOYEE_OF_BAR"
//, joinColumns = {@JoinColumn(name = "BAR_ID")}
//, inverseJoinColumns = {@JoinColumn(name = "EMPLOYEE_ID")}
//, uniqueConstraints = {@UniqueConstraint(name = "ARC_UK_EMPLOYEE_OF_BAR", columnNames = {"EMPLOYEE_ID", "BAR_ID"})}
//, foreignKey = @ForeignKey(name = "ARC_FK_BAR_OF_EMPLOYEE")
//, inverseForeignKey = @ForeignKey(name = "ARC_FK_EMPLOYEE_OF_BAR"))







