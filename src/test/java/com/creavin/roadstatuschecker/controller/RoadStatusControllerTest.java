package com.creavin.roadstatuschecker.controller;

import com.creavin.roadstatuschecker.configuration.TflConfig;
import com.creavin.roadstatuschecker.model.RoadStatus;
import com.creavin.roadstatuschecker.service.StatusService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RoadStatusController.class)
public class RoadStatusControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private TflConfig tflConfig;

    @MockBean
    private StatusService statusService;

    @Test
    public void initStatusCheck() throws Exception {
        mockMvc.perform(get("/statusChecker")).andExpect(status().isOk());
    }

    @Test
    public void checkStatusPositive() throws Exception {
        RoadStatus rs = new RoadStatus();
        rs.setDisplayName("Test Road");
        rs.setStatusSeverity("Test");
        rs.setStatusSeverityDescription("Test Description");

        Mockito.when(statusService.roadStatus("TEST")).thenReturn(rs);

        mockMvc.perform(get("/checkStatus").param("road", "TEST"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("roadStatus", hasProperty("displayName",
                        equalTo("Test Road"))));
    }

    @Test
    public void checkStatusNegative() throws Exception {
        RoadStatus rs = new RoadStatus();
        rs.setMessage("Unavailable");

        Mockito.when(statusService.roadStatus("TEST2")).thenReturn(rs);

        mockMvc.perform(get("/checkStatus").param("road", "TEST2"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("roadStatus", hasProperty("message",
                        equalTo("Unavailable"))));
    }

    @Test
    public void returnError() throws Exception {
        Mockito.when(statusService.roadStatus("TEST")).thenReturn(null);
        mockMvc.perform(get("/checkStatus").param("road", "TEST"))
                .andExpect(model().attribute
                        ("error", "Sorry we cannot currently check the status of your road"));
    }

}
