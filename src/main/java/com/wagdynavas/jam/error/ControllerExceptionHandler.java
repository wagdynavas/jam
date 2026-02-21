package com.wagdynavas.jam.error;

import com.wagdynavas.jam.dto.APIError;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.print.attribute.standard.Severity;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
    /**
     * "Black hole". If nothing else could handle this error...
     */
    @ExceptionHandler(Throwable.class)
    private ResponseEntity<APIError> handleException(Throwable e, HttpServletRequest request)
    {
        APIErrorCode errorCode = APIErrorCode.UNKNOWN;
        APIError error = APIError.builder()
                .code(errorCode.getCode())
                .message(e.getMessage())
                .build();
        logErrorUnknown(error, request);

        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(error);
    }

    @ExceptionHandler(JobApplicationException.class)
    private ResponseEntity<APIError> handleJobApplicationException(JobApplicationException e, HttpServletRequest request)
    {
        APIErrorCode errorCode = e.getApiErrorCode();
        APIError error = APIError.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
        logErrorknown(e, error, request);

        return ResponseEntity.status(BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(error);
    }

    @ExceptionHandler(BadCredentialsException.class)
    private ResponseEntity<APIError> handleBadCredentialsException(BadCredentialsException e,  HttpServletRequest request) {

        APIError error = APIError.builder()
                .message(e.getMessage())
                .build();

        logErrorknown(e, error, request);

        return ResponseEntity.status(UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(error);
    }

    private void logErrorUnknown(APIError error, HttpServletRequest request)
    {
        log.warn(format("Unclassified error: %s:%s; Exception data: %s",
                error.getId(),
                error.getMessage(),
                getPrettyUrl(request)));
    }

    private void logErrorknown(Throwable e, APIError error, HttpServletRequest request)
    {
        log.warn(format("Classified error: %s, %s: %s", e.getClass().getSimpleName(),
                error.getMessage(), getPrettyUrl(request)));
    }

    private String getPrettyUrl(HttpServletRequest request)
    {
        StringBuilder buffer = new StringBuilder();

        buffer.append(request.getMethod())
                .append(" ")
                .append(request.getRequestURL());

        if (request.getQueryString() != null)
        {
            buffer.append("?").append(request.getQueryString());
        }

        return buffer.toString();
    }
}
