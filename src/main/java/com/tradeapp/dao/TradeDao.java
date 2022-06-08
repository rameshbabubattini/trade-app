package com.tradeapp.dao;

import com.tradeapp.bean.Trade;

public interface TradeDao {
    Trade save(Trade trade);
    void delete(String tradeId);
    void get(String tradeId);
}
