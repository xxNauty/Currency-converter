package com.example.konwerterwalut.service;

import com.example.konwerterwalut.model.Currency;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;

@Service
@AllArgsConstructor
public class ConvertRates {

    private final GetCurrencyRate getCurrencyRate;

    public final float calculateRate(String fromCurrency, String toCurrency, float value) {
        if(!List.of(Currency.availableCurrencies).contains(fromCurrency)
                || !List.of(Currency.availableCurrencies).contains(toCurrency)
        ) {
            throw new InvalidParameterException("Niewspierana waluta");
        }
        if(fromCurrency.equals(toCurrency)) {
            throw new InvalidParameterException("Podane waluty są takie same");
        }
        if(value < 0) {
            throw new InvalidParameterException("Nie można przeliczać wartości mniejszych od zera");
        }
        float rate = 0;
        if(fromCurrency.equals(Currency.CURRENCY_DEFAULT)){
            rate = 1 / getCurrencyRate.getCurrencyRate(toCurrency).rates.get(0).mid;
        }
        else{
            rate = getCurrencyRate.getCurrencyRate(fromCurrency).rates.get(0).mid;
        }
        return value * rate;
    }
}
