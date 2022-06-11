package com.tradeapp.mapper;

import com.tradeapp.entity.Trade;
import com.tradeapp.dto.TradeDTO;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;

@UtilityClass
public class TradeMapper {

    public static Trade mapToTrade(TradeDTO tradeDTO) {
        return Trade.builder()
                .tradeId(tradeDTO.getTradeId())
                .version(tradeDTO.getVersion())
                .counterPartyId(tradeDTO.getCounterPartyId())
                .bookId(tradeDTO.getBookId())
                .maturityDate(tradeDTO.getMaturityDate())
                .createdDate(tradeDTO.getCreatedDate())
                .build();
    }
}
