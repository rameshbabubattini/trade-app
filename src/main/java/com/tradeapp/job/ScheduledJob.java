package com.tradeapp.job;

import com.tradeapp.dao.TradeDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ScheduledJob {

    @Autowired
    private TradeDao tradeDao;


    @Scheduled(cron = "0/30 * * * * *")
//    @Scheduled(cron = "* * 5 * * *")
    public void updateExpiredStatus() {
        log.info("Executing schedule for updateExpiredStatus.. ");
//        tradeDao.findAllExpiredTrades();
    }
}
