package com.example.konwerterwalut.request;

public class DataToConvertRequest {
    public String currencyFrom;
    public String currencyTo;
    public float value;

    public DataToConvertRequest(String currencyFrom, String currencyTo, float value) {
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.value = value;
    }
}
