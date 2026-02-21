package com.wagdynavas.jam.service;

import com.wagdynavas.jam.dto.ClientRequest;
import com.wagdynavas.jam.model.Client;
import com.wagdynavas.jam.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.wagdynavas.jam.util.JBUtils.generateKey;
import static com.wagdynavas.jam.util.JBUtils.generateUUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public Client createClient(ClientRequest clientRequest) {


        Client client = clientRequest.client();
        String xClientId = generateUUID();
        String key = generateKey(xClientId, 20);

        client.setXClientId(xClientId);
        client.setDev1Key(passwordEncoder.encode(key));

        clientRepository.save(clientRequest.client());

        return client;
    }
}
