package com.example.konwerterwalut.controller;

import com.example.konwerterwalut.dto.DataToConvert;
import com.example.konwerterwalut.service.ConvertRates;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ConvertRatesController {

    private final ConvertRates convertRates;

    @PostMapping("/convert")
    public ResponseEntity<Float> convert(
            @RequestBody DataToConvert data
    ){
        return ResponseEntity.ok(convertRates.calculateRate(data.currencyFrom.toUpperCase(), data.currencyTo.toUpperCase(), data.value));
    }
}
