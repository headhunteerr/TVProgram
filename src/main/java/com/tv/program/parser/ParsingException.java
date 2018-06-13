package com.tv.program.parser;

public class ParsingException extends RuntimeException {
    ParsingException(String message) {
        super(message);
    }

    ParsingException(String message, Throwable cause) {
        super(message, cause);
    }
}
