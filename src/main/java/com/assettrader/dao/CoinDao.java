package com.assettrader.dao;

import java.util.Date;
import java.util.List;

import com.assettrader.model.coinmarket.Coin;

public interface CoinDao {

	// TODO, add parameters according to API endpoint
	String getCoinMarketName(String coinMarketName);
	String getCoinMarketNameLong(String coinMarketName);
	Long getCoinMarketId(String marketName);
	String getMarketName(String marketName);
	String getMarketNameByCurrencyShortName(String currencyShortName);
	
	String getCoinMarketCurrency(String coinMarketName);
	String getCoinBaseCurrency(String coinMarketName);
	String getCoinLogo(String coinMarketName);
	
	Double getBidPriceLast(String coinMarketName);
	Double getBidPriceRange(String coinMarketName);
	Double getPriceCurrent(String coinMarketName);
	Double getPriceOfPrimaryPair(String coinMarketName);
	Double getMarketSummaryHigh(String coinMarketName);
	Double getMarketSummaryLow(String coinMarketName);
	Double getMarketSummaryVolume(String coinMarketName);
	Double getMarketSummaryBaseVolume(String coinMarketName);
	Double getMarketSummaryOpenBuyOrders(String coinMarketName);
	Double getMarketSummaryOpenSellOrders(String coinMarketName);
	Double getMarketHistoryOfBuyOrders(String coinMarketName);
	Double getMarketHistoryOfSellOrders(String coinMarketName);
	Double getMarketHistoryOfPriceOverRange(String coinMarketName, Date start, Date end);
	
	Date getCoinCreateDate(String coinMarketName);
	
	List<Coin> getCoinAllPairs();
	boolean checkCoinIsActive(String coinMarketName);
}
