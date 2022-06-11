package com.tradeapp.repository;

import com.tradeapp.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade, String> {

    @Query("select * from trade where maturityDate < current_datetime")
    List<Trade> findAllExpiredTrades();
}
