package com.tv.program.parser;

import org.omg.SendingContext.RunTime;

public class ParsingException extends RuntimeException {
    public ParsingException(String message) {
        super(message);
    }
}
