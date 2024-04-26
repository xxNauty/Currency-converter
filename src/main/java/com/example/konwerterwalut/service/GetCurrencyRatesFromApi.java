package com.example.konwerterwalut.service;

import com.example.konwerterwalut.model.Currency;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@AllArgsConstructor
public class GetCurrencyRatesFromApi {

    public static final List<String> supportedCurrencies = List.of("USD", "EUR", "GBP", "CHF");

    public List<Currency> getRates(){
        final String remoteApiUrl = "http://api.nbp.pl/api/exchangerates/rates/A/";
        ArrayList<Currency> rates = new ArrayList<>();

        for (String currency : supportedCurrencies) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            HttpEntity<String> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<Currency> response = restTemplate.exchange(
                    remoteApiUrl + currency,
                    HttpMethod.GET,
                    httpEntity,
                    Currency.class
            );
            rates.add(response.getBody());
        }

        return rates;
    }
}
