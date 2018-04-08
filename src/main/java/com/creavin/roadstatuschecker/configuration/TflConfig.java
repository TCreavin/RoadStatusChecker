package com.creavin.roadstatuschecker.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="tfl")
public class TflConfig {

    private String apiAddress;
    private String apiRoad;
    private String appId;
    private String appKey;

    public String getApiAddress() {
        return apiAddress;
    }

    public void setApiAddress(String apiAddress) {
        this.apiAddress = apiAddress;
    }

    public String getApiRoad() {
        return apiRoad;
    }

    public void setApiRoad(String apiRoad) {
        this.apiRoad = apiRoad;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}
