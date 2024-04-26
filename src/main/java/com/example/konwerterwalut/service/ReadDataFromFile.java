package com.example.konwerterwalut.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ReadDataFromFile {

    public String readDataFromFile(String fileName) {
        String data = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            data = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Wystąpił błąd przy odczycie pliku", e);
        }
        return data;
    }
}
