package com.example.konwerterwalut.apiTest;

import com.example.konwerterwalut.request.DataToConvertRequest;
import com.example.konwerterwalut.response.ConvertRatesResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConvertRatesApiTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;

    @Test
    public void testCorrectConvertRatesFromPLN() throws URISyntaxException {
        URI uri = new URI("http://localhost:" + randomServerPort + "/convert");

        DataToConvertRequest data = new DataToConvertRequest("PLN", "EUR", 50);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<DataToConvertRequest> request = new HttpEntity<>(data, headers);
        ResponseEntity<ConvertRatesResponse> response = restTemplate.postForEntity(
                uri,
                request,
                ConvertRatesResponse.class
        );

        Assert.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        Assert.assertTrue(response.getBody().value > 0);
        Assert.assertTrue(response.getBody().value < 50);
    }

    @Test
    public void testCorrectConvertRatesToPLN() throws URISyntaxException {
        URI uri = new URI("http://localhost:" + randomServerPort + "/convert");

        DataToConvertRequest data = new DataToConvertRequest("EUR", "PLN", 50);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<DataToConvertRequest> request = new HttpEntity<>(data, headers);
        ResponseEntity<ConvertRatesResponse> response = restTemplate.postForEntity(uri, request, ConvertRatesResponse.class);

        Assert.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        Assert.assertTrue(response.getBody().value > 50);
    }

    @Test
    public void testIfLowerCaseCurrencyCodesAlsoWorks() throws URISyntaxException {
        URI uri = new URI("http://localhost:" + randomServerPort + "/convert");

        DataToConvertRequest data = new DataToConvertRequest("pln", "eUr", 50);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<DataToConvertRequest> request = new HttpEntity<>(data, headers);
        ResponseEntity<ConvertRatesResponse> response = restTemplate.postForEntity(uri, request, ConvertRatesResponse.class);

        Assert.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        Assert.assertTrue(response.getBody().value > 0);
        Assert.assertTrue(response.getBody().value < 50);
    }

    @Test
    public void testConvertFromCurrencyToTheSameCurrency() throws URISyntaxException {
        URI uri = new URI("http://localhost:" + randomServerPort + "/convert");

        DataToConvertRequest data = new DataToConvertRequest("PLN", "PLN", 50);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<DataToConvertRequest> request = new HttpEntity<>(data, headers);
        ResponseEntity<ConvertRatesResponse> response = restTemplate.postForEntity(uri, request, ConvertRatesResponse.class);

        Assert.assertEquals(HttpStatusCode.valueOf(500), response.getStatusCode());
    }

    @Test
    public void testConvertValueLessThanZero() throws URISyntaxException {
        URI uri = new URI("http://localhost:" + randomServerPort + "/convert");

        DataToConvertRequest data = new DataToConvertRequest("PLN", "EUR", -50);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<DataToConvertRequest> request = new HttpEntity<>(data, headers);
        ResponseEntity<ConvertRatesResponse> response = restTemplate.postForEntity(uri, request, ConvertRatesResponse.class);

        Assert.assertEquals(HttpStatusCode.valueOf(500), response.getStatusCode());
    }

    @Test
    public void testConvertFromUnsupportedCurrency() throws URISyntaxException {
        URI uri = new URI("http://localhost:" + randomServerPort + "/convert");

        DataToConvertRequest data = new DataToConvertRequest("NOR", "EUR", 50);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<DataToConvertRequest> request = new HttpEntity<>(data, headers);
        ResponseEntity<ConvertRatesResponse> response = restTemplate.postForEntity(uri, request, ConvertRatesResponse.class);

        Assert.assertEquals(HttpStatusCode.valueOf(500), response.getStatusCode());
    }

    @Test
    public void testConvertToUnsupportedCurrency() throws URISyntaxException {
        URI uri = new URI("http://localhost:" + randomServerPort + "/convert");

        DataToConvertRequest data = new DataToConvertRequest("PLN", "NOR", 50);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<DataToConvertRequest> request = new HttpEntity<>(data, headers);
        ResponseEntity<ConvertRatesResponse> response = restTemplate.postForEntity(uri, request, ConvertRatesResponse.class);

        Assert.assertEquals(HttpStatusCode.valueOf(500), response.getStatusCode());
    }

    @Test
    public void testConvertNotFromOrToDefaultCurrency() throws URISyntaxException {
        URI uri = new URI("http://localhost:" + randomServerPort + "/convert");

        DataToConvertRequest data = new DataToConvertRequest("EUR", "USD", 50);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<DataToConvertRequest> request = new HttpEntity<>(data, headers);
        ResponseEntity<ConvertRatesResponse> response = restTemplate.postForEntity(uri, request, ConvertRatesResponse.class);

        Assert.assertEquals(HttpStatusCode.valueOf(500), response.getStatusCode());
        System.out.println(response);
    }
}
