package com.enicar.soc.exceptions;

//Lorsqu'une ressource demandée (par ID ou autre critère) n'existe pas dans la base de données.

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
