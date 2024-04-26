package com.example.konwerterwalut.controller;

import com.example.konwerterwalut.model.Currency;
import com.example.konwerterwalut.service.GetCurrencyRate;
import com.example.konwerterwalut.service.GetCurrencyRatesFromApi;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rates")
@AllArgsConstructor
public class GetRates {

    private final GetCurrencyRate getCurrencyRate;

    @GetMapping("/get")
    public ResponseEntity<List<Currency>> getCurrencies() {
        List<Currency> response = new ArrayList<>();
        for (String currency : Currency.availableCurrencies){
            response.add(getCurrencyRate.getCurrencyRate(currency));
        }
        return ResponseEntity.ok(response);
    }
}
