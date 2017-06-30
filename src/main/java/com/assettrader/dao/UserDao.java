package com.assettrader.dao;

import java.util.List;

import com.assettrader.model.Address;
import com.assettrader.model.UserProfile;
import com.assettrader.model.coinmarket.Coin;


public interface UserDao {

	void registerUser(UserProfile newUser);
	void addCoinAsFavorite(Coin coin);
	
	void updateUsername(String newUsername);
	void updatePassword(String newPassword);
	void updateUserAddress(Address newAddress);
	void updateIsActive();
	
	void deleteUser(UserProfile userProfile);
	void deleteFavoriteCoin(int favoriteId);
	
	UserProfile loginUser(String username, String password);
	UserProfile getUser(int profileId);
	UserProfile getUser(String searchParam);
	Address getUserAddress(int profileId);
	
	List<Coin> getUserFavoriteCoins(int profileId);
	
	boolean checkIfUserExists(String username);
	
}
