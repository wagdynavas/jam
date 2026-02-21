package com.wagdynavas.jam.controller;

import com.wagdynavas.jam.dto.ClientRequest;
import com.wagdynavas.jam.model.Client;
import com.wagdynavas.jam.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody ClientRequest clientRequest) {
        return ResponseEntity.ok(clientService.createClient(clientRequest));
    }

    @PatchMapping
    public ResponseEntity<String> updateClient(@RequestBody ClientRequest clientRequest) {
        return null;
    }
}
