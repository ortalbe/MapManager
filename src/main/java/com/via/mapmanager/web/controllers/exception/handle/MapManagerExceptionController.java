package com.via.mapmanager.web.controllers.exception.handle;

import com.via.mapmanager.rest.datatype.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Date;

@ControllerAdvice
public class MapManagerExceptionController {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> exception(IllegalArgumentException exception) {
        return new ResponseEntity<ErrorResponse>(new ErrorResponse (HttpStatus.BAD_REQUEST,exception.getMessage(),new Date()),HttpStatus.BAD_REQUEST);
    }


}
