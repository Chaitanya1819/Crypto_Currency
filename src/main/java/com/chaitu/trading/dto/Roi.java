package com.chaitu.trading.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Roi {
    private String times;
    private String currency;
    private String percentage;
}
