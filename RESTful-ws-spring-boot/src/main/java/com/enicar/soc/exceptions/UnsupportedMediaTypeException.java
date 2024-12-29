package com.enicar.soc.exceptions;
/*
* Si une requête contient un type de contenu (MIME) non supporté.
* Exemple : Envoyer un fichier XML à une API qui n'accepte que du JSON.
* */
public class UnsupportedMediaTypeException extends RuntimeException {
    public UnsupportedMediaTypeException(String message) {
        super(message);
    }
}
