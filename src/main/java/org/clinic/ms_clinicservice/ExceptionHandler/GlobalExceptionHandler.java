package org.clinic.ms_clinicservice.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.clinic.ms_clinicservice.exception.EmptyResultException;
import org.clinic.ms_clinicservice.exception.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.HashMap;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class, EmptyResultException.class})
    public ResponseEntity<ErrorMessage> notFoundException(EntityNotFoundException ex) {
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("error-message", ex.getMessage());
        ErrorMessage errorMessage = ErrorMessage.builder().errorMessage(ex.getMessage()).build();

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ErrorMessage> existException(EntityExistsException ex) {
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("error-message", ex.getMessage());
        ErrorMessage errorMessage = ErrorMessage.builder().errorMessage(ex.getMessage()).build();
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

}
