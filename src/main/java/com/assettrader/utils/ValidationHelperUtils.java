package com.assettrader.utils;

import com.assettrader.model.utils.ExchangeName;
import com.assettrader.model.utils.OrderType;

public class ValidationHelperUtils {
	
	public static final String ERROR_INVALID_EXCHANGE = "INVALID EXCHANGE";
	public static final String DEFAULT_ORDERTYPE = "BOTH";
	
	//==|| PRIVATE VALIDATION METHODS
	public static String validateExchangeName(String exchange) {
		for (ExchangeName ex : ExchangeName.values()) { 
			if (ex.name().toUpperCase().equals(exchange.toUpperCase())) {
				return ex.name().toUpperCase();
			}
		}
		return ERROR_INVALID_EXCHANGE;
	}
	
	public static String validateOrderType(String orderType) {
		for (OrderType type : OrderType.values()) {
			if (type.name().toUpperCase().equals(orderType.toUpperCase())) {
				return type.name().toUpperCase();
			}
		}
		return DEFAULT_ORDERTYPE;		
	}

}
