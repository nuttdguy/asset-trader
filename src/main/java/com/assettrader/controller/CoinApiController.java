package com.assettrader.controller;

import static com.assettrader.utils.ValidationHelperUtils.ERROR_INVALID_EXCHANGE;
import static com.assettrader.utils.ValidationHelperUtils.validateExchangeName;
import static com.assettrader.utils.ValidationHelperUtils.validateOrderType;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assettrader.model.coinmarket.Coin;
import com.assettrader.model.coinmarket.Currency;
import com.assettrader.model.coinmarket.MarketHistory;
import com.assettrader.model.coinmarket.MarketSummary;
import com.assettrader.model.coinmarket.OrderBook;
import com.assettrader.model.coinmarket.Ticker;
import com.assettrader.service.DTO.CoinServiceDTO;

@CrossOrigin
@RestController
@RequestMapping("/coins")
public class CoinApiController {

	// TODO - CONSTRUCT A CLASS TO HANDLE/FORWARD A RESPONSE THAT CAN BE ITERATED THROUGH FROM CLIENT
	
	private static int FIRST_PERSIST = 0;
	
	@Autowired
	public CoinServiceDTO coinDTOService;

	
	@RequestMapping(value = "/getmarkets/{exchange}", method = RequestMethod.GET)
	public List<Coin> getCoin(Model model, @PathVariable String exchange) {
		String validatedExchange = validateExchangeName(exchange);
		
		if (validatedExchange != ERROR_INVALID_EXCHANGE) {
			if (FIRST_PERSIST == 0) {
				FIRST_PERSIST++;
				return coinDTOService.loadApplicationBootingEndPoints(validatedExchange);
			}
			return coinDTOService.getCoinMarkets(validatedExchange);
		}		
		return null;
	}
	
	@RequestMapping(value = "/getcurrencies/{exchange}", method = RequestMethod.GET)
	private List<Currency> getCurrencies(Model model, @PathVariable String exchange) {
		String validatedExchange = validateExchangeName(exchange);
		
		if (validatedExchange != ERROR_INVALID_EXCHANGE) {		
			if (FIRST_PERSIST == 0) {
				coinDTOService.loadApplicationBootingEndPoints(validatedExchange);
				FIRST_PERSIST++;
			}		
			return coinDTOService.getCurrencies(validatedExchange);
		}
		return null;
	}
			
	
	@RequestMapping(value = "/getmarketsummaries/{exchange}", method = RequestMethod.GET)
	private List<MarketSummary> getMarketSummaries(Model model, @PathVariable String exchange) {
		String validatedExchange = validateExchangeName(exchange);
		
		if (validatedExchange != ERROR_INVALID_EXCHANGE) {
			if (FIRST_PERSIST == 0) {
				coinDTOService.loadApplicationBootingEndPoints(validatedExchange);
				FIRST_PERSIST++;
			}
			return coinDTOService.getMarketSummaries(validatedExchange);
		}
		
		return null;
	}
	
	
	@RequestMapping(value = "/getmarketsummary/{marketname}&{exchange}", method = RequestMethod.GET)
	private MarketSummary getMarketSummary(Model model, @PathVariable String marketname, @PathVariable String exchange) {
		String validatedExchange = validateExchangeName(exchange);
		
		if (validatedExchange != ERROR_INVALID_EXCHANGE) {
			if (FIRST_PERSIST == 0) {
				coinDTOService.loadApplicationBootingEndPoints(validatedExchange);
				FIRST_PERSIST++;
			}		
			return coinDTOService.getMarketSummary(marketname, validatedExchange);
		}
		return null;
	}
	
	
	@RequestMapping(value = "/getorderbook/{marketname}&{ordertype}&{exchange}", method = RequestMethod.GET)
	private List<OrderBook> getOrderBook(Model model, 
			@PathVariable String marketname, @PathVariable String ordertype, @PathVariable String exchange) {
			
		String validatedExchange = validateExchangeName(exchange);
		String validatedOrderType = validateOrderType(ordertype);
		
		if (validatedExchange != ERROR_INVALID_EXCHANGE) {
			if (FIRST_PERSIST == 0) {
				coinDTOService.loadApplicationBootingEndPoints(validatedExchange);
				FIRST_PERSIST++;
			}
			
			return coinDTOService.getOrderBook(marketname, validatedOrderType, validatedExchange);
		}
		return null;
	}

	
	@RequestMapping(value = "/getticker/{marketname}&{exchange}", method= RequestMethod.GET)
	private Ticker getTicker(Model model, @PathVariable String marketname, @PathVariable String exchange) {
		String validatedExchange = validateExchangeName(exchange);
		
		if (validatedExchange != ERROR_INVALID_EXCHANGE) {
			if (FIRST_PERSIST == 0) {
				coinDTOService.loadApplicationBootingEndPoints(validatedExchange);
				FIRST_PERSIST++;
			}
	
			return coinDTOService.getTicker(marketname, validatedExchange);
		}
		return null;
	}
	
	@RequestMapping(value = "/getmarkethistory/{marketname}&{exchange}", method= RequestMethod.GET)
	private List<MarketHistory> getMarketHistory(Model model, @PathVariable String marketname, @PathVariable String exchange) {
		String validatedExchange = validateExchangeName(exchange);
		
		if (validatedExchange != ERROR_INVALID_EXCHANGE) {
			if (FIRST_PERSIST == 0) {
				coinDTOService.loadApplicationBootingEndPoints(validatedExchange);
				FIRST_PERSIST++;
			}
			
			return coinDTOService.getMarketHistory(marketname, validatedExchange);
			}
		return null;
	}
	
	
}
