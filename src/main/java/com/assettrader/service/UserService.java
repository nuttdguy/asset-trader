package com.assettrader.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.assettrader.model.SocialNetwork;
import com.assettrader.model.UserProfile;
import com.assettrader.model.rest.RWApiCredential;
import com.assettrader.model.rest.RWFavorite;
import com.assettrader.model.rest.RWLoginDetail;
import com.assettrader.model.rest.RWPassword;

@Service
public interface UserService {

	public UserProfile registerUser(UserProfile newUser);

	public boolean saveCoinAsFavorite(RWFavorite userFav);
	
	public boolean saveApiKey(RWApiCredential credential);
	
	public boolean addFriend(SocialNetwork friend);
	
	//============================================
	//=== UPDATE
	//============================================


	public boolean updateProfile(RWLoginDetail userDetail);
	public boolean updatePassword(RWPassword password);
	
	//============================================
	//=== DELETES
	//============================================
	
	
	public boolean deleteFriend(Long friendId);
		
	//============================================
	//=== RETRIEVE
	//============================================
	
	
	public RWLoginDetail loginUser(String username, String password);
	public List<SocialNetwork> getFriendList(Long userId);

	
	//============================================
	//=== VALIDATE
	//============================================
	
	
	public boolean checkIfUserExists(String username);

}
