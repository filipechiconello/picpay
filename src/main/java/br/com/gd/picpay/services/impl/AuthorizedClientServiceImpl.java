package br.com.gd.picpay.services.impl;

import br.com.gd.picpay.clients.AuthorizedClientApi;
import br.com.gd.picpay.dtos.responses.AuthorizedClientResponseDTO;
import br.com.gd.picpay.services.AuthorizedClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthorizedClientServiceImpl implements AuthorizedClientService {

    @Autowired
    private AuthorizedClientApi authorizedClientApi;

    @Override
    public AuthorizedClientResponseDTO getMessageFromClientApi() {
        log.info("getting message from client api");
        return authorizedClientApi.getMessage();
    }
}
