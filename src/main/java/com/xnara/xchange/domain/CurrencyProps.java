package com.xnara.xchange.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "currencyprops")
public class CurrencyProps {

    @Id
    private Integer cpid;
    private String currencyCode;
    private Integer decimalPlaces;
    private String symbol;
}
