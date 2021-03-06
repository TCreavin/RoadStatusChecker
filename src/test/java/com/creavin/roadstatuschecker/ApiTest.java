package com.creavin.roadstatuschecker;

import com.creavin.roadstatuschecker.configuration.TflConfig;
import com.creavin.roadstatuschecker.model.RoadStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    @Autowired
    public TflConfig tflConfig;

    @Test
    public void testApi() {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "https://api.tfl.gov.uk/Road/A2?app_id=ea975850&app_key=91567412e58a914106e41789ee4931ba";
        ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }
    @Test
    public void testApiNegative() {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "https://api.tfl.gov.uk/Road/A249?app_id=ea975850&app_key=91567412e58a914106e41789ee4931ba";
        String status;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);
            status = response.getStatusCode().toString();
        } catch(HttpClientErrorException e){
            status = e.getStatusText();
        }
        assertThat(status, equalTo(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }


    @Test
    public void testApiWithMyConfig() {
        String resourceUrl = tflConfig.getApiAddress() + "/" + tflConfig.getApiRoad() + "/A2"
            + "?app_id=" + tflConfig.getAppId() + "&app_key=" + tflConfig.getAppKey();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

    }

    @Test
    public void testApiWithMyConfigAndEntity() {
        String resourceUrl = tflConfig.getApiAddress() + "/" + tflConfig.getApiRoad() + "/A2"
                + "?app_id=" + tflConfig.getAppId() + "&app_key=" + tflConfig.getAppKey();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<RoadStatus>> response = restTemplate.exchange(resourceUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<RoadStatus>>(){});
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().size(), 1);
        assertEquals(response.getBody().get(0).getDisplayName(), "A2");
    }

}
