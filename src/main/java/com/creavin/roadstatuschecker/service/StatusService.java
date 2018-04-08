package com.creavin.roadstatuschecker.service;

import org.springframework.stereotype.Service;

@Service
public interface StatusService {
    String roadStatus(final String roadId);
}
