package com.tv.program.model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Duree {
    private final static Map<String, TimeUnit> TIME_UNIT_MAP;

    static {
        TIME_UNIT_MAP = new HashMap<>();
        TIME_UNIT_MAP.put("minutes", TimeUnit.MINUTES);
        TIME_UNIT_MAP.put("hours", TimeUnit.HOURS);
    }

    private TimeUnit unit;
    private long value;

    public Duree(String unit, long value) {
        this.unit = TIME_UNIT_MAP.get(unit);
        this.value = value;
    }

    public TimeUnit getUnit() {
        return unit;
    }

    public long getValue() {
        return value;
    }

    public long getValueInSeconds() {
        return unit.toSeconds(value);
    }

    @Override
    public String toString() {
        switch (unit) {
            case HOURS:
                return value + "h";
            case MINUTES:
                return value + "mn";
            case SECONDS:
                return value + "s";
        }
        throw new RuntimeException("L'unité de temps fournie n'est pas valide");
    }
}
