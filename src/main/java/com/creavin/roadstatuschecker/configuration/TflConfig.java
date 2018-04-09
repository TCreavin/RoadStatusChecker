package com.creavin.roadstatuschecker.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class pulling in properties with tfl prefix.
 */
@Configuration
@ConfigurationProperties(prefix="tfl")
public class TflConfig {
    /**
     * Address of the api resource.
     */
    private String apiAddress;
    /**
     * The route for the road api.
     */
    private String apiRoad;
    /**
     * The developers app id.
     */
    private String appId;
    /**
     * The developers app key.
     */
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
