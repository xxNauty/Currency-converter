package com.example.konwerterwalut.controller;

import com.example.konwerterwalut.request.DataToConvertRequest;
import com.example.konwerterwalut.response.ConvertRatesResponse;
import com.example.konwerterwalut.service.ConvertRates;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        consumes = "application/json",
        produces = "application/json"
)
@AllArgsConstructor
public class ConvertRatesController {

    private final ConvertRates convertRates;

    @PostMapping("/convert")
    public ResponseEntity<ConvertRatesResponse> convert(
            @RequestBody DataToConvertRequest data
    ) {
        float value = convertRates.calculateRate(
                data.currencyFrom.toUpperCase(),
                data.currencyTo.toUpperCase(),
                data.value
        );
        return ResponseEntity.ok(new ConvertRatesResponse(value));
    }
}
