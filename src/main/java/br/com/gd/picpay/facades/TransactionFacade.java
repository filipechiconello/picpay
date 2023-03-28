package br.com.gd.picpay.facades;

import br.com.gd.picpay.dtos.requests.TransactionRequestDTO;
import br.com.gd.picpay.dtos.responses.TransactionResponseDTO;

public interface TransactionFacade {

    TransactionResponseDTO transaction(String token, TransactionRequestDTO transactionRequestDTO);
}
