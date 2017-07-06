package com.assettrader.model;

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
@Table(name = "API_CREDENTIAL")
public class ApiCredential {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "API_CREDENTIAL_ID")
	private Long id;

	@Column(name = "API_KEY")
	private String apiKey;

	@Column(name = "SECRET_KEY")
	private String secretKey;

	@Column(name = "EXCHANGE_NAME")
	private String exchangeName;
	
	@Column(name = "SET_PRIMARY")
	private boolean setPrimary;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_PROFILE_ID", referencedColumnName="USER_PROFILE_ID", insertable=true, updatable=false)
	private UserProfile userProfile;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	public boolean isSetPrimary() {
		return setPrimary;
	}

	public void setSetPrimary(boolean setPrimary) {
		this.setPrimary = setPrimary;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	
	
}
