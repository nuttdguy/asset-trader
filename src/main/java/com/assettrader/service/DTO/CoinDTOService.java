package com.assettrader.service.DTO;

import java.util.List;

import com.assettrader.model.coin.Coin;
import com.assettrader.model.coin.Currency;
import com.assettrader.model.coin.MarketHistory;
import com.assettrader.model.coin.MarketSummary;
import com.assettrader.model.coin.OrderBook;
import com.assettrader.model.coin.Ticker;

public interface CoinDTOService {

	List<Coin> getCoinMarkets(String exchange);
	List<Currency> getCurrencies(String exchange);
	List<MarketSummary> getMarketSummaries(String exchange);
	
	MarketSummary getMarketSummary(String marketName, String exchange);
	Ticker getTicker(String marketName, String exchange);
	
	List<MarketHistory> getMarketHistory(String marketName, String exchange);	
	List<OrderBook> getOrderBook(String marketName, String buyOrSell, String exchange);
	
	List<Coin> loadApplicationBootingEndPoints(String exchange);

}
