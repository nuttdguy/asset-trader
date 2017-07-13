package com.assettrader.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.assettrader.model.EmailProvider;
import com.assettrader.model.SocialNetwork;
import com.assettrader.model.rest.RWApiCredential;
import com.assettrader.model.rest.RWExternalWallet;
import com.assettrader.model.rest.RWFavorite;
import com.assettrader.model.rest.RWLoginDetail;
import com.assettrader.model.rest.RWPassword;
import com.assettrader.model.rest.ResResponse;
import com.assettrader.model.rest.ResWrapper;
import com.assettrader.model.utils.ExchangeName;
import com.assettrader.model.view.FavoriteCoinView;
import com.assettrader.service.UserService;

@CrossOrigin
@RestController
@RequestMapping(value = "/user")
public class SettingsController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/favorite", method = RequestMethod.POST)
	@ResponseBody
	public ResResponse saveCoinAsFavorite(@RequestBody RWFavorite userFav) {

		boolean isSuccess = userService.saveCoinAsFavorite(userFav);
		ResResponse response = new ResResponse();

		if (isSuccess) {
			response.setMessage("Coin has been successfully saved");
			response.setSuccess(true);
		} else {
			response.setMessage("Coin was not saved");
			response.setSuccess(false);
		}
		return response;

	}

	@RequestMapping(value = "/apikey", method = RequestMethod.POST)
	@ResponseBody
	public ResResponse addApiExchange(@RequestBody RWApiCredential credential) {

		boolean isSuccess = userService.saveApiKey(credential);
		ResResponse response = new ResResponse();

		if (!isSuccess) {
			response.setMessage("Coin has been successfully saved");
			response.setSuccess(true);
		} else {
			response.setMessage("Coin was not saved");
			response.setSuccess(false);
		}
		return response;

	}

	@RequestMapping(value = "/profile/update", method = RequestMethod.POST)
	public ResResponse updateProfile(@RequestBody RWLoginDetail userDetail) {

		ResResponse response = new ResResponse();
		boolean isSuccess = false;

		if (!userDetail.getUserName().equals("") && !userDetail.getFirstName().equals("")
				&& !userDetail.getLastName().equals("")) {

			isSuccess = userService.updateProfile(userDetail);
		}

		if (isSuccess) {
			response.setMessage("USER UPDATED");
			response.setSuccess(true);
			return response;
		}

		response.setMessage("UPDATE FAILED");
		response.setSuccess(false);
		return response;
	}

	@RequestMapping(value = "/friend/add", method = RequestMethod.POST)
	public ResResponse addFriend(@RequestBody SocialNetwork friend) {

		ResResponse response = new ResResponse();

		if (!friend.getEmail().equals("") && !friend.getFirstName().equals("") && !friend.getLastName().equals("")) {
			boolean isSuccess = userService.addFriend(friend);

			if (!isSuccess) {
				response.setMessage("Friend added ...");
				response.setSuccess(true);
				return response;
			}
		}

		response.setMessage("Error adding friend ...");
		response.setSuccess(false);
		return response;
	}

	@RequestMapping(value = "/friend/list/{userId}", method = RequestMethod.GET)
	public ResWrapper<List<SocialNetwork>> getFriendList(@PathVariable Long userId) {

		List<SocialNetwork> socialNetwork = userService.getFriendList(userId);

		ResWrapper<List<SocialNetwork>> response = new ResWrapper<>();
		response.setMessage("GETTING FRIEND LIST IS SUCCESSFUL");
		response.setSuccess(true);
		response.setResult(socialNetwork);

		return response;
	}

	@RequestMapping(value = "/friend/delete/{friendId}", method = RequestMethod.GET)
	public ResResponse deleteFriend(@PathVariable Long friendId) {

		boolean isSuccess = userService.deleteFriend(friendId);
		ResResponse response = new ResResponse();
		if (!isSuccess) {
			response.setMessage("FRIEND WAS DELETED");
			response.setSuccess(true);
			return response;
		}

		response.setMessage("FRIEND WAS NOT DELETED");
		response.setSuccess(false);
		return response;

	}

	@RequestMapping(value = "/password/update", method = RequestMethod.POST)
	public ResResponse updatePassword(@RequestBody RWPassword password) {

		boolean isSuccess = userService.updatePassword(password);
		ResResponse response = new ResResponse();
		if (!isSuccess) {
			response.setMessage("Password updated");
			response.setSuccess(true);
			return response;
		}

		response.setMessage("Was not able to update password");
		response.setSuccess(false);
		return response;

	}

	@RequestMapping(value = "/favorite/coin/list/{userId}", method = RequestMethod.GET)
	public ResWrapper<List<FavoriteCoinView>> getFavoriteCoins(@PathVariable Long userId) {

		List<FavoriteCoinView> coinFavList = userService.getFavoriteCoins(userId);

		ResWrapper<List<FavoriteCoinView>> response = new ResWrapper<>();
		if (coinFavList != null) {
			response.setMessage("YOUR FAVORITES");
			response.setResult(coinFavList);
			response.setSuccess(true);
			response.setStatus(HttpStatus.OK);
			return response;
		}

		response.setMessage("ERROR GETTING COIN LIST");
		response.setSuccess(false);
		return response;
	}

	@RequestMapping(value = "/favorite/delete/{coinId}", method = RequestMethod.DELETE)
	public ResResponse deleteCoinFavorite(@PathVariable Long coinId) {

		boolean isSuccess = userService.deleteCoinFavorite(coinId);
		ResResponse response = new ResResponse();
		if (!isSuccess) {
			response.setMessage("COIN REMOVED");
			response.setSuccess(true);
			return response;
		}

		response.setMessage("COIN COULD NOT BE REMOVED");
		response.setSuccess(false);
		return response;

	}

	
	@RequestMapping(value = "/wallet/{userId}", method = RequestMethod.GET)
	public ResWrapper<List<RWExternalWallet>> getExternalCoinWallets(@PathVariable Long userId) {

		List<RWExternalWallet> walletList = userService.getExternalWallets(userId);

		ResWrapper<List<RWExternalWallet>> response = new ResWrapper<>();
		if (walletList != null) {
			response.setResult(walletList);
			response.setStatus(HttpStatus.OK);
			response.setMessage("Success");
			return response;
			
		}

		response.setMessage("Wallet list cannot be retrieved");
		response.setSuccess(false);
		return response;

	}

	
	@RequestMapping(value = "/wallet/add", method = RequestMethod.POST)
	public ResResponse addExternalCoinWallet(@RequestBody RWExternalWallet walletDetail) {

		walletDetail.setExchangeName(ExchangeName.WALLET);
		boolean isSuccess = userService.addExternalWallet(walletDetail);

		ResResponse response = new ResResponse();
		if (!isSuccess) {
			response.setMessage("External Wallet added");
			response.setSuccess(true);
			return response;
		}

		response.setMessage("External wallet was not added");
		response.setSuccess(false);
		return response;

	}
	
	@RequestMapping(value = "/wallet/delete/{walletId}", method = RequestMethod.DELETE)
	public ResResponse deleteExternalCoinWallet(@PathVariable Long walletId) {

		boolean isSuccess = userService.deleteExternalWallet(walletId);

		ResResponse response = new ResResponse();
		if (!isSuccess) {
			response.setMessage("Wallet deleted ... ");
			response.setSuccess(true);
			return response;
		}

		response.setMessage("Wallet was not deleted ... ");
		response.setSuccess(false);
		return response;

	}
	

	@RequestMapping(value = "/email/external", method = RequestMethod.POST)
	public ResResponse addExternalEmail(@RequestBody EmailProvider emailProvider) {

		boolean isSuccess = true;
		if (!emailProvider.getEmailFrom().isEmpty() 
				|| !emailProvider.getEmailFromPassword().isEmpty() ) { 
			
			isSuccess = userService.addExternalEmail(emailProvider);
		}
		
		ResResponse response = new ResResponse();
		if (!isSuccess) {
			response.setMessage("Email added");
			response.setSuccess(true);
			return response;
		}

		response.setMessage("Email was not added");
		response.setSuccess(false);
		return response;
	}
	
	@RequestMapping(value = "/email/external/list/{userId}", method = RequestMethod.GET)
	public ResWrapper<List<EmailProvider>> getExternalEmails(@PathVariable Long userId) {
		
		List<EmailProvider> externalEmails = userService.getExternalEmails(userId);
		
		ResWrapper<List<EmailProvider>> response = new ResWrapper<>();
		if (externalEmails != null) {
			response.setResult(externalEmails);
			response.setMessage("Retrieved emails");
			response.setSuccess(true);
			return response;
		}
		
		response.setResult(externalEmails);
		response.setMessage("Error in getting emails");
		response.setSuccess(false);
		return response;
	}
	
	@RequestMapping(value = "/email/external/delete/{emailId}", method = RequestMethod.DELETE)
	public ResResponse deleteExternalEmail(@PathVariable Long emailId) {
		
		boolean isSuccess = userService.deleteExternalEmail(emailId);
		
		ResResponse response = new ResResponse();
		if (!isSuccess) {
			response.setMessage("Email deleted");
			response.setSuccess(true);
			return response;
		}

		response.setMessage("Email not deleted");
		response.setSuccess(false);
		return response;
		
	}
	

}









