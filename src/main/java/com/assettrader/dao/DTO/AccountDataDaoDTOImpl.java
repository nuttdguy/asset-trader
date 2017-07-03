package com.assettrader.dao.DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.assettrader.api.bittrex.model.accountapi.Balance;
import com.assettrader.api.bittrex.model.accountapi.DepositAddress;
import com.assettrader.api.bittrex.model.accountapi.DepositHistoryEntry;
import com.assettrader.api.bittrex.model.accountapi.OrderHistoryEntry;
import com.assettrader.api.bittrex.model.accountapi.WithdrawalHistoryEntry;
import com.assettrader.api.bittrex.model.common.ApiResult;
import com.assettrader.dao.CoinDao;
import com.assettrader.model.utils.ExchangeName;
import com.assettrader.utils.DAOUtils;
import com.assettrader.utils.LocalDateTimePersistenceConverter;

@Repository
public class AccountDataDaoDTOImpl implements AccountDataDaoDTO {
	
	
	private Connection connection = null;
	private PreparedStatement statement = null;
	private LocalDateTimePersistenceConverter dateConverter = new LocalDateTimePersistenceConverter();

	
	@Override
	public ApiResult<List<Balance>> saveAllAccountBalancesDTO(ApiResult<List<Balance>> balanceApiDTO) {
		
		try {
			connection = DAOUtils.getConnection();
			String sql = "INSERT IGNORE INTO BALANCE(AVAILABLE, ACCOUNT_BALANCE, BALANCE_DATE, "
					+ "CRYPTO_ADDRESS, CURRENCY, PENDING, EXCHANGE_NAME, USER_PROFILE_ID) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?) ";
			
			List<Balance> balanceList = balanceApiDTO.getResult();
			
			for (Balance entry : balanceList){
				
				statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS );
				statement.setDouble(1, entry.getAvailable());
				statement.setDouble(2, entry.getBalance());
				
				statement.setTimestamp(3, dateConverter.convertToDatabaseColumn(LocalDateTime.now()));
				statement.setString(4, entry.getCryptoAddress());
				statement.setString(5, entry.getCurrency());
				statement.setDouble(6, entry.getPending());
				
				statement.setString(7, ExchangeName.BITTREX.name());
				statement.setLong(8, 1); // TODO - TEST USER CAN BE ADDED TO DATABASE AND RETRIEVED		
				
				statement.execute();
				statement.clearParameters();
			}
			
			System.out.println("Successfully persisted ALL ACCOUNT BALANCE RECORDS");
		} catch (SQLException sex) {
			System.out.println("SQL Exception: " + sex.getMessage() );
		} finally {
			closeResources();
		}
		
