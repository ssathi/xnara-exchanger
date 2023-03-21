package com.xnara.xchange.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExchangeRequest {

    @JsonProperty("curr_code")
    private String currCode;
    private BigDecimal amount;
    private String language;
    @JsonProperty("target_code")
    private String targetCode;
}
