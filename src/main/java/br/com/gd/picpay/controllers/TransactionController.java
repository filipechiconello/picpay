package br.com.gd.picpay.controllers;

import br.com.gd.picpay.dtos.requests.TransactionRequestDTO;
import br.com.gd.picpay.dtos.responses.TransactionResponseDTO;
import br.com.gd.picpay.facades.TransactionFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    @Autowired
    private TransactionFacade transactionFacade;

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> transaction(@RequestHeader String token,
                                                              @RequestBody TransactionRequestDTO transactionRequestDTO) {
        return new ResponseEntity<>(transactionFacade.transaction(token, transactionRequestDTO), HttpStatus.OK);
    }
}
