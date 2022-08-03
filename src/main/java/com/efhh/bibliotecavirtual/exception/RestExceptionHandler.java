package com.efhh.bibliotecavirtual.exception;

import com.efhh.bibliotecavirtual.model.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestControllerAdvice
public class RestExceptionHandler extends Throwable {
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

}
