package com.assettrader.controller;

import static com.assettrader.utils.ValidationHelperUtils.validateExchangeName;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assettrader.model.rest.ResWrapper;
import com.assettrader.model.view.AccountInfoView;
import com.assettrader.service.AccountInfoService;

@CrossOrigin
@RestController
public class AccountInfoController {
	
	@Autowired
	private AccountInfoService accountInfoService;
	
	@RequestMapping(value = "/market/{exchange}")
	public ResWrapper<List<AccountInfoView>> getMarketInfoView(@PathVariable String exchange) {
		
		String validatedExchange = validateExchangeName(exchange);
		List<AccountInfoView> viewList = accountInfoService.getMarketInfoView(validatedExchange);
		ResWrapper<List<AccountInfoView>> resResponse = new ResWrapper<>();
		resResponse.setResult(viewList);
		
		return resResponse;
		
	}
	

}
