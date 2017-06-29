package com.assettrader.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assettrader.api.bittrex.BittrexClient;
import com.assettrader.api.bittrex.model.accountapi.Balance;
import com.assettrader.api.bittrex.model.common.ApiResult;

@RestController
public class AccountDataController {

	
	@RequestMapping(value = "/account/getbalances", method = RequestMethod.GET)
	public ApiResult<List<Balance>> getBalances() {
		
		
/*		ApiCredentials credentials = new ApiCredentials(
				prop.getProperty("bittrex.key"), prop.getProperty("bittrex.secret"));*/
		
		BittrexClient bittrexApi = new BittrexClient();		
		return bittrexApi.getAccountApi().getBalances();
	}
	
}
