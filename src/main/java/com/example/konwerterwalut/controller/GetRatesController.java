package com.example.konwerterwalut.controller;

import com.example.konwerterwalut.model.Currency;
import com.example.konwerterwalut.response.GetRatesResponse;
import com.example.konwerterwalut.service.GetCurrencyRate;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class GetRatesController {

    private final GetCurrencyRate getCurrencyRate;

    @GetMapping("/rates")
    public ResponseEntity<GetRatesResponse> getCurrencies() {
        List<Currency> currencies = new ArrayList<>();
        for (String currency : Currency.convertableCurrencies) {
            currencies.add(getCurrencyRate.getCurrencyRate(currency));
        }
        return ResponseEntity.ok(new GetRatesResponse(currencies));
    }
}
