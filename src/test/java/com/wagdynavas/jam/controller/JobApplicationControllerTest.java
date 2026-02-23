package com.wagdynavas.jam.controller;

import com.wagdynavas.jam.dto.JobApplicationResponse;
import com.wagdynavas.jam.model.Client;
import com.wagdynavas.jam.model.JobApplication;
import com.wagdynavas.jam.repository.ClientRepository;
import com.wagdynavas.jam.service.AuthenticationService;
import com.wagdynavas.jam.service.JobApplicationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JobApplicationController.class)
class JobApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private AuthenticationService authenticationService;

    @MockitoBean
    private JobApplicationService jobApplicationService;

    @MockitoBean
    private ClientRepository clientRepository;

    private static JobApplicationResponse jobApplicationResponse;


    @BeforeAll
    static void init() {
        JobApplication jobApplication =  JobApplication.builder()
                .jobTitle("Job Title Test")
                .companyName("Company Test")
                .build();

        jobApplicationResponse = JobApplicationResponse.builder()
                .jobApplications(singletonList(jobApplication))
                .build();
    }

    @Test
    void shouldReturnUserWhenExists() throws Exception {
        // Given
        Client client = Client.builder()
                .xClientId("test-id")
                .dev1Key("jam")
                .build();

        when(clientRepository.getByXClientId(anyString())).thenReturn(Optional.of(client));
        when(jobApplicationService.getJobApplicationById("001")).thenReturn(jobApplicationResponse);

        // When & Then
        mockMvc.perform(get("/job/applications/001")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"jobApplications\":[{\"jobTitle\":\"Job Title Test\", \"companyName\":\"Company Test\"}]}"));
    }
}
