package com.assettrader.api.bittrex.model.common;


public class BittrexApiException extends RuntimeException {

	private static final long serialVersionUID = -4335947907885900836L;

	public BittrexApiException() {
    }

    public BittrexApiException(String message) {
        super(message);
    }

    public BittrexApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public BittrexApiException(Throwable cause) {
        super(cause);
    }

    public BittrexApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
