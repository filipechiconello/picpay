package br.com.gd.picpay.exceptions;

import br.com.gd.picpay.exceptions.enums.UsersEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class UsersException extends PicPayException {

    private static final long serialVersionUID = -4589179341768493322L;

    public UsersException(UsersEnum error) {
        super(error.getMessage());
        this.error = error;
    }

    private final UsersEnum error;
}
