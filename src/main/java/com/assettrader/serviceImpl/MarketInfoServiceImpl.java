package com.assettrader.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assettrader.dao.MarketInfoDao;
import com.assettrader.model.view.MarketInfoView;
import com.assettrader.service.MarketInfoService;

@Service
public class MarketInfoServiceImpl implements MarketInfoService {
	
	@Autowired
	private MarketInfoDao marketInfoDao;

	@Override
	public List<MarketInfoView> getMarketInfo(String exchange) {
		return marketInfoDao.getMarketInfoView(exchange);
	}

}
