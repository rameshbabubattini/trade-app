package com.tradeapp.mapper;

import com.tradeapp.bean.Trade;
import com.tradeapp.dto.TradeDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TradeMapper {

    public static Trade mapToTrade(TradeDTO tradeDTO) {
        return Trade.builder()
                .tradeId(tradeDTO.getTradeId())
                .version(tradeDTO.getVersion())
                .counterPartyId(tradeDTO.getCounterPartyId())
                .maturityDate(tradeDTO.getMaturityDate())
                .createdDate(tradeDTO.getCreatedDate())
                .build();
    }
}
