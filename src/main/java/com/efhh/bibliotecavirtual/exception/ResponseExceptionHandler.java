package com.efhh.bibliotecavirtual.exception;

import com.efhh.bibliotecavirtual.model.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

//@ControllerAdvice
//@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

   /* @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> manejarTodasExcepciones(Exception ex, WebRequest request){
        ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ExceptionResponse>(er, HttpStatus.INTERNAL_SERVER_ERROR);
    }*/

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ErrorDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException manve) {
        ErrorDetail errorDetail = new ErrorDetail();

        // llenamos los detalles del error
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetail.setTitle("Error de validación");
        errorDetail.setDetail("El formulario tiene algunos errores de validación");

        // agregamos los errores de validación por cada campo
        List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();

        for (FieldError fe : fieldErrors) {
            List<ValidationError> validationErrors = errorDetail.getErrors().get(fe.getField());

            if (validationErrors == null) {
                validationErrors = new ArrayList<>();
                errorDetail.getErrors().put(fe.getField(), validationErrors);
            }
            ValidationError validationError = new ValidationError();
            validationError.setCode(fe.getCode());
            validationError.setMessage(messageSource.getMessage(fe, Locale.getDefault()));

            validationErrors.add(validationError);
        }
        return errorDetail;
    }





    @ExceptionHandler(ModeloNotFoundException.class)
    public ResponseEntity<ExceptionResponse> manejarModeloNotFoundException(ModeloNotFoundException ex, WebRequest request){
        ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ExceptionResponse>(er, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SFileNotFoundException.class)
    public ResponseEntity<ExceptionResponse> manejarSFileNotFoundException(SFileNotFoundException ex, WebRequest request){
        ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ExceptionResponse>(er, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String mensaje = ex.getBindingResult().getAllErrors().stream().map(e -> {
            return e.getDefaultMessage().toString().concat(", ");
        }).collect(Collectors.joining());

        ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(), mensaje, request.getDescription(false));
        return new ResponseEntity<Object>(er, HttpStatus.BAD_REQUEST);
    }
}
