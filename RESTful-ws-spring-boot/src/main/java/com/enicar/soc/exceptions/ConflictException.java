package com.enicar.soc.exceptions;
/*
* Lorsque l'opération entraîne un conflit dans les données.
Exemple : Création d'une ressource avec un identifiant ou un champ déjà existant.
* */
public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
