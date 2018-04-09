package com.creavin.roadstatuschecker.service.impl;

import com.creavin.roadstatuschecker.configuration.TflConfig;
import com.creavin.roadstatuschecker.model.RoadStatus;
import com.creavin.roadstatuschecker.service.StatusService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatusServiceImplTest {

    @Autowired
    private StatusService statusService;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private TflConfig tflConfig;

    @Test
    public  void roadStatusPositive() {
        when(tflConfig.getApiAddress()).thenReturn("www.api.test.co.uk");
        when(tflConfig.getAppKey()).thenReturn("key");
        when(tflConfig.getAppId()).thenReturn("id");
        when(tflConfig.getApiRoad()).thenReturn("Road");

        RoadStatus rs = new RoadStatus();
        rs.setDisplayName("Service Test Road");
        rs.setStatusSeverity("Service Test");
        rs.setStatusSeverityDescription("Service Test Description");

        ArrayList<RoadStatus> statuses = new ArrayList<>();
        statuses.add(rs);

        ResponseEntity<List<RoadStatus>> responseEntity = new ResponseEntity<>(statuses, HttpStatus.ACCEPTED);
        when(restTemplate.exchange(anyString(),
                eq(HttpMethod.GET),
                eq(null),
                eq(new ParameterizedTypeReference<List<RoadStatus>>(){})
        )).thenReturn(responseEntity);

        RoadStatus result = statusService.roadStatus("Service Test Road");
        assertEquals(result.getStatusSeverity(), "Service Test");
    }

    @Test
    public void roadStatusNegative() {
        when(tflConfig.getApiAddress()).thenReturn("www.api.fail.co.uk");
        when(tflConfig.getAppKey()).thenReturn("key");
        when(tflConfig.getAppId()).thenReturn("id");
        when(tflConfig.getApiRoad()).thenReturn("Road");

        RoadStatus rs = new RoadStatus();
        rs.setMessage("This did not work");

        ArrayList<RoadStatus> statuses = new ArrayList<>();
        statuses.add(rs);

        ResponseEntity<List<RoadStatus>> responseEntity = new ResponseEntity<>(statuses, HttpStatus.NOT_FOUND);
        when(restTemplate.exchange(anyString(),
                eq(HttpMethod.GET),
                eq(null),
                eq(new ParameterizedTypeReference<List<RoadStatus>>(){})
        )).thenReturn(responseEntity);

        RoadStatus result = statusService.roadStatus("NA");
        assertEquals(result.getMessage(), "This did not work");
    }

    @Ignore
    @Test
    public void nullTest() {
        RoadStatus rs = statusService.roadStatus(null);
        System.out.println("null");
    }
}
