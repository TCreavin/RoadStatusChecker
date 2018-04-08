package com.creavin.roadstatuschecker.service;

import org.springframework.stereotype.Service;

@Service
public interface StatusService {
    public String roadStatus(final String roadId);
}
