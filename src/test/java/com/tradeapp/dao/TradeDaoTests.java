package com.tradeapp.dao;

import com.tradeapp.entity.Trade;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Collection;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TradeDaoTests {

    @Autowired
    private TradeDao tradeDao;

    @Test
    public void testExpiringTrades() {
        Collection<Trade> allExpiringTrades = tradeDao.findAllExpiringTrades();

        LocalDate currentDate = LocalDate.now();
        allExpiringTrades.forEach(trade -> {
            log.info("trade.getMaturityDate(): {} -> currentDate: {}", trade.getMaturityDate(), currentDate);
            Assertions.assertTrue(trade.getMaturityDate().compareTo(currentDate) < 0);
            Assertions.assertEquals("N", trade.getExpired());
        });
    }
}
