package com.assettrader.daoImpl;

public enum AccountApiSQLStatement {

	GET_ORDER_BY_UUID("INSERT INTO ORDER_HISTORY_ENTRY( "
					+ "ORDER_UUID, DATE_CLOSED, COMMISSION_PAID, COMMISSION_RESERVED_REMANING, " 
					+ "COMMISSION_RESERVED, CONDITIONS_OF_EXCHANGE, CONDITION_OF_EXCHANGE_TARGET, " 
					+ "EXCHANGE_CURRENCY_MARKET, IMMEDIATE_OR_CANCEL, IS_CONDITIONAL, IS_OPEN, " 
					+ "STOP_LIMIT, DATE_OPENED, PRICE, PRICE_PER_UNIT, ORDER_QUANTITY, "
					+ "ORDER_QUANTITY_REMAINING, IN_RESERVE_REMAINING, IN_RESERVED, SENTINEL, " 
					+ "ORDER_TYPE, CURRENCY, EXCHANGE_NAME, USER_PROFILE_ID) "
					+ "VALUES("
					+ "?, ?, ?, ?, ?, "
					+ "?, ?, ?, ?, ?, "
					+ "?, ?, ?, ?, ?, "
					+ "?, ?, ?, ?, ?,"
					+ "?, ?, ?, ? )"),
	
	GET_ORDER_HISTORY("INSERT INTO ORDER_HISTORY_ENTRY( "
					+ "ORDER_UUID, CLOSED, COMMISSION, CONDITIONS_OF_EXCHANGE, CONDITION_OF_EXCHANGE_TARGET, "
					+ "EXCHANGE_CURRENCY_MARKET, IMMEDIATE_OR_CANCEL, IS_CONDITIONAL, "
					+ "STOP_LIMIT, ORDER_TYPE, PRICE, PRICE_PER_UNIT, QUANTITY, "
					+ "QUANTITY_REMAINING, TIME_STAMP, CURRENCY, EXCHANGE_NAME, USER_PROFILE_ID) "
					+ "VALUES("
					+ "?, ?, ?, ?, ?, "
					+ "?, ?, ?, ?, ?, "
					+ "?, ?, ?, ?, ?, "
					+ "?, ?, ? )");
	
	private String query;
	
	AccountApiSQLStatement(String query) {
		this.query = query;
	}

	public String getQuery() {
		return query;
	}

	
}
