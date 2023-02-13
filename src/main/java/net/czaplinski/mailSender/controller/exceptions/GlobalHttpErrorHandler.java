package net.czaplinski.mailSender.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler {

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<Object> handlerMailAddressNotFoundException(AddressNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The mail address with the given ID does not exist");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getBindingResult().getFieldError().getDefaultMessage());
    }
}
