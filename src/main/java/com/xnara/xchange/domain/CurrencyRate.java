package com.xnara.xchange.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity(name = "currencyrate")
public class CurrencyRate {

    @Id
    private Integer crid;
    private String currencyCode;
    @Column(name = "is_base")
    private Integer isBase;
    private BigDecimal rateModifier;
}
