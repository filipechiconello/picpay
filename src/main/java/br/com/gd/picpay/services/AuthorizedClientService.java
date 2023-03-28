package br.com.gd.picpay.services;

import br.com.gd.picpay.dtos.responses.AuthorizedClientResponseDTO;

public interface AuthorizedClientService {

    AuthorizedClientResponseDTO getMessageFromClientApi();
}
