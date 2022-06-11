package com.tradeapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trade")
public class Trade {
    @Id
    private String tradeId;
    private Integer version;
    private String counterPartyId;
    private String bookId;
    private String maturityDate;
    private String createdDate;
    private String expired = "N";
}
