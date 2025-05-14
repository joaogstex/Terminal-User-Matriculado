package br.com.ecoground.user.user_ecoground.exception;

public class MatriculadoException extends Exception {
    private String errorCode;

    public MatriculadoException(String msg, String errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
