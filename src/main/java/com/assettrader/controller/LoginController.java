package com.assettrader.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

		// TODO - CHECK IF USER EXISTS
		boolean isExist = userService.checkIfUserExists(user.getUsername());		
		
		if (!isExist) {
			
			UserProfile authenticatedUser = new UserProfile();
			authenticatedUser.setFirstName(user.getFirstName());
			authenticatedUser.setLastName(user.getLastName());
			
			Credential credential = new Credential();
			credential.setPassword(user.getPassword());
			credential.setUsername(user.getUsername());
			
			authenticatedUser.setCredentials(credential);
			
			authenticatedUser = userService.registerUser(authenticatedUser);
		}
		
		System.out.println(user);
		return null;
	}

}
