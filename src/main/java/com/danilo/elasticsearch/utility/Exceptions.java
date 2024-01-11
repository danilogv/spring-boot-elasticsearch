package com.danilo.elasticsearch.utility;

import com.danilo.elasticsearch.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@ControllerAdvice
public class Exceptions {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> errorArgumentInvalid(MethodArgumentNotValidException ex) {
        if (Objects.nonNull(ex.getFieldError())) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            String msg = ex.getFieldError().getDefaultMessage();
            ErrorDTO error = new ErrorDTO(status.value(),msg);
            return new ResponseEntity<>(error,status);
        }
        else {
            ErrorDTO error = this.genericError();
            return new ResponseEntity<>(error,HttpStatusCode.valueOf(error.getStatus()));
        }
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorDTO> errorBusinessRule(ResponseStatusException ex) {
        HttpStatusCode status = ex.getStatusCode();
        String msg = ex.getReason();
        ErrorDTO error = new ErrorDTO(status.value(),msg);
        return new ResponseEntity<>(error,status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> errorServer(Exception ex) {
        ErrorDTO error = this.genericError();
        return new ResponseEntity<>(error,HttpStatusCode.valueOf(error.getStatus()));
    }

    private ErrorDTO genericError() {
        Integer status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String msg = "Internal server error.";
        ErrorDTO error = new ErrorDTO(status,msg);
        return error;
    }

}
