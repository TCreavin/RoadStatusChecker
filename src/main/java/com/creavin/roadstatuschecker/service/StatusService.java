package com.creavin.roadstatuschecker.service;

import com.creavin.roadstatuschecker.model.RoadStatus;
import org.springframework.stereotype.Service;

/**
 * Interface for communication with tfl road status api.
 */
@Service
public interface StatusService {
    RoadStatus roadStatus(final String roadId);
}
