package com.danny.levievs.myspringreactivewebkeynotes.web;

import com.danny.levievs.myspringreactivewebkeynotes.exceptions.ResourceNotFoundException;
import com.danny.levievs.myspringreactivewebkeynotes.model.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;


@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public Mono<ResponseEntity<ApiErrorResponse>> handleNotFound(ResourceNotFoundException ex) {
        log.warn("Not found: {}", ex.getMessage());
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value())));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Mono<ResponseEntity<ApiErrorResponse>> handleBadRequest(IllegalArgumentException ex) {
        log.warn("Bad request: {}", ex.getMessage());
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value())));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ApiErrorResponse>> handleGeneral(Exception ex) {
        log.error("Unexpected error", ex);
        return Mono.just(ResponseEntity.internalServerError()
                .body(new ApiErrorResponse("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR.value())));
    }

    @Override
    protected Mono<ResponseEntity<Object>> handleWebExchangeBindException(WebExchangeBindException ex,
                                                                          HttpHeaders headers,
                                                                          HttpStatusCode status,
                                                                          ServerWebExchange exchange) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .collect(Collectors.joining("; "));
        log.warn("Validation failed: {}", message);
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponse(message, HttpStatus.BAD_REQUEST.value())));
    }

    @Override
    protected Mono<ResponseEntity<Object>> handleServerWebInputException(ServerWebInputException ex,
                                                                         HttpHeaders headers,
                                                                         HttpStatusCode status,
                                                                         ServerWebExchange exchange) {
        log.warn("Malformed request: {}", ex.getReason());
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponse("Malformed request body or parameters.", HttpStatus.BAD_REQUEST.value())));
    }
}