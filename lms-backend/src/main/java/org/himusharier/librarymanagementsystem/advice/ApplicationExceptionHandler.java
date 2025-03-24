package org.himusharier.librarymanagementsystem.advice;

import org.himusharier.librarymanagementsystem.exception.BookAddingException;
import org.himusharier.librarymanagementsystem.exception.DuplicateEntryException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            response.put("code", HttpStatus.BAD_REQUEST.value());
            response.put(error.getField(), error.getDefaultMessage());
        });
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BookAddingException.class)
    public Map<String, Object> handleBookAddingException(BookAddingException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", HttpStatus.BAD_REQUEST.value());
        response.put("message", ex.getMessage());
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateEntryException.class)
    public Map<String, Object> handleDuplicateEntryException(DuplicateEntryException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", HttpStatus.BAD_REQUEST.value());
        response.put("message", ex.getMessage());
        return response;
    }

}
