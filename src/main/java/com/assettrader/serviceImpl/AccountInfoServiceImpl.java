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
	private AccountInfoDao marketInfoDao;

	@Override
	public List<AccountInfoView> getMarketInfo(String exchange) {
		return marketInfoDao.getMarketInfoView(exchange);
	}

}
