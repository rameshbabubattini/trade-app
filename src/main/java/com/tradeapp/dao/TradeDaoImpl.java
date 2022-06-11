package com.tradeapp.dao;

import com.tradeapp.entity.Trade;
import com.tradeapp.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class TradeDaoImpl implements TradeDao {

    private TradeRepository tradeRepository;

    @Autowired
    public TradeDaoImpl(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    @Override
    public Trade save(Trade trade) {
        return tradeRepository.save(trade);
    }

    @Override
    public void saveAll(Iterable<Trade> trades) {
        tradeRepository.saveAll(trades);
    }

    @Override
    public void delete(String tradeId) {
        tradeRepository.deleteById(tradeId);
    }

    @Override
    public Optional<Trade> findById(String tradeId) {
        return tradeRepository.findById(tradeId);
    }

    @Override
    public Collection<Trade> findAllExpiringTrades() {
        return tradeRepository.findAllExpiringTrades();
    }
}
