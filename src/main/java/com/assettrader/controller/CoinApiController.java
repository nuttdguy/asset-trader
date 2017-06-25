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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.assettrader.DTO.CoinResultDTO;
import com.assettrader.DTO.service.CoinDTOService;
import com.assettrader.model.coin.Coin;
import com.assettrader.model.coin.Currency;
import com.assettrader.model.coin.MarketSummary;
import com.assettrader.model.coin.OrderBook;
import com.assettrader.model.coin.Ticker;

@Controller
//@RestController
@RequestMapping("/coins")
public class CoinApiController {

	@Autowired
	public CoinDTOService coinDTOService;

	// FOR REST IMPLEMENTATION - TESTING
//	@RequestMapping(value = "/getmarkets", method = RequestMethod.GET)
//	public List<Coin> getCoin(Model model) {
//
//		List<Coin> coinList = coinDTOService.getMarkets();
//		
//		return coinList;
//	}
	
//	// FOR REST IMPLEMENTAION USING SPRING API
//	@RequestMapping(value = "/getmarkets",    method = RequestMethod.GET)
//	public String getCoin() {
//
//		final String url = "https://bittrex.com/api/v1.1/public/getmarkets";
//		RestTemplate restTemplate = new RestTemplate();
//
//		CoinResultDTO coinList = restTemplate.getForObject(url, CoinResultDTO.class);
//		
//		// List<Coin> coinList = coinResponse.getBody();
//		// model.addAttribute("coinList", coinList);
//		
//		return "coinlist";
//	}
	
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
			
	
	@RequestMapping(value = "/getmarketsummaries", method = RequestMethod.GET)
	private String getMarketSummaries(Model model) {
		
		List<MarketSummary> marketSummariesList = coinDTOService.getMarketSummaries();
		model.addAttribute("marketSummaries", marketSummariesList);
		
		return "marketsummariesview";
	}
	
	
	@RequestMapping(value = "/getmarketsummary", method = RequestMethod.GET)
	private String getMarketSummary(Model model ) {
		
		// TODO - Add param to get market-name for passing into API request
		String marketName = "BTC-LTC";
		MarketSummary marketSummary = coinDTOService.getMarketSummary(marketName);
		model.addAttribute("marketSummary", marketSummary);
		
		return "marketsummaryview";
	}
	
	
	@RequestMapping(value = "/getorderbook", method = RequestMethod.GET)
	private String getOrderBook(Model model) {
		
		// TODO - add param to get orderbook for a market-name, buy/sell/both
		String marketName = "BTC-LTC";
		String orderType = "both";
		
		List<OrderBook> orderBooks = coinDTOService.getOrderBook(marketName, orderType);
		model.addAttribute("orderbooks", orderBooks);
		
		return "orderbookview";
	}

	
	@RequestMapping(value = "/getticker", method= RequestMethod.GET)
	private String getTicker(Model model) {
		
		// TODO - Add param to get market-name
		String marketName = "BTC-LTC";
		Ticker ticker = coinDTOService.getTicker(marketName);
		model.addAttribute("ticker", ticker);
		
		return "ticker";
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
