package com.ita.edu.speakua.api.models;

import lombok.Data;

@Data
public class ErrorResponse {
    String message;
    int status;
}
