package com.assettrader.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.assettrader.model.DTO.CoinResultDTO;
import com.assettrader.model.coin.Coin;
import com.assettrader.service.CoinDTOService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/coins")
public class HomeProfileController {


//	@RequestMapping(value = "/getcoins", method = RequestMethod.POST)
//	public void getCoin(@ModelAttribute("coin") Coin coin) {
//		System.out.println(coin);
//
//	}

	@RequestMapping(value = "/getmarket", method = RequestMethod.GET)
	public String getCoin(Model model) {

		CoinDTOService coinDtoService = new CoinDTOService();
		
		HashMap<String, CoinResultDTO> coinList = (HashMap<String, CoinResultDTO>) coinDtoService.refreshCoinMarketsURLImpl();
		model.addAttribute("coins", coinList);		
		
		return "hello";
	}
	
	
	
	//////////////////////////////////////////////////////////////
	// TRYING METHODS FOR GETTING DATA FROM THE API ENDPOINT
	// TODO - CREATE A DTO TO ENTITY CLASS TO MAP RESULT TO OBJECT & VICE VERSA
	// TODO - CREATE A DTO TO ENTITY CLASS FOR LIST OF OBJECTS, CHECK DEPTH OF JSON OBJECT, LOOP THROUGH IN ORDER TO ASSIGN RESULTS
	
	
	// This gets a stream of the URL by line
	private static List<Coin> refreshCoinMarketsURLImpl() {
		
		try {
			URL url = new URL("https://bittrex.com/api/v1.1/public/getmarkets");
			ObjectMapper mapper = new ObjectMapper();
			BufferedReader inputStream = new BufferedReader(
					new InputStreamReader(url.openStream()));
			
					
			String line = "";
			while (line != null) {
				line = inputStream.readLine();
				System.out.println(line);
			}

			inputStream.close();
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		}
		
		return null; 
	}

	
	// This gets a stream of data from the URL & enables getting more detailed information about the request/response
	private static void refreshCoinMarketsURLConnectionImpl() {

		try {
			URL url = new URL("https://bittrex.com/api/v1.1/public/getmarkets");
			URLConnection urlConnection = url.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.connect();
			
			BufferedReader inputStream = new BufferedReader( 
					new InputStreamReader( urlConnection.getInputStream() ));
			
			String line = "";
			while (line != null) {
				line = inputStream.readLine();
				System.out.println(line);
			}
			
			inputStream.close();

		} catch (MalformedURLException ex) {
			System.out.println("Malformed URL: " + ex.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//  This gets stream specific to HTTP protocol and its featureS, can set many fields of a HTTP request
	private static void refreshCoinMarketsHTTPConnection() {
		
		try {
			URL url = new URL("https://bittrex.com/api/v1.1/public/getmarkets");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setRequestMethod("GET"); // EACH INSTANCE CAN ONLY MAKE A SINGLE REQUEST PER REQUEST
			connection.setRequestProperty("User-Agent", "Chrome"); // SETS THE BROWSER-HEADER
			connection.setReadTimeout(5000); // SETS THE TIMEOUT FOR THE REQUEST
			
			int responseCode = connection.getResponseCode();
			System.out.println("Response code: " + responseCode);
			
			// CHECKS WHETHER REQUEST WAS SUCCESSFUL, IF NOT RETURNS ERROR
			if (responseCode != 200) {
				System.out.println("Error reading web page");
				return;
			}
			
			BufferedReader inputReader = new BufferedReader(
					new InputStreamReader( connection.getInputStream() ));
			
			String line;
			while ((line = inputReader.readLine()) != null ) {
				System.out.println(line);
			}
			
			inputReader.close();
			
		} catch (IOException io ) {
			System.out.println("IO Exception: " + io.getMessage());
		}
		
	}

	// What should be displayed to user in their Home Profile
	// = I want to be able to see my username so that I know its my account
	// = I want to view my account balance for (a single account)
	// = I want to view my account balances for (all accounts)
	// = I want to view my favorites coins (limit 5, current market price, last
	// bid price, last ask price, date of last record, order of importance)
	// = I want to update the prices of my favorite coins (all)
	// = I want to update the price of my favorite coin (single)
	// = I want to add an importance priority on my favorite coins
	// = I want to add a coin as my favorite and view an updated list of my
	// favorites
	// = I want to view the current price of a primary pair (in USD)
	// =

	// List activities that will take place within this controller
	// 1. Get the getAccountBalances()
	// 2. Get the getAccountBalances(LocalDate dateStart, LocalDate dateEnd); //
	// use default values
	// 3. Get

}
