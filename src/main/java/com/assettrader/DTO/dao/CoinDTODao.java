package com.assettrader.DTO.dao;

import java.util.List;

import com.assettrader.model.coin.Coin;
import com.assettrader.model.coin.Currency;
import com.assettrader.model.coin.MarketHistory;
import com.assettrader.model.coin.MarketSummary;
import com.assettrader.model.coin.OrderBook;
import com.assettrader.model.coin.Ticker;

public interface CoinDTODao {

	public void saveGetTicker(Ticker ticker);
	public void saveGetMarketSummary(MarketSummary marketSummary);
	public void saveGetMarketHistory(List<MarketHistory> marketHistory);
	public void saveGetOrderBook(List<OrderBook> orderBook);
	
	public void saveGetMarkets(List<Coin> coinList);
	public void saveGetCurrencies(List<Currency> currencyList);
	public void saveGetMarketSummaries(List<MarketSummary> marketSummaryList);
	public void saveGetMarketSummary(List<MarketSummary> marketSummary);	
	
}
