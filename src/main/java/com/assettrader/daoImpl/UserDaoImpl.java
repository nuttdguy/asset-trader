package com.assettrader.daoImpl;

import java.util.List;

import com.assettrader.dao.UserDao;
import com.assettrader.model.Address;
import com.assettrader.model.UserProfile;
import com.assettrader.model.coinmarket.Coin;

public class UserDaoImpl implements UserDao {

	@Override
	public void registerUser(UserProfile newUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCoinAsFavorite(Coin coin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUsername(String newUsername) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePassword(String newPassword) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUserAddress(Address newAddress) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateIsActive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(UserProfile userProfile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFavoriteCoin(int favoriteId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserProfile loginUser(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProfile getUser(int profileId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProfile getUser(String searchParam) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Address getUserAddress(int profileId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Coin> getUserFavoriteCoins(int profileId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkIfUserExists(String username) {
		// TODO Auto-generated method stub
		return false;
	}

}
