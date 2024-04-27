package com.example.konwerterwalut.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ReadDataFromFile {

    public String readDataFromFile(String filePath) {
        String data;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            data = reader.readLine();
            reader.close();

            if (data == null) {
                throw new RuntimeException("Wystąpił błąd przy odczycie pliku");
            }
        } catch (IOException e) {
            throw new RuntimeException("Wystąpił błąd przy odczycie pliku", e);
        }
        return data;
    }
}
