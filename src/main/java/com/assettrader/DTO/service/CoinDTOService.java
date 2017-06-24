package com.assettrader.DTO.service;

import java.util.List;

import com.assettrader.model.coin.Coin;
import com.assettrader.model.coin.Currency;
import com.assettrader.model.coin.MarketHistory;
import com.assettrader.model.coin.MarketSummary;
import com.assettrader.model.coin.OrderBook;
import com.assettrader.model.coin.Ticker;

public interface CoinDTOService {

	List<Coin> getMarkets();
	List<Currency> getCurrencies();
	List<MarketSummary> getMarketSummaries();
	
	MarketSummary getMarketSummary(String marketName);
	Ticker getTicker(String marketName);
	
	List<MarketHistory> getMarketHistory(String marketName);	
	List<OrderBook> getOrderBook(String marketName, String buyOrSell);

}
