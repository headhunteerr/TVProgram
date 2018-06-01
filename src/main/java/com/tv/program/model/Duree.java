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
}
