package com.wagdynavas.jam.error;

public enum APIErrorCode {
    // common errors on fi.span platform
    UNKNOWN(100000, "Error Occurred"),
    GENERAL(100001, "Error Occurred"),
    GENERAL_VALIDATION_FAILED(100002, "Validation Error"),
    TIMEOUT(100011, "Request took took too long and was interrupted"),
    // common HTTP Errors
    ACCESS_DENIED(100403, "Unauthorized"),
    NOT_FOUND(100404, "Not Found"),
    NOT_ALLOWED(100405, "Request Method Not Allowed"),
    NOT_SUPPORTED(100415, "Not Supported"),
    SERVICE_UNAVAILABLE(100503, "Service Unavailable");

    private final int code;
    private final String message;

    APIErrorCode(int code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public int getCode()
    {
        return code;
    }

    public String getMessage()
    {
        return message;
    }
}
