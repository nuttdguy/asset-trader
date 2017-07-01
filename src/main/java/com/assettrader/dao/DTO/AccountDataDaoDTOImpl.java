package com.assettrader.dao.DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.assettrader.api.bittrex.model.accountapi.Balance;
import com.assettrader.api.bittrex.model.accountapi.DepositAddress;
import com.assettrader.api.bittrex.model.accountapi.DepositHistoryEntry;
import com.assettrader.api.bittrex.model.accountapi.OrderHistoryEntry;
import com.assettrader.api.bittrex.model.accountapi.WithdrawalHistoryEntry;
import com.assettrader.api.bittrex.model.common.ApiResult;
import com.assettrader.model.utils.ExchangeName;
import com.assettrader.utils.DAOUtils;
import com.assettrader.utils.DateTimePersistenceConverter;

@Repository
public class AccountDataDaoDTOImpl implements AccountDataDaoDTO {
	
	private Connection connection = null;
	private PreparedStatement statement = null;
	private DateTimePersistenceConverter localDateTimeConverter = new DateTimePersistenceConverter();

	
	@Override
	public ApiResult<List<Balance>> saveAllAccountBalancesDTO(ApiResult<List<Balance>> balanceApiDTO) {
		
		try {
			connection = DAOUtils.getConnection();
			String sql = "INSERT INTO BALANCE(AVAILABLE, ACCOUNT_BALANCE, BALANCE_DATE, "
					+ "CRYPTO_ADDRESS, CURRENCY, PENDING, EXCHANGE_NAME, USER_PROFILE_ID) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?) ";
			
			List<Balance> balanceList = balanceApiDTO.getResult();
			
			for (Balance entry : balanceList){
				
				statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS );
				statement.setDouble(1, entry.getAvailable());
				statement.setDouble(2, entry.getBalance());
				
				statement.setTimestamp(3, localDateTimeConverter.convertToDatabaseColumn(LocalDateTime.now()));
				statement.setString(4, entry.getCryptoAddress());
				statement.setString(5, entry.getCurrency());
				statement.setDouble(6, entry.getPending());
				
				statement.setString(7, ExchangeName.BITTREX.name());
				statement.setLong(8, 1); // TODO - TEST USER CAN BE ADDED TO DATABASE AND RETRIEVED		
				
				statement.execute();
				statement.clearParameters();
			}
			
			System.out.println("Successfully persisted ALL ACCOUNT BALANCE INSERTS");
		} catch (SQLException sex) {
			System.out.println("SQL Exception: " + sex.getMessage() );
		}
		
		return balanceApiDTO;
	}
	
	
	@Override
	public ApiResult<Balance> saveAccountBalanceDTO(ApiResult<Balance> balanceApiDTO) {
		
		try {
			connection = DAOUtils.getConnection();
			String sql = "INSERT INTO BALANCE(AVAILABLE, ACCOUNT_BALANCE, BALANCE_DATE, "
					+ "CRYPTO_ADDRESS, CURRENCY, PENDING, EXCHANGE_NAME, USER_PROFILE_ID) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			
			statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS );
						
			statement.setDouble(1, balanceApiDTO.getResult().getAvailable());
			statement.setDouble(2, balanceApiDTO.getResult().getBalance());
			statement.setTimestamp(3, localDateTimeConverter.convertToDatabaseColumn(LocalDateTime.now()));
			
			statement.setString(4, balanceApiDTO.getResult().getCryptoAddress());
			statement.setString(5, balanceApiDTO.getResult().getCurrency());
			statement.setDouble(6, balanceApiDTO.getResult().getPending());
			statement.setString(7, ExchangeName.BITTREX.name());
			statement.setLong(8, 1); // TODO - TEST USER CAN BE ADDED TO DATABASE AND RETRIEVED		
			
			statement.execute();
			System.out.println("Successfully persisted ACCOUNT BALANCE INSERT");
			
		} catch (SQLException sex) {
			System.out.println("SQL Exception: " + sex.getMessage() );
		}
		
		return balanceApiDTO;
	}

	@Override
	public ApiResult<DepositAddress> saveDepositAddressDTO(ApiResult<DepositAddress> depositAddressDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResult<List<OrderHistoryEntry>> saveAllOrderHistoryEntry(
			ApiResult<List<OrderHistoryEntry>> orderHistoryDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResult<List<WithdrawalHistoryEntry>> saveAllWithdrawalHistory(
			ApiResult<List<WithdrawalHistoryEntry>> withdrawalHistoryDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResult<List<DepositHistoryEntry>> saveAllDepositHistory(
			ApiResult<List<DepositHistoryEntry>> depositHistoryDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
