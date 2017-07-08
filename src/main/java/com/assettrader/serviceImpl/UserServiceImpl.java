package com.assettrader.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assettrader.dao.UserDao;
import com.assettrader.model.SocialNetwork;
import com.assettrader.model.UserProfile;
import com.assettrader.model.rest.RWApiCredential;
import com.assettrader.model.rest.RWFavorite;
import com.assettrader.model.rest.RWLoginDetail;
import com.assettrader.model.rest.RWPassword;
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
	
	@Override
	public boolean addFriend(SocialNetwork friend) {
		return userDao.addFriend(friend);
	}
	
	//============================================
	//=== UPDATE
	//============================================

	@Override
	public boolean updateProfile(RWLoginDetail userDetail) {
		return userDao.updateProfile(userDetail);
	}

	@Override
	public boolean updatePassword(RWPassword password) {
		return userDao.updatePassword(password);
	}
	
	//============================================
	//=== DELETES
	//============================================
	
	
	public boolean deleteFriend(Long friendId) {
		return userDao.deleteFriend(friendId);
	}
		
	//============================================
	//=== RETRIEVE
	//============================================
	
	
	@Override
	public RWLoginDetail loginUser(String username, String password) {
		return userDao.loginUser(username, password);
	}
	
	@Override
	public List<SocialNetwork> getFriendList(Long userId) {
		return userDao.getFriendList(userId);
	}

	
	//============================================
	//=== VALIDATE
	//============================================
	
	
	@Override
	public boolean checkIfUserExists(String username) {
		return userDao.checkIfUserExists(username);
	}



}
