package com.enicar.soc.exceptions;

/*
* Si une méthode HTTP non supportée est utilisée pour une ressource.
* Exemple : Utiliser POST sur une ressource qui n'accepte que GET.
* */

public class MethodNotAllowedException extends RuntimeException {
    public MethodNotAllowedException(String message) {
        super(message);
    }
}
