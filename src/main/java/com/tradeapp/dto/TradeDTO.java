package com.tradeapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeDTO {
    @NotEmpty
    private String tradeId;
    private Integer version;
    private String counterPartyId;
    private String bookId;
    private String maturityDate;
    private String createdDate;
}
