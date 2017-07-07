package com.assettrader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.assettrader.model.Credential;
import com.assettrader.model.rest.RWApiCredential;
import com.assettrader.model.rest.RWFavorite;
import com.assettrader.model.rest.RWLoginDetail;
import com.assettrader.model.rest.ResResponse;
import com.assettrader.service.UserService;

@CrossOrigin
@RestController
@RequestMapping( value = "/user")
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
	
	@RequestMapping(value = "/apikey", method = RequestMethod.POST )
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
	
	@RequestMapping(value = "/updateprofile", method = RequestMethod.POST)
	public ResResponse updateProfile(@RequestBody RWLoginDetail userDetail) {
		
		boolean isSuccess = userService.updateProfile(userDetail);
		ResResponse response = new ResResponse();
		
		if (isSuccess) {
			response.setMessage("USER UPDATED");
			response.setSuccess(true);
			return response;
		} else {
			response.setMessage("UPDATE FAILED");
			response.setSuccess(false);
			return response;
		}
	}
	
	
}

