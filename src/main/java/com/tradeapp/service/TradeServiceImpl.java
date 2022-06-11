package com.tradeapp.service;

import com.tradeapp.constants.TradeConstants;
import com.tradeapp.entity.Trade;
import com.tradeapp.dao.TradeDao;
import com.tradeapp.dto.TradeDTO;
import com.tradeapp.exception.TradeException;
import com.tradeapp.mapper.TradeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

@Service
@Slf4j
public class TradeServiceImpl implements TradeService {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(TradeConstants.CREATE_DATE_FORMAT);
    private TradeDao tradeDao;

    public TradeServiceImpl(TradeDao tradeDao) {
        this.tradeDao = tradeDao;
    }

    @Override
    public void processTrade(TradeDTO tradeDTO) {
        log.debug("processing trade with tradeId: {}", tradeDTO.getTradeId());

        // Ignore if maturityDate is older than today's date
        if (isOldMaturityDate(tradeDTO.getMaturityDate())) {
            log.info("Ignoring trade: {}", tradeDTO);
            return;
        }

        Optional<Trade> existingTrade = tradeDao.findById(tradeDTO.getTradeId());
        if (existingTrade.isPresent()) { // If there is an existing trade

            // Ignore and throw exception if version is older than existing version
            if (tradeDTO.getVersion() < existingTrade.get().getVersion()) {
                log.info("Ignoring trade since version received is older than existing version. {}", tradeDTO);
                throw new TradeException("Version received is lower than the existing version for tradeId: " + tradeDTO.getTradeId());
            }

            save(tradeDTO);
        } else { // If it is a new trade
            save(tradeDTO);
        }
    }

    private Trade save(TradeDTO tradeDTO) {
        Trade trade = TradeMapper.mapToTrade(tradeDTO);

        // Set created Date to current date if input created date is null or blank
        if (ObjectUtils.isEmpty(trade.getCreatedDate())) {
            trade.setCreatedDate(DATE_FORMATTER.format(LocalDate.now()));
        }

        LocalDate maturityDate = LocalDate.parse(trade.getMaturityDate(), DATE_FORMATTER);
        LocalDate currentDate = LocalDate.now();

        // set expired to "N" if maturity date is older than current date
        if (maturityDate.compareTo(currentDate) < 0) {
            trade.setExpired("Y");
        } else {
            trade.setExpired("N");
        }
        return tradeDao.save(trade);
    }

    private boolean isOldMaturityDate(String maturityDate) {
        LocalDate localDate = LocalDate.parse(maturityDate, DATE_FORMATTER);
        LocalDate currentDate = LocalDate.now();
        return localDate.compareTo(currentDate) < 0;
    }
}
