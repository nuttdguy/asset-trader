package com.assettrader.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CREDENTIAL")
public class Credential {
	
	@Id
	@Column( name = "CREDENTIAL_ID")
	private Long id;
	
	@Column( name = "USERNAME")
	private String username;
	
	@Column( name = "PASSWORD")
	private String password;
	
	// Is the non-owning-side, which contains the foreign-key 
	@OneToOne( cascade = CascadeType.ALL)
	@JoinColumn( name = "USER_PROFILE_ID")
	private UserProfile userProfile;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
	
}
