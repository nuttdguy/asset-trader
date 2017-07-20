package com.assettrader.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.assettrader.entities.ids.ApiKeyCredentialId;

@Entity
@Table(name = "API_CREDENTIAL")
@IdClass(ApiKeyCredentialId.class)
public class ApiCredential {

	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "API_CREDENTIAL_ID", columnDefinition="SERIAL")
	private Long id;

	@Id
	@Column(name = "API_KEY")
	private String apiKey;

	@Id
	@Column(name = "SECRET_KEY")
	private String secretKey;

	@Column(name = "WALLET_ORIGIN")
	private String walletOrigin;
	
	@Column(name = "SET_PRIMARY")
	private boolean setPrimary;

	@ManyToOne(cascade = CascadeType.ALL)
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

	public String getWalletOrigin() {
		return walletOrigin;
	}

	public void setWalletOrigin(String exchangeName) {
		this.walletOrigin = exchangeName;
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
