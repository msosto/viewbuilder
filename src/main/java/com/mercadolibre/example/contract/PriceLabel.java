package com.mercadolibre.example.contract;

import com.mercadolibre.kisc.viewbuilder.Object;

/**
 * Created by abertolo on 03/01/18.
 */
public class PriceLabel implements Object {

    String integerPart;
    String decimalPart;
    String thousandSeparator;
    String currency;
    String decimalSeparator;

    public String getIntegerPart() {
        return integerPart;
    }

    public PriceLabel withIntegerPart(String integerPart) {
        this.integerPart = integerPart;
        return this;
    }

    public String getDecimalPart() {
        return decimalPart;
    }

    public PriceLabel withDecimalPart(String decimalPart) {
        this.decimalPart = decimalPart;
        return this;
    }

    public String getThousandSeparator() {
        return thousandSeparator;
    }

    public PriceLabel withThousandSeparator(String thousandSeparator) {
        this.thousandSeparator = thousandSeparator;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public PriceLabel withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public String getDecimalSeparator() {
        return decimalSeparator;
    }

    public PriceLabel withDecimalSeparator(String decimalSeparator) {
        this.decimalSeparator = decimalSeparator;
        return this;
    }
}
