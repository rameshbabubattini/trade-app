package com.tradeapp.job;

import com.tradeapp.dao.TradeDao;
import com.tradeapp.entity.Trade;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduledJobTests {

    @Autowired
    private TradeDao tradeDao;

    @Autowired
    private ScheduledJob scheduledJob;

    @Test
    public void testUpdateExpiredStatus() {
        scheduledJob.updateExpiredStatus();

        Optional<Trade> trade4 = tradeDao.findById("T4");
        Optional<Trade> trade5 = tradeDao.findById("T5");

        // After executing the job, assert that Expired flag is updated as Y for trades which are expired
        Assertions.assertTrue("Y".equals(trade4.get().getExpired()) && "Y".equals(trade5.get().getExpired()));
    }

}
