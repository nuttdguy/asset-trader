package com.assettrader.model.view;

public class FavoriteCoinView {

	private Long userId;
	private Long coinId;
	private Long userCoinFavoriteId;
	private String logoUrl;
	private String marketCurrencyLong;
	private Double volume;
	private Double ask;
	private Double bid;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCoinId() {
		return coinId;
	}

	public void setCoinId(Long coinId) {
		this.coinId = coinId;
	}

	public Long getUserCoinFavoriteId() {
		return userCoinFavoriteId;
	}

	public void setUserCoinFavoriteId(Long userCoinFavoriteId) {
		this.userCoinFavoriteId = userCoinFavoriteId;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getMarketCurrencyLong() {
		return marketCurrencyLong;
	}

	public void setMarketCurrencyLong(String marketCurrencyLong) {
		this.marketCurrencyLong = marketCurrencyLong;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public Double getAsk() {
		return ask;
	}

	public void setAsk(Double ask) {
		this.ask = ask;
	}

	public Double getBid() {
		return bid;
	}

	public void setBid(Double bid) {
		this.bid = bid;
	}

}
