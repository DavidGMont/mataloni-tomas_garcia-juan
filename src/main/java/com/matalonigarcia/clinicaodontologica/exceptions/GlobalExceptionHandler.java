package com.matalonigarcia.clinicaodontologica.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> procesarBadRequestException(BadRequestException exception) {
        Map<String, String> mensaje = new HashMap<>();
        mensaje.put("message", exception.getMessage());
        return mensaje;
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> procesarResourceNotFoundException(ResourceNotFoundException exception) {
        Map<String, String> mensaje = new HashMap<>();
        mensaje.put("message", exception.getMessage());
        return mensaje;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> procesarNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> mensaje = new HashMap<>();
        exception.getBindingResult()
                .getAllErrors()
                .forEach((error) -> {
                    String campo = ((FieldError) error).getField();
                    String mensajeDeError = error.getDefaultMessage();
                    mensaje.put(campo, mensajeDeError);
                });
        return mensaje;
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> procesarTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        Map<String, String> mensaje = new HashMap<>();
        mensaje.put(exception.getPropertyName(), "🛑 ¡Atención! El tipo de dato que pasaste no puede ser convertido "
                .concat("al requerido, puede que hayas pasado una letra en donde se necesitaba un número, ")
                .concat("chequea eso."));
        return mensaje;
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> procesarHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        Map<String, String> mensaje = new HashMap<>();
        mensaje.put("message", "🛑 ¡Atención! El cuerpo de tu solicitud no pudo ser encontrado, por favor envía "
                .concat("la información nuevamente."));
        return mensaje;
    }

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> procesarConstraintViolationException(ConstraintViolationException exception) {
        Map<String, String> mensaje = new HashMap<>();
        exception.getConstraintViolations().forEach((error) -> {
            String campo = error.getPropertyPath().toString();
            String mensajeError = error.getMessage();
            mensaje.put(campo, mensajeError);
        });
        return mensaje;
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> procesarDataIntegrityViolationException(DataIntegrityViolationException exception) {
        Map<String, String> mensaje = new HashMap<>();
        mensaje.put("message", "🛑 ¡Atención! Violación de restricción de integridad referencial; "
                .concat("en otras palabras, estás realizando transacciones con registros que no han sido creados ")
                .concat("o que están siendo utilizados por otros registros."));
        return mensaje;
    }
}
