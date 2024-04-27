package com.example.konwerterwalut.service;

import com.example.konwerterwalut.model.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCurrencyRatesFromApi {

    @Value("${rates.api.url}")
    public String ratesApiUrl;

    public List<Currency> getRates() {
        ArrayList<Currency> rates = new ArrayList<>();

        for (String currency : Currency.convertableCurrencies) {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            HttpEntity<String> httpEntity = new HttpEntity<>(headers);

            ResponseEntity<Currency> response = restTemplate.exchange(
                    ratesApiUrl + currency,
                    HttpMethod.GET,
                    httpEntity,
                    Currency.class
            );
            rates.add(response.getBody());
        }

        return rates;
    }
}
