package com.assettrader.service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.assettrader.model.DTO.CoinDTO;
import com.assettrader.model.DTO.CoinResultDTO;
import com.assettrader.model.coin.Coin;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.RawValue;

public class CoinDTOService {

	// TODO, Convert CoinDTO to COIN for return type
	@SuppressWarnings("null")
	public HashMap<String, CoinResultDTO> refreshCoinMarketsURLImpl() {
		// StringBuilder jsonStringBuilder = new StringBuilder();

		try {
			URL url = new URL("https://bittrex.com/api/v1.1/public/getmarkets");
			ObjectMapper mapper = new ObjectMapper();	
			mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
			
			 
			@SuppressWarnings("unchecked")
			HashMap<String, CoinResultDTO> coinDTOMap = mapper.readValue(url, HashMap.class); // Map JSON response
			// List<String> coinDTOMap = mapper.readValue(url, List.class);
			// Map<String, RawValue> results = mapper.readValue(url, new TypeReference<Map<String, RawValue>>() { } );
			
			//CoinResultDTO coin = new CoinResultDTO();
			//coin = mapper.readValue(jsonStringBuilder.toString(), CoinResultDTO.class);

			// TODO - CREATE METHOD TO TRANSFER DTO OBJECT TO OBJECT
			// List<Coin> coinList = CoinDTOToCoin(coinDTOMap); 
			// List<String> coinList = CoinDTOToCoinList(coinDTOMap); 
			// Map<String, CoinResultDTO> coinList = CoinDTOToCoinMap(coinDTOMap); 
			
			return coinDTOMap;

		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		}

		return null;

	}
	
	@SuppressWarnings("unchecked")
	private static HashMap<String, CoinResultDTO> CoinDTOToCoinMap(HashMap<String, CoinResultDTO> coinDTOMap) {
		
		List<String> coinList = new ArrayList<>();
		
		for ( Map.Entry<String, CoinResultDTO> entry : coinDTOMap.entrySet()) {
			System.out.println(entry);
			if (entry.getKey().equals("result")) {
				
			}			
		}
		
		
		Collection<CoinResultDTO> coinCollection = coinDTOMap.values();
		
		while ( coinCollection.iterator().hasNext()) {
			Object[] coinArr = coinCollection.toArray();
			System.out.println(coinArr);
			// coinList.addAll( );
		}
		
//		coinDTOMap.forEach( (k,v) -> {
//			if (k.equals("result")) {
//				String[] sList = v.toString().
//				System.out.println(sList.length);
//			} else {
//				System.out.println("key: " + k + " || value: " + v);
//			}
//		}); 
		
		return null;
		
	}
	
	
	@SuppressWarnings("unchecked")
	private static List<String> CoinDTOToCoinList(List<String> coinDTOMap) {
		
		List<String> coinList = new ArrayList<>();
		
		coinDTOMap.forEach( (k) -> { System.out.println(k); });
		
		return coinList;
		
	}
	
//	@SuppressWarnings("unchecked")
//	private static List<Coin> CoinDTOToCoin(Map<String, CoinResultDTO> coinDTOMap) {
//		
//		Collection<CoinResultDTO> coinDTOList = coinDTOMap.values();
//		List<Coin> coinList = new ArrayList<>();
//		
//		coinDTOList.forEach((k) -> {
//			System.out.println(k.getResult());
//		});
//		for (int k = 0;k < coinDTOMap.; k++) {
//			coinList.addAll((Collection<? extends Coin>) coinDTOMap.get("result"));
//		}
//		
//		for (Iterator<CoinResultDTO> iterator = coinDTOList.iterator(); iterator.hasNext();) {	
//			System.out.println( iterator.next().getMessage().toString() ); // Checks whether next object 
//			// System.out.println(iterator);  //  Returns the hash-value
//			System.out.println(coinDTOMap.get("result")); // Gets the inner result of "result" list
//			// for (coinDTOMap.get("result");
//						
//		}
//		
//		return coinList;
//		
//	}
	
}
