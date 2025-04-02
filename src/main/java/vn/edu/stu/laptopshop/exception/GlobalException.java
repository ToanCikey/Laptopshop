package vn.edu.stu.laptopshop.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.edu.stu.laptopshop.controller.response.ResponseError;

import java.util.Date;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handleValidationException(MethodArgumentNotValidException e, HttpServletRequest request) {
        ResponseError errorResponse = new ResponseError();
        errorResponse.setTimestamp(new Date());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setMessage(e.getBindingResult().getFieldError().getDefaultMessage());
        return errorResponse;
    }

    @ExceptionHandler({MissingServletRequestParameterException.class, ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handleInValidationException(Exception e, HttpServletRequest request) {
        ResponseError errorResponse = new ResponseError();
        errorResponse.setTimestamp(new Date());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setMessage(e.getMessage());
        return errorResponse;
    }

    @ExceptionHandler({UsernameNotFoundException.class, ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError handleInValidationResourceException(Exception e, HttpServletRequest request) {
        ResponseError errorResponse = new ResponseError();
        errorResponse.setTimestamp(new Date());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setMessage(e.getMessage());
        return errorResponse;
    }

    @ExceptionHandler({InvalidDataException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseError handleValidationConFlictException(Exception e, HttpServletRequest request) {
        ResponseError errorResponse = new ResponseError();
        errorResponse.setTimestamp(new Date());
        errorResponse.setStatus(HttpStatus.CONFLICT.value());
        errorResponse.setError(HttpStatus.CONFLICT.getReasonPhrase());
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setMessage(e.getMessage());
        return errorResponse;
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseError handleValidationServerException(Exception e, HttpServletRequest request) {
        ResponseError errorResponse = new ResponseError();
        errorResponse.setTimestamp(new Date());
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setMessage(e.getMessage());
        return errorResponse;
    }

    @ExceptionHandler({BadCredentialsException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseError handleInvalidationLoginException(Exception e, HttpServletRequest request) {
        ResponseError errorResponse = new ResponseError();
        errorResponse.setTimestamp(new Date());
        errorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorResponse.setError(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setMessage(e.getMessage());
        return errorResponse;
    }
}
