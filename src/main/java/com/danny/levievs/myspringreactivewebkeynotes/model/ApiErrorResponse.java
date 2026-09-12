package com.danny.levievs.myspringreactivewebkeynotes.model;

import java.time.Instant;

public record ApiErrorResponse(String message, int status, Instant timestamp) {
        public ApiErrorResponse(String message, int status) {
            this(message, status, Instant.now());
        }
    }

