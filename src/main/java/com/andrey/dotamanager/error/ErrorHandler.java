package com.andrey.dotamanager.error;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Глобальный обработчик ошибок для контроллеров.
 */
@ControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        String responseBody = "Entity not found: " + ex.getMessage();
        log.error(ExceptionUtils.getStackTrace(ex));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        String responseBody = "An error occurred: " + ex.getMessage();
        log.error(ExceptionUtils.getStackTrace(ex));
        return ResponseEntity.internalServerError().body(responseBody);
    }
}