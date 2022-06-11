package com.tradeapp.dao;

import com.tradeapp.entity.Trade;

import java.util.List;
import java.util.Optional;

public interface TradeDao {
    Trade save(Trade trade);
    void delete(String tradeId);
    Optional<Trade> findById(String tradeId);

    List<Trade> findAllExpiredTrades();
}
