package io.github.tpalucki.bbpromo.model;

import lombok.Value;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

@Value
public class PriceDetails {

    private String shopName;
    private List<PriceDetails.AdditionalInfo> additionalInfo;
    private BigDecimal price;
    private Currency currency;
    private boolean freeDelivery;

    @Value
    public static class AdditionalInfo {
        String info;
    }
}
