package com.assettrader.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.assettrader.DTO.service.CoinDTOService;
import com.assettrader.model.coin.Coin;
import com.assettrader.model.coin.Currency;

@Controller
@RequestMapping("/coins")
public class CoinApiController {

	@Autowired
	public CoinDTOService coinDTOService;

	@RequestMapping(value = "/getmarkets", method = RequestMethod.GET)
	public String getCoin(Model model) {

		List<Coin> coinList = coinDTOService.getMarkets();
		
		model.addAttribute("coinList", coinList);
		return "coinlist";
	}
	
	@RequestMapping(value = "/getcurrencies", method = RequestMethod.GET)
	private String getCurrencies(Model model) {
		
		List<Currency> currencyList = coinDTOService.getCurrencies();
		model.addAttribute("currencyList", currencyList);
		
		return "currencyview";
	}
			

	// What should be displayed to user in their Home Profile
	// = I want to be able to see my username so that I know its my account
	// = I want to view my account balance for (a single account)
	// = I want to view my account balances for (all accounts)
	// = I want to view my favorites coins (limit 5, current market price, last
	// bid price, last ask price, date of last record, order of importance)
	// = I want to update the prices of my favorite coins (all)
	// = I want to update the price of my favorite coin (single)
	// = I want to add an importance priority on my favorite coins
	// = I want to add a coin as my favorite and view an updated list of my
	// favorites
	// = I want to view the current price of a primary pair (in USD)
	// =

	// List activities that will take place within this controller
	// 1. Get the getAccountBalances()
	// 2. Get the getAccountBalances(LocalDate dateStart, LocalDate dateEnd); //
	// use default values
	// 3. Get

}
