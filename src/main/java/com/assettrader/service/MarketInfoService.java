package com.assettrader.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.assettrader.model.view.MarketInfoView;


public interface MarketInfoService {

	List<MarketInfoView> getMarketInfo(String exchange);
		
}
