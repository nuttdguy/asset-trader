package com.assettrader.api.bittrex;


import java.util.List;

import com.assettrader.api.bittrex.model.common.ApiResult;
import com.assettrader.api.bittrex.model.publicapi.Currency;
import com.assettrader.api.bittrex.model.publicapi.Market;
import com.assettrader.api.bittrex.model.publicapi.MarketHistoryEntry;
import com.assettrader.api.bittrex.model.publicapi.MarketSummary;
import com.assettrader.api.bittrex.model.publicapi.OrderBook;
import com.assettrader.api.bittrex.model.publicapi.OrderBookEntry;
import com.assettrader.api.bittrex.model.publicapi.Ticker;

import feign.Param;
import feign.RequestLine;

/**
 * @author contact@elbatya.de
 */
public interface BittrexPublicApi {

    @RequestLine("GET /public/getmarkets")
    ApiResult<List<Market>> getMarkets();

    @RequestLine("GET /public/getcurrencies ")
    ApiResult<List<Currency>> getCurrencies();

    @RequestLine("GET /public/getticker?market={market}")
    ApiResult<Ticker> getTicker(@Param("market") String market);

    @RequestLine("GET /public/getmarketsummaries")
    ApiResult<List<MarketSummary>> getMarketSummaries();

    @RequestLine("GET /public/getmarketsummary?market={market}")
    ApiResult<List<MarketSummary>> getMarketSummary(@Param("market") String market);

    @RequestLine("GET /public/getorderbook?type=both&market={market}&depth={depth}")
    ApiResult<OrderBook> getOrderBook(
            @Param("market") String market,
            @Param("depth") int depth
    );

    @RequestLine("GET /public/getorderbook?type=both&market={market}")
    ApiResult<OrderBook> getOrderBook(
            @Param("market") String market
    );

    @RequestLine("GET /public/getorderbook?type=sell&market={market}&depth={depth}")
    ApiResult<List<OrderBookEntry>> getOrderBookForSell(
            @Param("market") String market,
            @Param("depth") int depth
    );

    @RequestLine("GET /public/getorderbook?type=sell&market={market}")
    ApiResult<List<OrderBookEntry>> getOrderBookForSell(
            @Param("market") String market
    );

    @RequestLine("GET /public/getorderbook?type=buy&market={market}&depth={depth}")
    ApiResult<List<OrderBookEntry>> getOrderBookForBuy(
            @Param("market") String market,
            @Param("depth") int depth
    );

    @RequestLine("GET /public/getorderbook?type=buy&market={market}")
    ApiResult<List<OrderBookEntry>> getOrderBookForBuy(
            @Param("market") String market
    );


    @RequestLine("GET /public/getmarkethistory?market={market}")
    ApiResult<List<MarketHistoryEntry>> getMarketHistory(@Param("market") String market);
}
