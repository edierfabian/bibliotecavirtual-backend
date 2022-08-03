package com.efhh.bibliotecavirtual.exception;

import com.efhh.bibliotecavirtual.model.ValidationError;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ErrorDetail {
    private String title;
    private int status;
    private String detail;
    private long timeStamp;

    /**
     * un mapa de errores de validación.
     * Ej.:
     *
     * "slug": [
     *  {"code": "NotNull", "message": "El slug es obligatorio."}
     * ]
     */
    private Map<String, List<ValidationError>> errors = new HashMap<>();
}
