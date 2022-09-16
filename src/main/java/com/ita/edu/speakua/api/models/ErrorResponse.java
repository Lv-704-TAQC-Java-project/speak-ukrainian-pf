package com.ita.edu.speakua.api.models;

import lombok.Data;

@Data
public class ErrorResponse {
    int status;
    String message;
}
