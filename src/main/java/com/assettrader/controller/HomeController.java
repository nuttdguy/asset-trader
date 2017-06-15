package com.assettrader.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public static String login(@ModelAttribute Model model) {
		// TODO implement login service
		return "hello";
	}
	
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public static String logout() {
		
		// TODO 
		return "";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public static String signUp() {
		
		// TODO
		return "";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public static String signUp(@ModelAttribute Model model) {
		
		// TODO
		return "";
	}
	
}

















