package com.diegofernandes.springmongo.services.exception;

/**
 * Class responsible for performing exception handling when an object
 * is not located in the database
 *
 * @author  Diego Fernandes
 * @version 1.0.1
 * @since   2019-05-02
 */
public class ObjectNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ObjectNotFoundException(String message) {
        super(message);
    }
}
