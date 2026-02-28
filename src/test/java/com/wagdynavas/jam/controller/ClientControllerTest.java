package com.wagdynavas.jam.controller;

import com.wagdynavas.jam.service.AuthenticationService;
import com.wagdynavas.jam.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthenticationService authenticationService;

    @MockitoBean
    private ClientService clientService;

    @Test
    void create_client_should_ok_status() throws Exception {
        mockMvc.perform(post(URI.create("/client"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"clientName\":\"Client Name\",\"clientRole\":\"OPERATIONS\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void create_client_should_return_bad_request() throws Exception {
        mockMvc.perform(post(URI.create("/client"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"clientName\":\"\",\"clientRole\":\"OPERATIONS\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
