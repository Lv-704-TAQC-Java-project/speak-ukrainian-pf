package com.ita.edu.speakua.api.models.challenge;

import lombok.Data;

@Data
public class ErrorResponse {
    String message;
    int status;
}
