package com.creavin.roadstatuschecker;

import com.creavin.roadstatuschecker.service.StatusService;
import com.creavin.roadstatuschecker.service.impl.StatusServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Spring boot app declaration.
 */
@SpringBootApplication
public class RoadStatusCheckerApplication {

    /**
     * Start the app.
     * @param args any app arguments.
     */
    public static void main(final String[] args) {
        SpringApplication.run(RoadStatusCheckerApplication.class, args);
    }

    /**
     * Reusable rest template.
     * @param builder the builder.
     * @return the rest template.
     */
    @Bean
    public RestTemplate restTemplate(final RestTemplateBuilder builder) {
        return builder.build();
    }

    /**
     * The road status service.
     * @return service to lookup road status.
     */
    @Bean
    public StatusService statusService() {
        return new StatusServiceImpl();
    }
}
