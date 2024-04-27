package com.example.konwerterwalut.apiTest;

import com.example.konwerterwalut.model.Currency;
import com.example.konwerterwalut.response.GetRatesResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetRatesApiTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;

    @Test
    public void testCorrectGetRates() throws URISyntaxException {
        URI uri = new URI("http://localhost:" + randomServerPort + "/rates");

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        ResponseEntity<GetRatesResponse> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                GetRatesResponse.class
        );

        Assert.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(response.getBody().currencies.size(), 4);
        for (Currency currency : response.getBody().currencies) {
            Assert.assertTrue(currency.rates.get(0).mid != 0);
        }
    }
}
