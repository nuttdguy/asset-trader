package com.assettrader.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.assettrader.model.view.AccountInfoView;

@Repository
@Transactional
public interface AccountInfoDao {

	
	public List<AccountInfoView> getMarketInfoView(String exchange);
	
}
