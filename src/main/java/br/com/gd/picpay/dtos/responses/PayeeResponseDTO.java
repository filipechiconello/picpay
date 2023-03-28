package br.com.gd.picpay.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayeeResponseDTO {

    private Long id;
    private String name;
    private String lastName;
}
