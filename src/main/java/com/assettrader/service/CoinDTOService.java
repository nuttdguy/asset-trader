package com.assettrader.service;

import java.util.List;

import com.assettrader.model.coin.Coin;

public interface CoinDTOService {

	List<Coin> refreshCoinMarketsURLImpl();

}
