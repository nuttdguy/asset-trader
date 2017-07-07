package com.assettrader.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assettrader.dao.AccountInfoDao;
import com.assettrader.model.view.AccountInfoView;
import com.assettrader.service.AccountInfoService;

@Service
public class AccountInfoServiceImpl implements AccountInfoService {
	
	@Autowired
	private AccountInfoDao accountInfoDao;
	
	//============================================
	//== CREATE
	//============================================
	
	
	//============================================
	//=== UPDATE
	//============================================
	
	
	//============================================
	//=== DELETES
	//============================================
	

	//============================================
	//=== RETRIEVE
	//============================================

	@Override
	public List<AccountInfoView> getMarketInfoView(String exchange) {
		return accountInfoDao.getMarketInfoView(exchange);
	}
	
}
