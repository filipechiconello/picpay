package br.com.gd.picpay.controllers;

import br.com.gd.picpay.dtos.requests.AuthenticationRequestDTO;
import br.com.gd.picpay.dtos.responses.AuthenticationResponseDTO;
import br.com.gd.picpay.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<AuthenticationResponseDTO> generateToken(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
        return new ResponseEntity<>(authenticationService.generateToken(authenticationRequestDTO), HttpStatus.OK);
    }
}