package com.creavin.roadstatuschecker.service.impl;

import com.creavin.roadstatuschecker.configuration.TflConfig;
import com.creavin.roadstatuschecker.model.RoadStatus;
import com.creavin.roadstatuschecker.service.StatusService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The status service used to find out the status of a road.
 */
public class StatusServiceImpl implements StatusService {

    /**
     * The rest template.
     */
    @Autowired
    private RestTemplate restTemplate;
    /**
     * The TFL Config.
     */
    @Autowired
    private TflConfig tflConfig;

    /**
     * Find the status for a given road.
     *
     * @param roadId the id/name of the road to lookup.
     * @return return a roadStatus.
     */
    @Override
    public final RoadStatus roadStatus(final String roadId) {
        List<RoadStatus> statuses = new ArrayList<>();
        String resourceUrl = tflConfig.getApiAddress() + "/"
                + tflConfig.getApiRoad() + "/" + roadId + "?app_id="
                + tflConfig.getAppId() + "&app_key=" + tflConfig.getAppKey();
        try {
            ResponseEntity<List<RoadStatus>> response =
                    restTemplate.exchange(resourceUrl, HttpMethod.GET,
                            null, new ParameterizedTypeReference<List<RoadStatus>>() {
                            });
            statuses = response.getBody();
        } catch (HttpClientErrorException e) {
            ObjectMapper om = new ObjectMapper();
            String responseBody = e.getResponseBodyAsString();
            try {
                RoadStatus r = om.readValue(responseBody, RoadStatus.class);
                statuses.add(r);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return statuses.get(0);
    }
}
