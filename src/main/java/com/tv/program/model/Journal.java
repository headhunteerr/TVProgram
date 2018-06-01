package com.tv.program.model;

import java.util.Date;
import java.util.List;

public class Journal extends Emission {
    public Journal(List<Personne> personnes) {
        super(personnes);
    }
}
