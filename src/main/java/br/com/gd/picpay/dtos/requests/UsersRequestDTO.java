package br.com.gd.picpay.dtos.requests;

import br.com.gd.picpay.enums.Type;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersRequestDTO {

    private String name;
    private String lastName;
    private String document;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!#@$%&])(?=.*[0-9])(?=.*[a-z]).{6,15}$", message = "password.invalid")
    private String password;
    private String confirmPassword;
    private String email;
    private Type type;
    private BigDecimal balance;
    private int otp;
}