package com.assettrader.service.DTO;

import java.util.List;

import com.assettrader.model.coinmarket.Coin;
import com.assettrader.model.coinmarket.Currency;
import com.assettrader.model.coinmarket.MarketHistory;
import com.assettrader.model.coinmarket.MarketSummary;
import com.assettrader.model.coinmarket.OrderBook;
import com.assettrader.model.coinmarket.Ticker;

public interface CoinServiceDTO {

	List<Coin> getCoinMarkets(String exchange);
	List<Currency> getCurrencies(String exchange);
	List<MarketSummary> getMarketSummaries(String exchange);
	
	Ticker getTicker(String marketName, String exchange);
	
	List<MarketHistory> getMarketHistory(String marketName, String exchange);	
	List<OrderBook> getOrderBook(String marketName, String buyOrSell, String exchange);
	
	List<Coin> loadApplicationBootingEndPoints(String exchange);

}
