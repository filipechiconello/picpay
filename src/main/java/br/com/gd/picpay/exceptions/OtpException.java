package br.com.gd.picpay.exceptions;

import br.com.gd.picpay.enums.OtpEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class OtpException extends PicPayException {

    private static final long serialVersionUID = -4589179341768493322L;

    public OtpException(OtpEnum error) {
        super(error.getMessage());
        this.error = error;
    }

    private final OtpEnum error;
}