		return balanceApiDTO;
	}
	
	
	@Override
	public ApiResult<Balance> saveAccountBalanceDTO(ApiResult<Balance> balanceApiDTO) {
		
		try {
			connection = DAOUtils.getConnection();
			String sql = "INSERT IGNORE INTO BALANCE(AVAILABLE, ACCOUNT_BALANCE, BALANCE_DATE, "
					+ "CRYPTO_ADDRESS, CURRENCY, PENDING, EXCHANGE_NAME, USER_PROFILE_ID) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			
			statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS );
						
			statement.setDouble(1, balanceApiDTO.getResult().getAvailable());
			statement.setDouble(2, balanceApiDTO.getResult().getBalance());
			statement.setTimestamp(3, dateConverter.convertToDatabaseColumn(LocalDateTime.now()));
			
			statement.setString(4, balanceApiDTO.getResult().getCryptoAddress());
			statement.setString(5, balanceApiDTO.getResult().getCurrency());
			statement.setDouble(6, balanceApiDTO.getResult().getPending());
			statement.setString(7, ExchangeName.BITTREX.name());
			statement.setLong(8, 1); // TODO - TEST USER CAN BE ADDED TO DATABASE AND RETRIEVED		
			
			statement.execute();
			System.out.println("Successfully persisted ACCOUNT BALANCE RECORD");
			
		} catch (SQLException sex) {
			System.out.println("SQL Exception: " + sex.getMessage() );
		} finally {
			closeResources();
		}
		
		return balanceApiDTO;
	}

	@Override
	public ApiResult<DepositAddress> saveDepositAddressDTO(ApiResult<DepositAddress> depositAddressDTO) {

		try {
			connection = DAOUtils.getConnection();
			String sql = "INSERT IGNORE INTO DEPOSIT_ADDRESS(ADDRESS, CURRENCY, EXCHANGE_NAME, USER_PROFILE_ID) "
					+ "VALUES(?, ?, ?, ?)";
			
			statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, depositAddressDTO.getResult().getAddress() );
			statement.setString(2, depositAddressDTO.getResult().getCurrency() );
			statement.setString(3, ExchangeName.BITTREX.name() );
			statement.setLong(4, 1);
			
			statement.execute();
			System.out.println("Successfully persisted DEPOSIT ADDRESS RECORD");
			
		} catch (SQLException sex) {
			System.out.println("SQL Exception: " + sex.getMessage() );
		} finally {
			closeResources();
		}
		
		return depositAddressDTO;
	}

	@Override
	public ApiResult<List<OrderHistoryEntry>> saveAllOrderHistoryEntry(
			ApiResult<List<OrderHistoryEntry>> orderHistoryDTO, String marketName) {
		
		try {
			connection = DAOUtils.getConnection();
			String sql = "INSERT IGNORE INTO ORDER_HISTORY_ENTRY( "
					+ "ORDER_UUID, CLOSED, COMMISSION, CONDITIONS_OF_EXCHANGE, CONDITION_OF_EXCHANGE_TARGET, " // 5
					+ "EXCHANGE_CURRENCY_MARKET, IMMEDIATE_OR_CANCEL, IS_CONDITIONAL, " // 3
					+ "STOP_LIMIT, ORDER_TYPE, PRICE, PRICE_PER_UNIT, QUANTITY, " // 5
					+ "QUANTITY_REMAINING, TIME_STAMP, CURRENCY, EXCHANGE_NAME, USER_PROFILE_ID) " // 5
					+ "VALUES("
					+ "?, ?, ?, ?, ?, "
					+ "?, ?, ?, ?, ?, "
					+ "?, ?, ?, ?, ?, "
					+ "?, ?, ? )";
			
			List<OrderHistoryEntry> orderHistoryList = orderHistoryDTO.getResult();
			
			for (OrderHistoryEntry entry : orderHistoryList) {
				
				statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				statement.setString(1, entry.getOrderUuid());
				statement.setTimestamp(2, new Timestamp(entry.getClosed().getTime() ));
				statement.setDouble(3, entry.getCommission());
				statement.setString(4, entry.getCondition());			
				statement.setString(5, entry.getConditionTarget());
				
				statement.setString(6, entry.getExchange());
				statement.setBoolean(7, entry.getImmediateOrCancel());
				statement.setBoolean(8, entry.getIsConditional());
				
				statement.setDouble(9, entry.getLimit());
				statement.setString(10, entry.getOrderType());
				statement.setDouble(11, entry.getPrice());
				statement.setDouble(12, entry.getPricePerUnit());
				statement.setDouble(13, entry.getQuantity());
				
				statement.setDouble(14, entry.getQuantityRemaining());
				statement.setTimestamp(15, new Timestamp(entry.getTimeStamp().getTime() ));				
				statement.setString(16, marketName.substring(4).toUpperCase() );
				statement.setString(17, ExchangeName.BITTREX.name());
				statement.setLong(18, 1); // CHANGE WHEN USER REGISTRATION COMPLETED
				
				statement.execute();
			}
			
			System.out.println("Successfully persisted ORDER HISTORY RECORD");
		} catch (SQLException sex) {
			System.out.println("SQL Exception: " + sex.getMessage() );
		} finally {
			closeResources();
		}
		
		return orderHistoryDTO;
	}
	

	@Override
	public ApiResult<List<OrderHistoryEntry>> saveAllOrderHistoryEntry(
			ApiResult<List<OrderHistoryEntry>> orderHistoryDTO) {
		try {
			connection = DAOUtils.getConnection();
			String sql = "INSERT IGNORE INTO ORDER_HISTORY_ENTRY( "
					+ "ORDER_UUID, CLOSED, COMMISSION, CONDITIONS_OF_EXCHANGE, CONDITION_OF_EXCHANGE_TARGET, " // 5
					+ "EXCHANGE_CURRENCY_MARKET, IMMEDIATE_OR_CANCEL, IS_CONDITIONAL, " // 3
					+ "STOP_LIMIT, ORDER_TYPE, PRICE, PRICE_PER_UNIT, QUANTITY, " // 5
					+ "QUANTITY_REMAINING, TIME_STAMP, CURRENCY, EXCHANGE_NAME, USER_PROFILE_ID) " // 5
					+ "VALUES("
					+ "?, ?, ?, ?, ?, "
					+ "?, ?, ?, ?, ?, "
					+ "?, ?, ?, ?, ?, "
					+ "?, ?, ? )";
			
			List<OrderHistoryEntry> orderHistoryList = orderHistoryDTO.getResult();
			
			for (OrderHistoryEntry entry : orderHistoryList) {
				
				statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				statement.setString(1, entry.getOrderUuid());
				statement.setTimestamp(2, new Timestamp(entry.getClosed().getTime() ));
				statement.setDouble(3, entry.getCommission());
				statement.setString(4, entry.getCondition());			
				statement.setString(5, entry.getConditionTarget());
				
				statement.setString(6, entry.getExchange());
				statement.setBoolean(7, entry.getImmediateOrCancel());
				statement.setBoolean(8, entry.getIsConditional());
				
				statement.setDouble(9, entry.getLimit());
				statement.setString(10, entry.getOrderType());
				statement.setDouble(11, entry.getPrice());
				statement.setDouble(12, entry.getPricePerUnit());
				statement.setDouble(13, entry.getQuantity());
				
				statement.setDouble(14, entry.getQuantityRemaining());
				statement.setTimestamp(15, new Timestamp(entry.getTimeStamp().getTime() ));				
				statement.setString(16, entry.getExchange().substring(4).toUpperCase() ); // GETS THE CURRENCY SHORT-NAME
				statement.setString(17, ExchangeName.BITTREX.name());
				statement.setLong(18, 1); // CHANGE WHEN USER REGISTRATION COMPLETED
				
				statement.execute();
			}
			
			System.out.println("Successfully persisted ORDER HISTORY RECORD");
		} catch (SQLException sex) {
			System.out.println("SQL Exception: " + sex.getMessage() );
		} finally {
			closeResources();
		}
		
		return orderHistoryDTO;
	}

	@Override
	public ApiResult<List<WithdrawalHistoryEntry>> saveAllWithdrawalHistory(
			ApiResult<List<WithdrawalHistoryEntry>> withdrawalHistoryDTO) {

		try {
			connection = DAOUtils.getConnection();
			String sql = "INSERT IGNORE INTO WITHDRAWAL_HISTORY_ENTRY( "
					+ "PAYMENT_UUID, ADDRESS, AMOUNT, AUTHORIZED, CANCELED, " // 5
					+ "CURRENCY, INVALID_ADDRESS, OPENED, PENDING_PAYMENT, TX_COST, " // 5
					+ "TX_ID, EXCHANGE_NAME, USER_PROFILE_ID ) " // 3
					+ "VALUES("
					+ "?, ?, ?, ?, ?, "
					+ "?, ?, ?, ?, ?, "
					+ "?, ?, ?) ";
			
			List<WithdrawalHistoryEntry> withdrawList = withdrawalHistoryDTO.getResult();
			
			for (WithdrawalHistoryEntry entry : withdrawList) {
				
				statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);		
				statement.setString(1, entry.getPaymentUuid());
				statement.setString(2, entry.getAddress());
				statement.setString(3, entry.getAmount());
				statement.setBoolean(4, entry.getAuthorized());
				statement.setBoolean(5, entry.getCanceled());
				statement.setString(6, entry.getCurrency());
				statement.setBoolean(7, entry.getInvalidAddress());
				statement.setTimestamp(8, new Timestamp(entry.getOpened().getTime()));
				statement.setBoolean(9, entry.getPendingPayment());
				statement.setDouble(10, entry.getTxCost());
				statement.setString(11, entry.getTxId());
				statement.setString(12, ExchangeName.BITTREX.name());
				statement.setLong(13, 1); // TODO, CHANGE FOR WHEN USER ENROLLS
				
				statement.execute();
				statement.clearParameters();
				
			}
			
			System.out.println("Successfully persisted WITHDRAWAL HISTORY RECORDS");
		} catch (SQLException se) {
			System.out.println("SQL Exception: " + se.getMessage());
		} finally {
			closeResources();
		}
		
		return withdrawalHistoryDTO;
	}

	@Override
	public ApiResult<List<DepositHistoryEntry>> saveAllDepositHistory(
			ApiResult<List<DepositHistoryEntry>> depositHistoryDTO) {
		
		try {
			connection = DAOUtils.getConnection();
			String sql = "INSERT IGNORE INTO DEPOSIT_HISTORY_ENTRY( "
					+ "AMOUNT, CONFIRMATIONS, CRYPTO_ADDRESS, CURRENCY," // 4
					+ "LAST_UPDATED, TX_ID, EXCHANGE_NAME, USER_PROFILE_ID) " // 4
					+ "VALUES("
					+ "?, ?, ?, ?, ?, "
					+ "?, ?, ? )";
			
			List<DepositHistoryEntry> depositList = depositHistoryDTO.getResult();
			
			for (DepositHistoryEntry entry : depositList) {
				statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				
				statement.setDouble(1, entry.getAmount());
				statement.setInt(2, entry.getConfirmations());
				statement.setString(3, entry.getCryptoAddress());
				statement.setString(4, entry.getCurrency());
				statement.setTimestamp(5, new Timestamp(entry.getLastUpdated().getTime()));
				statement.setString(6, entry.getTxId());
				statement.setString(7, ExchangeName.BITTREX.name());
				statement.setLong(8, 1); // TODO -- CHANGE AFTER IMPLEMENTING USER-LOGIN
				
				statement.execute();
				statement.clearParameters();
			}
			
			System.out.println("Successfully persisted DEPOSIT HISTORY RECORDS");
		} catch (SQLException se) {
			System.out.println("SQL Exception: " + se.getMessage());
		} finally {
			closeResources();
		}
		
		
		return depositHistoryDTO;
	}


	// PRIVATE METHODS TO CLOSE OPENED RESOURCES
	private void closeResources() {
		try {
			if (statement != null)
				statement.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}
	

	
}
