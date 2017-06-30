package com.assettrader.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.assettrader.dao.CoinDao;
import com.assettrader.model.coinmarket.Coin;
import com.assettrader.utils.DAOUtils;

@Repository
public class CoinDaoImpl implements CoinDao {

	Connection connection = null;
	PreparedStatement statement = null;

	@Override
	public Long getCoinMarketId(String marketName) {

		try {
			connection = DAOUtils.getConnection();
			String sql = "SELECT COIN_ID FROM COIN " + "WHERE MARKET_NAME = ?;";

			statement = connection.prepareStatement(sql);
			statement.setString(1, marketName);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return rs.getLong("COIN_ID");
			}
			return 0L;

		} catch (SQLException sex) {
			System.out.println("Error has occured while getting ID from coin markets " + sex.getMessage());
		}
		return 0L;

	}

	@Override
	public String getCoinMarketName(String marketName) {

		try {
			connection = DAOUtils.getConnection();
			String sql = "SELECT A.MARKET_NAME FROM COIN A " 
						+ "WHERE A.MARKET_NAME = ?;";

			statement = connection.prepareStatement(sql);
			statement.setString(1, marketName);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				String name = rs.getString("MARKET_NAME");
				return name;
			}

		} catch (SQLException sex) {
			System.out.println(
					"Error has occured while getting currency short name from coin markets " + sex.getMessage());
		}
		return "NOT FOUND";
	}

	@Override
	public String getMarketNameByCurrencyShortName(String currencyShortName) {
		try {
			connection = DAOUtils.getConnection();
			String sql = "SELECT A.MARKET_NAME FROM COIN A " + "WHERE A.MARKET_CURRENCY = ?;";

			statement = connection.prepareStatement(sql);
			statement.setString(1, currencyShortName);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				String name = rs.getString("MARKET_NAME");
				return name;
			}

		} catch (SQLException sex) {
			System.out.println(
					"Error has occured while getting currency short name from coin markets " + sex.getMessage());
		}
		return "NOT FOUND";
	}

	@Override
	public String getMarketName(String marketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCoinMarketNameLong(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCoinMarketCurrency(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCoinBaseCurrency(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCoinLogo(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getBidPriceLast(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getBidPriceRange(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getPriceCurrent(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getPriceOfPrimaryPair(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getMarketSummaryHigh(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getMarketSummaryLow(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getMarketSummaryVolume(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getMarketSummaryBaseVolume(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getMarketSummaryOpenBuyOrders(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getMarketSummaryOpenSellOrders(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getMarketHistoryOfBuyOrders(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getMarketHistoryOfSellOrders(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getMarketHistoryOfPriceOverRange(String coinMarketName, Date start, Date end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getCoinCreateDate(String coinMarketName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Coin> getCoinAllPairs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkCoinIsActive(String coinMarketName) {
		// TODO Auto-generated method stub
		return false;
	}

}
