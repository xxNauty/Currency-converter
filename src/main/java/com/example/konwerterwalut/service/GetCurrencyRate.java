package com.example.konwerterwalut.service;

import com.example.konwerterwalut.model.Currency;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

@Service
@RequiredArgsConstructor
public class GetCurrencyRate {

    @Value("${rates.filepath}")
    private String filePath;

    private final ReadDataFromFile readDataFromFile;

    public Currency getCurrencyRate(String code) {
        String rates = readDataFromFile.readDataFromFile(filePath);
        Currency[] currencies = new Gson().fromJson(rates, Currency[].class);

        for (Currency currency : currencies) {
            if(currency.code.equals(code)) {
                return currency;
            }
        }

        throw new InvalidParameterException("Nie znaleziono wartości dla podanej waluty");
    }
}
