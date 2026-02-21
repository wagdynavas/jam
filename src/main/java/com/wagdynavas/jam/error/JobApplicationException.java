package com.wagdynavas.jam.error;

import com.wagdynavas.jam.dto.APIError;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.wagdynavas.jam.error.APIErrorCode.GENERAL;
@Data
@EqualsAndHashCode(callSuper = false)
public class JobApplicationException extends RuntimeException {

    protected APIError apiError;
    protected APIErrorCode apiErrorCode;

    protected String clientFacingError;

    public JobApplicationException(APIError error)
    {
        this.apiError = error;
    }

    public JobApplicationException(APIError error, String clientFacingError)
    {
        this.apiError = error;
        this.clientFacingError = clientFacingError;
    }

    /**
     * Common {@link JobApplicationException}
     *
     * @param apiErrorCode - {@link APIErrorCode}
     * @param message      - {@link String}
     */
    public JobApplicationException(APIErrorCode apiErrorCode, String message)
    {
        super(message);
        this.apiErrorCode = apiErrorCode;
    }

    /**
     * Common {@link JobApplicationException}
     *
     * @param apiErrorCode      - {@link APIErrorCode}
     * @param message           - {@link String}
     * @param clientFacingError - {@link String}
     */
    public JobApplicationException(APIErrorCode apiErrorCode, String message, String clientFacingError)
    {
        super(message);
        this.apiErrorCode = apiErrorCode;
        this.clientFacingError = clientFacingError;
    }

    /**
     * Common {@link JobApplicationException} that will use {@link APIErrorCode#getMessage()}
     * as the message of the exception.
     *
     * @param apiErrorCode - {@link APIErrorCode}
     */
    public JobApplicationException(APIErrorCode apiErrorCode)
    {
        super(apiErrorCode.getMessage());
        this.apiErrorCode = apiErrorCode;
    }

    public JobApplicationException(String message, Throwable throwable)
    {
        super(message, throwable);
        this.apiErrorCode = GENERAL;
    }
}
