package br.com.ecoground.user.user_ecoground.common.exception;

public class ErrorResponse {
    private String errorCode;
    private String message;
    
    public ErrorResponse(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
