package com.assettrader.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.assettrader.api.bittrex.model.common.ApiResult;
import com.assettrader.model.Credential;
import com.assettrader.model.UserProfile;
import com.assettrader.model.rest.RWLoginDetail;
import com.assettrader.model.rest.RWRegister;
import com.assettrader.model.rest.ResWrapper;
import com.assettrader.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

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
	
	@RequestMapping(value = { "/login" },
			consumes={MediaType.APPLICATION_JSON_VALUE},
			method = RequestMethod.POST)
	@ResponseBody
	public RWLoginDetail login(@RequestBody RWRegister user) {
		
		RWLoginDetail registeredUser = 
				userService.loginUser(user.getUsername(), user.getPassword());
				
		if ( registeredUser.getUserName() != null ) {
			registeredUser.setSuccess(true);
			registeredUser.setMessage("User authenticated");
			registeredUser.setToken( generateJwt() );
			return registeredUser;
		} 
		
		registeredUser.setSuccess(false);
		registeredUser.setMessage("Username and/or password is incorrect");
		return registeredUser;

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
			return userService.registerUser(registerNewUser(user, isExist));
		} 
		
		System.out.println("!!! USER IS ALREADY REGISTERED !!! ");
		return new UserProfile();
	}
	
	
	//============================================
	//=== HELPER
	//============================================
	
	private UserProfile registerNewUser(RWRegister user, boolean isExist) {
		
		UserProfile registerUser = new UserProfile();
		registerUser.setFirstName(user.getFirstName());
		registerUser.setLastName(user.getLastName());
		registerUser.setActive(isExist);
		registerUser.setUsername(user.getUsername());
		
		Credential credential = new Credential();
		credential.setPassword(user.getPassword());
		
		registerUser.setCredential(credential);
		return registerUser;
	}
	
	
	private String generateJwt() {
		
		try {
			Algorithm algorithm = Algorithm.HMAC256("secret");
			String token = JWT.create()
					.withIssuer("auth0")
					.sign(algorithm);
			
			return token;
		} catch (UnsupportedEncodingException exception){
		    //UTF-8 encoding not supported
		} catch (JWTCreationException exception){
		    //Invalid Signing configuration / Couldn't convert Claims.
		}
		return "Could not create token.";
	}

}
