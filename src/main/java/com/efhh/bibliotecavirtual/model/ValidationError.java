package com.efhh.bibliotecavirtual.model;

import lombok.Data;

@Data
public class ValidationError {
    private String code;
    private String message;
}
