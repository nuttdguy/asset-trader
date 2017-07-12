package com.assettrader.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assettrader.dao.UserDao;
import com.assettrader.model.EmailProvider;
import com.assettrader.model.SocialNetwork;
import com.assettrader.model.UserProfile;
import com.assettrader.model.rest.RWApiCredential;
import com.assettrader.model.rest.RWExternalWallet;
import com.assettrader.model.rest.RWFavorite;
import com.assettrader.model.rest.RWLoginDetail;
import com.assettrader.model.rest.RWPassword;
import com.assettrader.model.view.FavoriteCoinView;
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
	
	@Override
	public boolean addExternalWallet(RWExternalWallet walletDetail) {
		return userDao.addExternalWallet(walletDetail);
	}
	
	@Override
	public List<RWExternalWallet> getExternalWallets(Long userId) {
		return userDao.getExternalWallets(userId);
	}
	
	@Override
	public boolean addExternalEmail(EmailProvider emailProvider) {
		return userDao.addExternalEmail(emailProvider);
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
	
	@Override
	public boolean deleteFriend(Long friendId) {
		return userDao.deleteFriend(friendId);
	}
		
	@Override
	public boolean deleteCoinFavorite(Long userCoinFavId) {
		return userDao.deleteCoinFavorite(userCoinFavId);
	}
	
	@Override
	public boolean deleteExternalEmail(Long emailId) {
		return userDao.deleteExternalEmail(emailId);
	}
	
	@Override
	public boolean deleteExternalWallet(Long walletId) {
		return userDao.deleteExternalWallet(walletId);
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

	@Override
	public List<FavoriteCoinView> getFavoriteCoins(Long userId) {
		return userDao.getFavoriteCoins(userId);
	}
	
	@Override
	public List<EmailProvider> getExternalEmails(Long userId) {
		return userDao.getExternalEmails(userId);
	}
	
	//============================================
	//=== VALIDATE
	//============================================
	
	
	@Override
	public boolean checkIfUserExists(String username) {
		return userDao.checkIfUserExists(username);
	}


}
