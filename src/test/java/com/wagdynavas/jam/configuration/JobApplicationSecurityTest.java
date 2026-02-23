package com.wagdynavas.jam.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class JobApplicationSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldDenyAccessWithoutApiKey() throws Exception {
        mockMvc.perform(get("/job/applications/001"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldDenyAccessWithWrongKeyApiKey() throws Exception {
        mockMvc.perform(get("/job/applications/001")
                                .header("X-Client-ID", "test")
                                .header("X-API-Key", "jam"))
                .andExpect(status().isUnauthorized());
    }
}
