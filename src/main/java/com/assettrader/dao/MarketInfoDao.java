package com.assettrader.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.assettrader.model.view.MarketInfoView;

@Repository
@Transactional
public interface MarketInfoDao {

	
	public List<MarketInfoView> getMarketInfoView(String exchange);
	
}
