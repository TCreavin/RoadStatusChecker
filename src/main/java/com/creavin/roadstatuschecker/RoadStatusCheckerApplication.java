package com.creavin.roadstatuschecker;

import com.creavin.roadstatuschecker.service.StatusService;
import com.creavin.roadstatuschecker.service.impl.StatusServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RoadStatusCheckerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoadStatusCheckerApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public StatusService statusService() {
        return new StatusServiceImpl();
    }
}
