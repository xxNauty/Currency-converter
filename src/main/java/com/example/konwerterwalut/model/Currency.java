package com.example.konwerterwalut.model;

import java.util.List;

public class Currency {

    public static final String CURRENCY_DEFAULT = "PLN";

    public static final String CURRENCY_USD = "USD";
    public static final String CURRENCY_EUR = "EUR";
    public static final String CURRENCY_CHF = "CHF";
    public static final String CURRENCY_GBP = "GBP";

    public static final String[] convertableCurrencies = {
            CURRENCY_USD,
            CURRENCY_EUR,
            CURRENCY_CHF,
            CURRENCY_GBP,
    };

    public String currency;
    public String code;
    public List<Rate> rates;
}
