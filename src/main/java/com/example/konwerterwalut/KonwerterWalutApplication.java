package com.example.konwerterwalut;

import com.example.konwerterwalut.service.UpdateSavedCurrencies;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KonwerterWalutApplication {

    public static void main(String[] args) {
        SpringApplication.run(KonwerterWalutApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            UpdateSavedCurrencies updateSavedCurrencies
    ) {
        return args -> {
            updateSavedCurrencies.updateSavedCurrencies();
        };
    }


}
