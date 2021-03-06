package com.activedge.assessment.api;

import com.activedge.assessment.dtos.ErrorResponse;
import com.activedge.assessment.exceptions.InvalidDateException;
import com.activedge.assessment.exceptions.ResourceAlreadyExistsException;
import com.activedge.assessment.exceptions.ResourceNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException e){
        ErrorResponse response = new ErrorResponse("0001", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<Object> handleResourceAlreadyExists(ResourceAlreadyExistsException e){
        ErrorResponse response = new ErrorResponse("0002", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<Object> handleInvalidDateException (InvalidDateException e){
        ErrorResponse response = new ErrorResponse("0003", "Invalid date format, should be yyyy-MM-dd");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException (Exception e){
        ErrorResponse response = new ErrorResponse("0004", "Something has gone horribly wrong.");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
