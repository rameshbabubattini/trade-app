package com.tradeapp.dao;

import com.tradeapp.entity.Trade;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TradeDao {
    Trade save(Trade trade);

    void saveAll(Iterable<Trade> trades);

    void delete(String tradeId);

    Optional<Trade> findById(String tradeId);

    Collection<Trade> findAllExpiringTrades();
}
