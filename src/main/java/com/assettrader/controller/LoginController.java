package com.assettrader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.assettrader.model.Credential;
import com.assettrader.model.UserProfile;
import com.assettrader.model.rest.RWRegister;
import com.assettrader.service.UserService;

import feign.Headers;

@CrossOrigin
@RestController
@RequestMapping(value = "/user")
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login() {

		System.out.println("The LOGIN method was called!!");
		return "hello";
	}

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.POST)
	public String login(@ModelAttribute Model model) {

		return "";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {

		// TODO
		return "";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {

		// TODO
		return "gET WORKS";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public UserProfile registerUser(@RequestBody RWRegister user) {

		boolean isExist = userService.checkIfUserExists(user.getUsername());		
		
		if (!isExist) {			
			return userService.registerUser(registerNewUser(user));
		} 
		
		System.out.println("!!! USER IS ALREADY REGISTERED !!! ");
		return new UserProfile();
	}
	
	
	//============================================
	//=== HELPER
	//============================================
	
	private UserProfile registerNewUser(RWRegister user) {
		
		UserProfile authenticatedUser = new UserProfile();
		authenticatedUser.setFirstName(user.getFirstName());
		authenticatedUser.setLastName(user.getLastName());
		
		Credential credential = new Credential();
		credential.setPassword(user.getPassword());
		credential.setUsername(user.getUsername());
		
		authenticatedUser.setCredentials(credential);
		return authenticatedUser;
	}

}
