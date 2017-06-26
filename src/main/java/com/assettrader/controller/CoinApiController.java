package com.assettrader.controller;

import java.util.List;

import javax.xml.ws.soap.AddressingFeature.Responses;

import org.jboss.logging.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.assettrader.DTO.CoinResultDTO;
import com.assettrader.DTO.service.CoinDTOService;
import com.assettrader.model.coin.Coin;
import com.assettrader.model.coin.Currency;
import com.assettrader.model.coin.MarketHistory;
import com.assettrader.model.coin.MarketSummary;
import com.assettrader.model.coin.OrderBook;
import com.assettrader.model.coin.Ticker;

//@Controller
@RestController
@RequestMapping("/coins")
public class CoinApiController {

	@Autowired
	public CoinDTOService coinDTOService;

	
	@RequestMapping(value = "/getmarkets", method = RequestMethod.GET)
	public List<Coin> getCoin(Model model) {

		List<Coin> coinList = coinDTOService.getMarkets();
		
		model.addAttribute("coinList", coinList);
		return coinList;
	}
	
	@RequestMapping(value = "/getcurrencies", method = RequestMethod.GET)
	private List<Currency> getCurrencies(Model model) {
		
		List<Currency> currencyList = coinDTOService.getCurrencies();
		model.addAttribute("currencyList", currencyList);
		
		return currencyList;
	}
			
	
	@RequestMapping(value = "/getmarketsummaries", method = RequestMethod.GET)
	private List<MarketSummary> getMarketSummaries(Model model) {
		
		List<MarketSummary> marketSummariesList = coinDTOService.getMarketSummaries();
		model.addAttribute("marketSummaries", marketSummariesList);
		
		return marketSummariesList;
	}
	
	
	@RequestMapping(value = "/getmarketsummary/{marketname}", method = RequestMethod.GET)
	private MarketSummary getMarketSummary(Model model, @PathVariable String marketname) {
		
		MarketSummary marketSummary = coinDTOService.getMarketSummary(marketname);
		model.addAttribute("marketSummary", marketSummary);
		
		return marketSummary;
	}
	
	
	@RequestMapping(value = "/getorderbook/{marketname}&{ordertype}", method = RequestMethod.GET)
	private List<OrderBook> getOrderBook(Model model, @PathVariable String marketname, @PathVariable String ordertype) {
				
		List<OrderBook> orderBooks = coinDTOService.getOrderBook(marketname, ordertype);
		model.addAttribute("orderbooks", orderBooks);
		
		return orderBooks;
	}

	
	@RequestMapping(value = "/getticker/{marketname}", method= RequestMethod.GET)
	private Ticker getTicker(Model model, @PathVariable String marketname) {
		
		Ticker ticker = coinDTOService.getTicker(marketname);
		model.addAttribute("ticker", ticker);
		
		return ticker;
	}
	
	@RequestMapping(value = "/getmarkethistory/{marketname}", method= RequestMethod.GET)
	private List<MarketHistory> getMarketHistory(Model model, @PathVariable String marketname) {
		
		// TODO - Add param to get market-name
		// String marketName = "BTC-XEM";
		List<MarketHistory> marketHistory = coinDTOService.getMarketHistory(marketname);
		model.addAttribute("marketHistory", marketHistory);
		
		return marketHistory;
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
