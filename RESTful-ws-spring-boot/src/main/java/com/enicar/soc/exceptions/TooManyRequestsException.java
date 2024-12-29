package com.enicar.soc.exceptions;
/*
* Pour indiquer que l'utilisateur a dépassé le quota de requêtes (exemple : limitation par IP).
* */
public class TooManyRequestsException extends RuntimeException {
    public TooManyRequestsException(String message) {
        super(message);
    }
}

