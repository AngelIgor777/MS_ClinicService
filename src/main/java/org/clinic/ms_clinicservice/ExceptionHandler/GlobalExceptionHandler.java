package org.clinic.ms_clinicservice.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<HashMap<String, String>> handleUnauthorizedException(EntityNotFoundException ex) {
//        log.error(ex.getMessage());
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("error-message", ex.getMessage());
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }
}
