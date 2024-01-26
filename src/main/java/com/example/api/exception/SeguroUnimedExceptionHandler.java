package com.example.api.exception;


import com.example.api.domain.dto.ErrorView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestControllerAdvice
public class SeguroUnimedExceptionHandler extends ResponseEntityExceptionHandler {
    
    private final MessageSource messageSource;

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ErrorView handleNotFoundException(
            NotFoundException notFoundException,
            HttpServletRequest request) {
        return ErrorView.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .path(request.getServletPath())
                .error(HttpStatus.NOT_FOUND.name())
                .messages(Collections.singletonList("Nada encontrado!")).build();
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException(
            ConstraintViolationException ex, WebRequest request) {

        List<String> messages = new ArrayList<>(List.of());

        ex.getConstraintViolations().forEach(violation -> {
            messages.add(violation.getMessage());
        });

        ErrorView error = ErrorView.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.name())
                .messages(messages)
                .build();

        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({InvalidDataAccessApiUsageException.class})
    public ResponseEntity<Object> handleInvalidDataAccessApiUsageException(
            InvalidDataAccessApiUsageException ex, WebRequest request) {

        ErrorView error = ErrorView.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.name())
                .messages(Collections.singletonList("Passagem de parametros invalidos na requisicao."))
                .build();

        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({GlobalException.class})
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ErrorView handleGlobalException(
            GlobalException globalException,
            HttpServletRequest request) {
        return ErrorView.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .path(request.getServletPath())
                .error(HttpStatus.NOT_FOUND.name())
                .messages(Collections.singletonList(globalException.getMessage())).build();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            @NotNull HttpHeaders headers,
            @NotNull HttpStatusCode status,
            @NotNull WebRequest request) {

        ErrorView error = criarListaDeErros(ex.getBindingResult());

        return handleExceptionInternal(ex, error, headers, HttpStatus.BAD_REQUEST, request);
    }

    private ErrorView criarListaDeErros(BindingResult bindingResult){
        return ErrorView.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .path(bindingResult.getNestedPath())
                .messages(bindingResult.getFieldErrors().stream().map(error -> messageSource.getMessage(error, LocaleContextHolder.getLocale())).collect(Collectors.toList()))
                .error(HttpStatus.BAD_REQUEST.name()).build();
    }

    public static ErrorView handleErrorBadCrendentialUnauthorizadTokenException(){
        return ErrorView.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .path("/api/auth")
                .messages(Arrays.asList("Não foi possivel autenticar a requisição, verifique se o token está correto!"))
                .error(HttpStatus.UNAUTHORIZED.name()).build();
    }

}
