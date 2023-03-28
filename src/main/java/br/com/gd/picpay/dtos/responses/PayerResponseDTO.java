package br.com.gd.picpay.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayerResponseDTO {

    private Long id;
    private String name;
    private String lastName;
    private BigDecimal balance;
}
