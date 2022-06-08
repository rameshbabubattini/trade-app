package com.tradeapp.dao;

import com.tradeapp.bean.Trade;
import com.tradeapp.repository.TradeRepository;
import org.springframework.stereotype.Repository;

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
    public void get(String tradeId) {
        tradeRepository.getReferenceById(tradeId);
    }
}
