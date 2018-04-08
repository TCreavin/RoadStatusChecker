package com.creavin.roadstatuschecker.service.impl;

import com.creavin.roadstatuschecker.configuration.TflConfig;
import com.creavin.roadstatuschecker.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class StatusServiceImpl implements StatusService {

    @Autowired
    public RestTemplate restTemplate;
    @Autowired
    public TflConfig tflConfig;

    @Override
    public String roadStatus(String roadId) {
        return roadId + " CALL REST " +
                tflConfig.getApiAddress();
    }
}
