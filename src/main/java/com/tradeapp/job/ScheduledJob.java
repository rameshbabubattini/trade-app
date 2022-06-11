package com.tradeapp.job;

import com.tradeapp.dao.TradeDao;
import com.tradeapp.entity.Trade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@Slf4j
public class ScheduledJob {

    @Autowired
    private TradeDao tradeDao;

    /**
     * Job to update expired flag to N if the maturity date is crossed.
     * Running this job Daily once would be enough
     */
    @Scheduled(cron = "0/30 * * * * *")
//    @Scheduled(cron = "* * 0 * * *")
    public void updateExpiredStatus() {
        log.info("Executing schedule for updateExpiredStatus.. ");
        Collection<Trade> allExpiredTrades = tradeDao.findAllExpiringTrades();

        allExpiredTrades.stream().forEach(trade -> {
            trade.setExpired("Y");
        });

        tradeDao.saveAll(allExpiredTrades);
        log.info("Updated {} no of records as expired.", allExpiredTrades.size());
    }
}
