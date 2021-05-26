package io.github.tpalucki.bbpromo.model;

import lombok.Value;

import java.math.BigDecimal;
import java.util.Currency;

@Value
public class SetInfo {

    private String title;
    private String releaseYear;
    private Integer piecesAmount;
    private BigDecimal price;
    private Currency currency;
    private Integer number;

}
