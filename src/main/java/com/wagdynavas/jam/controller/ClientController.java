package com.wagdynavas.jam.controller;

import com.wagdynavas.jam.dto.ClientRequest;
import com.wagdynavas.jam.error.APIErrorCode;
import com.wagdynavas.jam.error.JobApplicationException;
import com.wagdynavas.jam.model.Client;
import com.wagdynavas.jam.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody ClientRequest clientRequest) {

        if (StringUtils.isEmpty(clientRequest.clientName())) {
            String errorMessage = "Client name cannot be empty or Null";

            log.warn(errorMessage);
            throw new JobApplicationException(APIErrorCode.GENERAL_VALIDATION_FAILED, errorMessage);
        }
        return ResponseEntity.ok(clientService.createClient(clientRequest.clientName(), clientRequest.clientRole()));
    }

    @PatchMapping
    public ResponseEntity<String> updateClient(@RequestBody ClientRequest clientRequest) {
        return null;
    }
}
