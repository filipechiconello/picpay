package br.com.gd.picpay.exceptions;

import br.com.gd.picpay.enums.OtpEnum;
import br.com.gd.picpay.exceptions.enums.TransactionEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class TransactionException extends PicPayException {

    private static final long serialVersionUID = -4589179341768493322L;

    public TransactionException(TransactionEnum error) {
        super(error.getMessage());
        this.error = error;
    }

    private final TransactionEnum error;
}
