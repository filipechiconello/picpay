package br.com.gd.picpay.services;


import br.com.gd.picpay.dtos.requests.AuthenticationRequestDTO;
import br.com.gd.picpay.dtos.responses.AuthenticationResponseDTO;

public interface AuthenticationService {

    AuthenticationResponseDTO generateToken(AuthenticationRequestDTO authenticationRequestDTO);
}
