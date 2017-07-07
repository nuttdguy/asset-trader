package com.assettrader.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assettrader.dao.UserDao;
import com.assettrader.model.UserProfile;
import com.assettrader.model.rest.RWApiCredential;
import com.assettrader.model.rest.RWFavorite;
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
	public boolean saveCoinAsFavorite(RWFavorite userFav) {
		return userDao.saveCoinAsFavorite(userFav);
	}
	
	@Override
	public boolean saveApiKey(RWApiCredential credential) {
		return userDao.saveApiKey(credential);
	}
	
	//============================================
	//=== UPDATE
	//============================================

	@Override
	public boolean updateProfile(RWLoginDetail userDetail) {
		return userDao.updateProfile(userDetail);
	}

	
	//============================================
	//=== DELETES
	//============================================
	
	

		
	//============================================
	//=== RETRIEVE
	//============================================
	
	
	@Override
	public RWLoginDetail loginUser(String username, String password) {
		return userDao.loginUser(username, password);
	}
	


	
	//============================================
	//=== VALIDATE
	//============================================
	
	
	@Override
	public boolean checkIfUserExists(String username) {
		return userDao.checkIfUserExists(username);
	}



}
