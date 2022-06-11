package com.tradeapp.dao;

import com.tradeapp.entity.Trade;
import com.tradeapp.repository.TradeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TradeDaoImpl implements TradeDao {

    private TradeRepository tradeRepository;

    public TradeDaoImpl(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    @Override
    public Trade save(Trade trade) {
        return tradeRepository.save(trade);
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
    public List<Trade> findAllExpiredTrades() {
        return tradeRepository.findAllExpiredTrades();
    }
}
