package com.wagdynavas.jam.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class APIError {
    private String id;
    private String timestamp;
    private String message;
    private int code;
    private Exception exception;
}
