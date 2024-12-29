package com.enicar.soc.exceptions;
/*
* Lorsque les données de la requête ne respectent pas les règles de validation métier ou structurelle.
* Exemple : Format d'email invalide, longueur de mot de passe insuffisante.
* */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
