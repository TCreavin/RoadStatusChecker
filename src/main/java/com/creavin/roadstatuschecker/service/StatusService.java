package com.creavin.roadstatuschecker.service;

import com.creavin.roadstatuschecker.model.RoadStatus;
import org.springframework.stereotype.Service;

/**
 * Interface for communication with tfl road status api.
 */
@Service
public interface StatusService {
    /**
     * Lookup road status.
     * @param roadId the id of the road.
     * @return road status model.
     */
    RoadStatus roadStatus(String roadId);
}
