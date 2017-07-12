package com.assettrader.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "SOCIAL_NETWORK")
public class SocialNetwork extends Person {

	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "SOCIAL_NETWORK_ID", columnDefinition = "SERIAL")
	private Long id;

	@Id
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "MESSAGE")
	private String message;

	@Column(name = "IS_ACTIVE")
	private boolean active;

	@Column(name = "ENABLE_SEND_TO")
	private boolean enableSendTo;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "USER_PROFILE_ID", 
				referencedColumnName="USER_PROFILE_ID", insertable=false, updatable=false)
	private UserProfile userProfile;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isEnableSendTo() {
		return enableSendTo;
	}

	public void setEnableSendTo(boolean enableSendTo) {
		this.enableSendTo = enableSendTo;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	
}
