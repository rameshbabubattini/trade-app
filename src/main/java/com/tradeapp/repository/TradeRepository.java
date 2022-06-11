package com.tradeapp.repository;

import com.tradeapp.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade, String> {

    @Query("select t from Trade t where t.maturityDate < CURRENT_DATE and t.expired = 'N'")
    Collection<Trade> findAllExpiringTrades();
}
