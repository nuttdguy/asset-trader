package com.assettrader.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assettrader.dao.AccountDataDao;
import com.assettrader.model.rest.RWLoginDetail;
import com.assettrader.service.AccountDataService;
import com.assettrader.utils.BittrexKeyUtil;

@Service
public class AccountDataServiceImpl implements AccountDataService {

	@Autowired
	public AccountDataDao accountDataDao;
	
	@Override
	public BittrexKeyUtil getApiKey(RWLoginDetail userDetail) {
		return accountDataDao.getApiKey(userDetail);
	}

}
