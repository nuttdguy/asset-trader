package com.assettrader.api.bittrex;

import javax.annotation.Nullable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.assettrader.api.bittrex.config.ApiBuilderFactory;
import com.assettrader.api.bittrex.config.ApiCredentials;

import feign.Feign;
import feign.Logger;
import feign.Util;
import feign.slf4j.Slf4jLogger;

/**
 * @author contact@elbatya.de
 */
@Service
public class BittrexClient {

	public static final String DEFAULT_BASE_URL = "https://bittrex.com/api/v1.1";
	
	private BittrexAccountApi accountApi;

	private boolean credentialsAvailable = true;

	public BittrexClient() {
		this(DEFAULT_BASE_URL, null, null);
	}

	public BittrexClient(@Nullable Logger.Level logLevel) {
		this(DEFAULT_BASE_URL, null, logLevel);
	}

	public BittrexClient(@Nullable ApiCredentials credentials) {
		this(DEFAULT_BASE_URL, credentials, null);
	}

	public BittrexClient(@Nullable ApiCredentials credentials, @Nullable Logger.Level logLevel) {
		this(DEFAULT_BASE_URL, credentials, logLevel);
	}

	public BittrexClient(String baseUrl, @Nullable ApiCredentials credentials, @Nullable Logger.Level logLevel) {

		Util.checkNotNull(baseUrl, "The baseUrl must not be null!");

		ApiBuilderFactory apiBuilderFactory = new ApiBuilderFactory(baseUrl);

		Feign.Builder apiBuilder = apiBuilderFactory.createApiBuilder(credentials);

		if (logLevel != null) {
			apiBuilder.logger(new Slf4jLogger(BittrexClient.class)).logLevel(logLevel);
		}

		// TODO -- CONVERT TO THIS PUBLIC IMPLEMENTATION 
		// publicApi = apiBuilder.target(BittrexPublicApi.class, baseUrl);

		credentialsAvailable = credentials != null;

		if (credentialsAvailable) {
			// TODO -- CONVERT TO THIS MARKET IMPLEMENTATION 
			// marketApi = apiBuilder.target(BittrexAccountApi.class, baseUrl);
			accountApi = apiBuilder.target(BittrexAccountApi.class, baseUrl);
		}
	}

	public BittrexAccountApi getAccountApi() {
		failIfNoCredentials("You can't use the AccountAPI without credentials.");
		return accountApi;
	}

	private void failIfNoCredentials(String message) {
		if (!credentialsAvailable) {
			throw new RuntimeException(message);
		}
	}
	
}
