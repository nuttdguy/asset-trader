package com.assettrader.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String login() {
		
		System.out.println("The LOGIN method was called!!");
		return "hello";
	}
	
	@RequestMapping(value={"/", "/login"}, method=RequestMethod.POST)
	public String login(@ModelAttribute Model model) {
		
		return "";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout() {
		
		// TODO 
		return "";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String signUp() {
		
		// TODO
		return "";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signUp(@ModelAttribute Model model) {
		
		// TODO
		return "";
	}
	
}

















