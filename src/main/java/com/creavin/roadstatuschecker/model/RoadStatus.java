package com.creavin.roadstatuschecker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Road Status model object.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoadStatus {
    /**
     * The road display name.
     */
    private String displayName;
    /**
     * The severity status.
     */
    private String statusSeverity;
    /**
     * The description of the severity status.
     */
    private String statusSeverityDescription;
    /**
     * Messages associated to api call.
     */
    private String message;
    /**
     * the status code of http requests.
     */
    private int httpStatusCode;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getStatusSeverity() {
        return statusSeverity;
    }

    public void setStatusSeverity(String statusSeverity) {
        this.statusSeverity = statusSeverity;
    }

    public String getStatusSeverityDescription() {
        return statusSeverityDescription;
    }

    public void setStatusSeverityDescription(String statusSeverityDescription) {
        this.statusSeverityDescription = statusSeverityDescription;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }
}
