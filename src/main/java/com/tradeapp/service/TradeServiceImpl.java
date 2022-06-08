package com.tradeapp.service;

import com.tradeapp.bean.Trade;
import com.tradeapp.dao.TradeDao;
import com.tradeapp.dto.TradeDTO;
import com.tradeapp.mapper.TradeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TradeServiceImpl implements TradeService {

    private TradeDao tradeDao;

    public TradeServiceImpl(TradeDao tradeDao) {
        this.tradeDao = tradeDao;
    }
    @Override
    public void processTrade(TradeDTO tradeDTO) {
        Trade trade = TradeMapper.mapToTrade(tradeDTO);
        Trade updatedTrade = tradeDao.save(trade);
        log.info("Trade successfully processed. Trade: {}", updatedTrade);
    }
}
