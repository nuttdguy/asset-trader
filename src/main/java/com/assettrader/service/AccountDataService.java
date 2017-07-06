package com.assettrader.service;

import com.assettrader.model.rest.RWLoginDetail;
import com.assettrader.utils.BittrexKeyUtil;


public interface AccountDataService {

	public BittrexKeyUtil getApiKey(RWLoginDetail userDetail);
}
