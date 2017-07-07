package com.assettrader.service.DTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assettrader.api.bittrex.model.accountapi.Balance;
import com.assettrader.api.bittrex.model.accountapi.DepositAddress;
import com.assettrader.api.bittrex.model.accountapi.DepositHistoryEntry;
import com.assettrader.api.bittrex.model.accountapi.OrderHistoryEntry;
import com.assettrader.api.bittrex.model.accountapi.WithdrawalHistoryEntry;
import com.assettrader.api.bittrex.model.common.ApiResult;
import com.assettrader.dao.DTO.AccountDataDaoDTO;

@Service
public class AccountDataServiceDTOImpl implements AccountDataServiceDTO {
	
	@Autowired
	AccountDataDaoDTO accountDataDaoDTO;

	@Override
	public ApiResult<List<Balance>> saveAllAccountBalancesDTO(ApiResult<List<Balance>> balanceApiDTO, Long userId) {
		return accountDataDaoDTO.saveAllAccountBalancesDTO(balanceApiDTO, userId);
	}
	
	@Override
	public ApiResult<Balance> saveAccountBalanceDTO(ApiResult<Balance> balanceApiDTO) {
		return accountDataDaoDTO.saveAccountBalanceDTO(balanceApiDTO);
	}

	@Override
	public ApiResult<DepositAddress> saveDepositAddressDTO(ApiResult<DepositAddress> depositAddressDTO) {
		return accountDataDaoDTO.saveDepositAddressDTO(depositAddressDTO);
	}

	@Override
	public ApiResult<List<OrderHistoryEntry>> saveAllOrderHistoryEntry(ApiResult<List<OrderHistoryEntry>> orderHistoryDTO) {
		return accountDataDaoDTO.saveAllOrderHistoryEntry(orderHistoryDTO);
	}

	@Override
	public ApiResult<List<OrderHistoryEntry>> saveAllOrderHistoryEntry(
			ApiResult<List<OrderHistoryEntry>> orderHistoryDTO, String currency) {
		return accountDataDaoDTO.saveAllOrderHistoryEntry(orderHistoryDTO, currency);
	}
	@Override
	public ApiResult<List<WithdrawalHistoryEntry>> saveAllWithdrawalHistory(
			ApiResult<List<WithdrawalHistoryEntry>> withdrawalHistoryDTO) {
		return accountDataDaoDTO.saveAllWithdrawalHistory(withdrawalHistoryDTO);
	}

	@Override
	public ApiResult<List<DepositHistoryEntry>> saveAllDepositHistory(ApiResult<List<DepositHistoryEntry>> depositHistoryDTO) {
		return accountDataDaoDTO.saveAllDepositHistory(depositHistoryDTO);
	}

}
