package com.assettrader.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assettrader.dao.UserDao;
import com.assettrader.model.Address;
import com.assettrader.model.UserProfile;
import com.assettrader.model.coinmarket.Coin;
import com.assettrader.model.rest.RWLoginDetail;
import com.assettrader.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	
	//============================================
	//== CREATE
	//============================================
	
	@Override
	public UserProfile registerUser(UserProfile newUser) {
		return userDao.registerUser(newUser);
	}

	@Override
	public void addCoinAsFavorite(Coin coin) {
		// TODO Auto-generated method stub
		
	}
	
	
	//============================================
	//=== UPDATE
	//============================================

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

	
	//============================================
	//=== DELETES
	//============================================
	
	
	@Override
	public void deleteUser(UserProfile userProfile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFavoriteCoin(int favoriteId) {
		// TODO Auto-generated method stub
		
	}

		
	//============================================
	//=== RETRIEVE
	//============================================
	
	
	@Override
	public RWLoginDetail loginUser(String username, String password) {
		return userDao.loginUser(username, password);
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

	
	//============================================
	//=== VALIDATE
	//============================================
	
	
	@Override
	public boolean checkIfUserExists(String username) {
		return userDao.checkIfUserExists(username);
	}

}
