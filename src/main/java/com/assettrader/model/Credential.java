package com.assettrader.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CREDENTIAL")
public class Credential implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CREDENTIAL_ID")
	private Long id;

	@Column(name = "TOKEN")
	private String token;

	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "ACTIVITY_DATE")
	private Date activityDate;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_PROFILE_ID", 
		referencedColumnName="USER_PROFILE_ID", insertable=true, updatable=false)
	private UserProfile userProfile;

	public Credential() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}
	
}
