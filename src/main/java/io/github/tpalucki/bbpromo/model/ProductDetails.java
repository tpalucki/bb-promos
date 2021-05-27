package io.github.tpalucki.bbpromo.model;

import lombok.Value;

import java.math.BigDecimal;
import java.util.Currency;

@Value
public class ProductDetails {

    private String title;
    private String catalogueTitle;
    private Integer catalogueNumber;
    private String seriesName;
    private Integer piecesAmount;
    private String releaseYear;
    private BigDecimal cataloguePrice;
    private BigDecimal lowestPrice;
    private String lowestPriceDetails;
    private Currency currency;
    private Integer recommendedAge;
}
