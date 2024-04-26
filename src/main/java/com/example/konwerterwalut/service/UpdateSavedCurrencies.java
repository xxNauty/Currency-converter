package com.example.konwerterwalut.service;

import com.example.konwerterwalut.model.Currency;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class UpdateSavedCurrencies {

    @Value("${rates.filepath}")
    private String filePath;

    private final GetCurrencyRatesFromApi getCurrencyRates;

    public void updateSavedCurrencies() {
        List<Currency> rates = getCurrencyRates.getRates();
        String jsonRates = new Gson().toJson(rates);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(jsonRates);
            writer.close();
            System.out.println("Aktualne kursy walut zapisane do pliku");
        } catch (IOException e) {
            throw new RuntimeException("Błąd zapisu do pliku", e);
        }
    }

    //co godzinę
    @Scheduled(cron = "0 0 * * * *")
    public void update(){
        this.updateSavedCurrencies();
    }
}
