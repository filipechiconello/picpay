package br.com.gd.picpay.exceptions;

public class PicPayException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PicPayException(String message) {
        super(message);
    }
}