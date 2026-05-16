package com.scalers.productserviceebatch.advice;

import com.scalers.productserviceebatch.dtos.ErrorDTO;
import com.scalers.productserviceebatch.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleProductNotFoundException(ProductNotFoundException ex) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(ex.getMessage());

        //Local variable 'errorResponseEntity' is redundant
        return new ResponseEntity<>(
                errorDTO,
                HttpStatus.NOT_FOUND
        );
    }
}