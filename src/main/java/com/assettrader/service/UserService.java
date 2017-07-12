package com.assettrader.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.assettrader.model.EmailProvider;
import com.assettrader.model.SocialNetwork;
import com.assettrader.model.UserProfile;
import com.assettrader.model.rest.RWApiCredential;
import com.assettrader.model.rest.RWExternalWallet;
import com.assettrader.model.rest.RWFavorite;
import com.assettrader.model.rest.RWLoginDetail;
import com.assettrader.model.rest.RWPassword;
import com.assettrader.model.rest.ResWrapper;
import com.assettrader.model.view.FavoriteCoinView;

@Service
public interface UserService {
	
	//============================================
	//=== CREATE
	//============================================

	public UserProfile registerUser(UserProfile newUser);
	public boolean saveCoinAsFavorite(RWFavorite userFav);
	public boolean saveApiKey(RWApiCredential credential);
	public boolean addFriend(SocialNetwork friend);
	public boolean addExternalWallet(RWExternalWallet walletDetail);
	public boolean addExternalEmail(EmailProvider emailProvider);
	
	//============================================
	//=== UPDATE
	//============================================

	public boolean updateProfile(RWLoginDetail userDetail);
	public boolean updatePassword(RWPassword password);
	
	//============================================
	//=== DELETES
	//============================================
	
	public boolean deleteFriend(Long friendId);
	public boolean deleteCoinFavorite(Long userCoinFavId);
	public boolean deleteExternalEmail(Long emailId);
	public boolean deleteExternalWallet(Long walletId);
		
	//============================================
	//=== RETRIEVE
	//============================================
	
	
	public RWLoginDetail loginUser(String username, String password);
	public List<SocialNetwork> getFriendList(Long userId);
	public List<FavoriteCoinView> getFavoriteCoins(Long userId);
	public List<RWExternalWallet> getExternalWallets(Long userId);
	public List<EmailProvider> getExternalEmails(Long userId);
	
	//============================================
	//=== VALIDATE
	//============================================
	
	
	public boolean checkIfUserExists(String username);

}
