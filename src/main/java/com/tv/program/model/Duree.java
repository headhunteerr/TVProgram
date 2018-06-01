package com.tv.program.model;

import java.util.concurrent.TimeUnit;

public class Duree {
    private TimeUnit unit;
    private long value;

    public Duree(TimeUnit unit, long value) {
        this.unit = unit;
        this.value = value;
    }

    public TimeUnit getUnit() {
        return unit;
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        switch (unit) {
            case HOURS:
                return value + " h";
            case MINUTES:
                return value + " mn";
            case SECONDS:
                return value + " s";
        }
        throw new RuntimeException("L'unité de temps fournie n'est pas valide");
    }
}
