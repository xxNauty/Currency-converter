package com.example.konwerterwalut.response;

import com.example.konwerterwalut.model.Currency;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class GetRatesResponse {
    public List<Currency> currencies;
}
