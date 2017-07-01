package com.assettrader.dao.DTO;

import java.util.List;

import com.assettrader.api.bittrex.model.accountapi.Balance;
import com.assettrader.api.bittrex.model.accountapi.DepositAddress;
import com.assettrader.api.bittrex.model.accountapi.DepositHistoryEntry;
import com.assettrader.api.bittrex.model.accountapi.OrderHistoryEntry;
import com.assettrader.api.bittrex.model.accountapi.WithdrawalHistoryEntry;
import com.assettrader.api.bittrex.model.common.ApiResult;

public interface AccountDataDaoDTO {

	ApiResult<Balance> saveAccountBalanceDTO(ApiResult<Balance> balanceApiDTO);
	ApiResult<DepositAddress> saveDepositAddressDTO(ApiResult<DepositAddress> depositAddressDTO);
	
	ApiResult<List<Balance>> saveAllAccountBalancesDTO(ApiResult<List<Balance>> balanceApiDTO);
	ApiResult<List<OrderHistoryEntry>> saveAllOrderHistoryEntry(ApiResult<List<OrderHistoryEntry>> orderHistoryDTO);
	ApiResult<List<WithdrawalHistoryEntry>> saveAllWithdrawalHistory(ApiResult<List<WithdrawalHistoryEntry>> withdrawalHistoryDTO);	
	ApiResult<List<DepositHistoryEntry>> saveAllDepositHistory(ApiResult<List<DepositHistoryEntry>> depositHistoryDTO);
	
}
