package com.tradeapp.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trade")
public class Trade {
    @Id
    private String tradeId;
    private String version;
    private String counterPartyId;
    private String bookId;
    private String maturityDate;
    private String createdDate;
    private String expired;
}
