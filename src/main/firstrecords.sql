INSERT INTO COIN( 		MARKET_NAME, CREATED, MIN_TRADE_SIZE,
						NOTICE, BASE_CURRENCY, BASE_CURRENCY_LONG,
						IS_ACTIVE, LOGO_URL, MARKET_CURRENCY, MARKET_CURRENCY_LONG)
VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);


INSERT INTO CURRENCY(	BASE_ADDRESS, COIN_TYPE,
						CURRENCY_LONG, CURRENCY, IS_ACTIVE, MIN_CONFIRMATION, TX_FEE)
VALUES(?, ?, ?, ?, ?, ?, ?);


INSERT INTO MARKET_SUMMARY(		ASK, BID, CREATED, HIGH, LOW, MARKET_NAME, OPEN_BUY_ORDERS,
								OPEN_SELL_ORDERS, PREV_DAY, TIME_STAMP, VOLUME)
VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);

#DYNAMIC SINGLE VALUE
INSERT INTO ORDER_BOOK(		ORDER_BOOK_DATETIME, ORDER_TYPE, QUANTITY, RATE) VALUES(?, ?, ?, ?);

#DYNAMIC SINGLE VALUE
INSERT INTO ORDER_BOOK(    	ORDER_BOOK_DATETIME, QUANTITY, RATE ) VALUES(?, ?, ?);


@DYNAMIC SINGLE VALUE
INSERT INTO TICKER( 		ASK, BID, LAST) VALUES (?, ?, ?);


