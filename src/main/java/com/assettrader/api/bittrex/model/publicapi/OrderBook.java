package com.assettrader.api.bittrex.model.publicapi;

import java.util.List;

public class OrderBook {

    private List<OrderBookEntry> buy;
    private List<OrderBookEntry> sell;

    public List<OrderBookEntry> getBuy() {
        return buy;
    }

    public void setBuy(List<OrderBookEntry> buy) {
        this.buy = buy;
    }

    public List<OrderBookEntry> getSell() {
        return sell;
    }

    public void setSell(List<OrderBookEntry> sell) {
        this.sell = sell;
    }
    
}
