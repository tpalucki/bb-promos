package io.github.tpalucki.bbpromo.model;

import lombok.Value;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

@Value
public class PriceDetails {

    private String shopName;
    private List<String> additionalInfo;
    private BigDecimal price;
    private Currency currency;
    private boolean freeDelivery;
}
