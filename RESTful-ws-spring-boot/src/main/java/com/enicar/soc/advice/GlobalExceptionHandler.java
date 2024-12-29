package com.enicar.soc.advice;


import com.enicar.soc.exceptions.*;
import com.enicar.soc.dto.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> handlerLawyerAlreadyExistException(AlreadyExistsException ex, HttpServletRequest request){
        // Création de l'objet de réponse d'erreur
        ErrorMessage errorResponse = new ErrorMessage(
                ex.getMessage(),
                HttpStatus.CONFLICT.value(),
                request.getRequestURI()
        );

        // Retourne la réponse d'erreur avec un code HTTP 409 (Conflit)
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException(BadRequestException ex, HttpServletRequest request) {
        ErrorMessage errorResponse = new ErrorMessage(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException ex, HttpServletRequest request) {
        ErrorMessage errorResponse = new ErrorMessage(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<ErrorMessage> handleMethodNotAllowedException(MethodNotAllowedException ex, HttpServletRequest request) {
        ErrorMessage errorResponse = new ErrorMessage(
                ex.getMessage(),
                HttpStatus.METHOD_NOT_ALLOWED.value(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }


    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity<ErrorMessage> handleTimeoutException(TimeoutException ex, HttpServletRequest request) {
        ErrorMessage errorResponse = new ErrorMessage(
                ex.getMessage(),
                HttpStatus.REQUEST_TIMEOUT.value(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.REQUEST_TIMEOUT);
    }






    // 404 - Ressource non trouvée
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        ErrorMessage errorResponse = new ErrorMessage(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    // 401 - Non autorisé
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorMessage> handleUnauthorizedException(UnauthorizedException ex, HttpServletRequest request) {
        ErrorMessage errorResponse = new ErrorMessage(
                ex.getMessage(),
                HttpStatus.UNAUTHORIZED.value(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    // 403 - Accès interdit
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorMessage> handleForbiddenException(ForbiddenException ex, HttpServletRequest request) {
        ErrorMessage errorResponse = new ErrorMessage(
                ex.getMessage(),
                HttpStatus.FORBIDDEN.value(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    // 409 - Conflit
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorMessage> handleConflictException(ConflictException ex, HttpServletRequest request) {
        ErrorMessage errorResponse = new ErrorMessage(
                ex.getMessage(),
                HttpStatus.CONFLICT.value(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }


    // 415 - Type de média non supporté
    @ExceptionHandler(UnsupportedMediaTypeException.class)
    public ResponseEntity<ErrorMessage> handleUnsupportedMediaTypeException(UnsupportedMediaTypeException ex, HttpServletRequest request) {
        ErrorMessage errorResponse = new ErrorMessage(
                ex.getMessage(),
                HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    // 500 - Erreur interne
    /*@ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ErrorMessage> handleInternalServerException(InternalServerException ex, HttpServletRequest request) {
        ErrorMessage errorResponse = new ErrorMessage(
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Validation des données (Bean Validation)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .reduce((m1, m2) -> m1 + ", " + m2)
                .orElse("Validation error");
        ErrorMessage errorResponse = new ErrorMessage(
                message,
                HttpStatus.BAD_REQUEST.value(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }*/

    // Exception générale
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGenericException(Exception ex, HttpServletRequest request) {
        ErrorMessage errorResponse = new ErrorMessage(
                "An unexpected error occurred: " + ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
