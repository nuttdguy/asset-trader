package com.assettrader.dao.DTO;

import java.util.List;

import com.assettrader.model.coin.Coin;
import com.assettrader.model.coin.Currency;
import com.assettrader.model.coin.MarketHistory;
import com.assettrader.model.coin.MarketSummary;
import com.assettrader.model.coin.OrderBook;
import com.assettrader.model.coin.Ticker;

public interface CoinDTODao {

	public void saveGetTicker(Ticker ticker, String exchange);
	public void saveGetMarketSummary(MarketSummary marketSummary, String exchange);
	public void saveGetMarketHistory(List<MarketHistory> marketHistory, String exchange);
	public void saveGetOrderBook(List<OrderBook> orderBook, String exchange);
	
	public void saveGetMarkets(List<Coin> coinList, String exchange);
	public void saveGetCurrencies(List<Currency> currencyList, String exchange);
	public void saveGetMarketSummaries(List<MarketSummary> marketSummaryList, String exchange);
	public void saveGetMarketSummary(List<MarketSummary> marketSummary, String exchange);	
	
}
