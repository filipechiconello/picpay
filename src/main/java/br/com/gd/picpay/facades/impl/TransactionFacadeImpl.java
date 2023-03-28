package br.com.gd.picpay.facades.impl;

import br.com.gd.picpay.dtos.requests.TransactionRequestDTO;
import br.com.gd.picpay.dtos.responses.PayeeResponseDTO;
import br.com.gd.picpay.dtos.responses.PayerResponseDTO;
import br.com.gd.picpay.dtos.responses.TransactionResponseDTO;
import br.com.gd.picpay.entities.UsersEntity;
import br.com.gd.picpay.enums.Type;
import br.com.gd.picpay.exceptions.TransactionException;
import br.com.gd.picpay.exceptions.UsersException;
import br.com.gd.picpay.exceptions.enums.TransactionEnum;
import br.com.gd.picpay.exceptions.enums.UsersEnum;
import br.com.gd.picpay.facades.TransactionFacade;
import br.com.gd.picpay.services.AuthorizedClientService;
import br.com.gd.picpay.services.UsersService;
import br.com.gd.picpay.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TransactionFacadeImpl implements TransactionFacade {

    @Autowired
    private UsersService usersService;

    @Autowired
    private AuthorizedClientService authorizedClientService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public TransactionResponseDTO transaction(String token, TransactionRequestDTO transactionRequestDTO) {
        jwtUtil.validateToken(token);
        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();

        if (transactionRequestDTO.getValue().doubleValue() <= 0) {
            throw new UsersException(UsersEnum.BALANCE_CANNOT_BE_LESS_THAN_ZERO);
        }

        UsersEntity payer = usersService.findById(jwtUtil.getAllClaims(token).getId());
        if (payer.getType() == Type.LOGIST) {
            throw new TransactionException(TransactionEnum.NOT_AUTHORIZED);
        }

        payer.setBalance(payer.getBalance().subtract(transactionRequestDTO.getValue()));
        usersService.updateById(payer.getId(), payer);

        UsersEntity payee = usersService.findById(transactionRequestDTO.getPayee());
        payee.setBalance(payee.getBalance().add(transactionRequestDTO.getValue()));
        usersService.updateById(transactionRequestDTO.getPayee(), payee);

        transactionResponseDTO.setResult(authorizedClientService.getMessageFromClientApi().getMessage());

        transactionResponseDTO.setPayer(new PayerResponseDTO(
                payer.getId(),
                payer.getName(),
                payer.getLastName(),
                payer.getBalance()
        ));

        transactionResponseDTO.setPayee(new PayeeResponseDTO(
                payee.getId(),
                payee.getName(),
                payee.getLastName()
        ));

        transactionResponseDTO.setValue(transactionRequestDTO.getValue());

        return transactionResponseDTO;
    }
}