package com.assettrader.controller;

import static com.assettrader.utils.ValidationHelperUtils.validateExchangeName;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assettrader.model.rest.ResWrapper;
import com.assettrader.model.view.MarketInfoView;
import com.assettrader.service.MarketInfoService;

@CrossOrigin
@RestController
public class MarketInfoController {
	
	@Autowired
	private MarketInfoService marketInfoService;
	
	@RequestMapping(value = "/market/{exchange}")
	public ResWrapper<List<MarketInfoView>> getMarketInfoView(@PathVariable String exchange) {
		
		String validatedExchange = validateExchangeName(exchange);
		List<MarketInfoView> viewList = marketInfoService.getMarketInfo(validatedExchange);
		ResWrapper<List<MarketInfoView>> resResponse = new ResWrapper<>();
		resResponse.setResult(viewList);
		
		return resResponse;
		
	}
	
	

}
