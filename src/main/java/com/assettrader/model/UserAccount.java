/*package com.assettrader.model;

import java.util.Date;

import javax.persistence.CascadeType;
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

import com.assettrader.entities.ids.UserAccountId;


@Entity
@Table(name = "USER_ACCOUNT")
@IdClass(UserAccountId.class)
public class UserAccount {

	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "USER_ACCOUNT_ID", columnDefinition="SERIAL")
	private Long id;

	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumns({
		@JoinColumn(name = "CURRENCY", referencedColumnName="CURRENCY", insertable=false, updatable=false),
		@JoinColumn(name = "EXCHANGE_NAME", referencedColumnName="EXCHANGE_NAME", insertable=false, updatable=false) })
	private Account account;
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn( name = "USER_PROFILE_ID", referencedColumnName="USER_PROFILE_ID", insertable=false, updatable=false)
	private UserProfile userProfile;

	@Column( name = "ADD_DATE")
	private Date addDate;

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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

}
*/