package com.assettrader.entities.ids;

import java.io.Serializable;

import com.assettrader.model.Account;
import com.assettrader.model.UserProfile;

public class UserAccountId implements Serializable {

	private static final long serialVersionUID = -6744935993536130076L;
	private Account account;
	private UserProfile userProfile;

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
