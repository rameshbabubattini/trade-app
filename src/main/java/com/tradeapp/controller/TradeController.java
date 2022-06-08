package com.tradeapp.controller;

import com.tradeapp.dto.TradeDTO;
import com.tradeapp.service.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TradeController {

    private TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @PostMapping(value = "/sendTrade", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendTrade(@RequestBody TradeDTO tradeDTO) {
        log.info("Trade received: {}", tradeDTO);
        tradeService.processTrade(tradeDTO);
        return ResponseEntity.ok("Trade processed successfully");
    }

}
