package com.tv.program.parser;

/**
 * Exception lancé lors du parsage d'un objet
 */
public class ParsingException extends RuntimeException {
    ParsingException(String message) {
        super(message);
    }

    ParsingException(String message, Throwable cause) {
        super(message, cause);
    }
}
