package com.tradeapp.exception;

import lombok.Data;

@Data
public class TradeException extends RuntimeException {
    public TradeException() {
        super();
    }

    public TradeException(String message) {
        super(message);
    }

    public TradeException(String message, Throwable ex) {
        super(message, ex);
    }
}
