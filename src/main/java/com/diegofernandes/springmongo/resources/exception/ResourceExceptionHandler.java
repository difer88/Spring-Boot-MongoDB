package com.diegofernandes.springmongo.resources.exception;

import com.diegofernandes.springmongo.services.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Class responsible for handling exceptions occurring in requests.
 *
 * @author  Diego Fernandes
 * @version 1.0.1
 * @since   2019-05-02
 */
@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){

        HttpStatus status = HttpStatus.NOT_FOUND;

        StandardError error = new StandardError(System.currentTimeMillis(), status.value(),
                "Não Encontrado", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(error);

    }

}
