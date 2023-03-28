package br.com.gd.picpay.dtos.responses;

import br.com.gd.picpay.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersResponseDTO {

    private Long id;
    private String name;
    private String lastName;
    private String document;
    private String email;
    private Type type;
    private BigDecimal balance;
}
