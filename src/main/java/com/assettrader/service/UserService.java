package com.assettrader.service;

import org.springframework.stereotype.Service;

import com.assettrader.model.UserProfile;
import com.assettrader.model.rest.RWApiCredential;
import com.assettrader.model.rest.RWFavorite;
import com.assettrader.model.rest.RWLoginDetail;

@Service
public interface UserService {

	public UserProfile registerUser(UserProfile newUser);

	public boolean saveCoinAsFavorite(RWFavorite userFav);
	
	public boolean saveApiKey(RWApiCredential credential);
	
	//============================================
	//=== UPDATE
	//============================================


	public boolean updateProfile(RWLoginDetail userDetail);
	
	//============================================
	//=== DELETES
	//============================================
	
	

		
	//============================================
	//=== RETRIEVE
	//============================================
	
	
	public RWLoginDetail loginUser(String username, String password);


	
	//============================================
	//=== VALIDATE
	//============================================
	
	
	public boolean checkIfUserExists(String username);

}
