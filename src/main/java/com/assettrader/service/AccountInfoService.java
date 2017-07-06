package com.assettrader.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.assettrader.model.view.AccountInfoView;


public interface AccountInfoService {

	List<AccountInfoView> getMarketInfo(String exchange);
		
}
