package com.tradeapp.service;

import com.tradeapp.dto.TradeDTO;
import org.springframework.stereotype.Service;

public interface TradeService {
    void processTrade(TradeDTO tradeDTO);
}
