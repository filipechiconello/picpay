package br.com.gd.picpay.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDTO {

    private String result;
    private PayerResponseDTO payer;
    private PayeeResponseDTO payee;
    private BigDecimal value;
}