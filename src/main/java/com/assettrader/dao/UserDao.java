package com.assettrader.dao;

import java.util.List;

import com.assettrader.model.SocialNetwork;
import com.assettrader.model.UserProfile;
import com.assettrader.model.rest.RWApiCredential;
import com.assettrader.model.rest.RWFavorite;
import com.assettrader.model.rest.RWLoginDetail;
import com.assettrader.model.rest.RWPassword;
import com.assettrader.model.view.FavoriteCoinView;


public interface UserDao {

	
	//============================================
	//== CREATE
	//============================================
	
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
	//=== DELETE
	//============================================
	
	public boolean deleteFriend(Long friendId);
	public boolean deleteCoinFavorite(Long userCoinFavId);
	
	//============================================
	//=== RETRIEVE
	//============================================
	
	public RWLoginDetail loginUser(String username, String password);
	public List<SocialNetwork> getFriendList(Long userId);
	public List<FavoriteCoinView> getFavoriteCoins(Long userId);
	
	//============================================
	//=== VALIDATE
	//============================================
	
	public boolean checkIfUserExists(String username);

	
}
