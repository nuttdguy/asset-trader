package com.assettrader.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.assettrader.dao.CoinDao;
import com.assettrader.model.coinmarket.Coin;
import com.assettrader.service.CoinService;

public class CoinServiceImpl implements CoinService {
	
	@Autowired
	private CoinDao coinDao;

	@Override
	public String getCoinMarketName(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCoinMarketNameLong(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCoinMarketCurrency(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCoinBaseCurrency(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMarketName(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override 	// ADDED IMPLEMENTATION
	public String getCoinLogo(String coinMarketName) {
		return coinDao.getCoinLogo(coinMarketName);
	}

	@Override 	// ADDED IMPLEMENTATION
	public List<Coin> getAllCoinLogos() {
		return coinDao.getAllCoinLogos();
	}
	
	@Override
	public Double getBidPriceLast(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getBidPriceRange(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getPriceCurrent(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getPriceOfPrimaryPair(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getMarketSummaryHigh(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getMarketSummaryLow(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getMarketSummaryVolume(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getMarketSummaryBaseVolume(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getMarketSummaryOpenBuyOrders(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getMarketSummaryOpenSellOrders(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getMarketHistoryOfBuyOrders(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getMarketHistoryOfSellOrders(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getMarketHistoryOfPriceOverRange(String coinMarketName, Date start, Date end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getCoinCreateDate(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Coin> getCoinAllPairs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkCoinIsActive(String coinMarketName) {
		// TODO Auto-generated method stub
		return false;
	}

}
