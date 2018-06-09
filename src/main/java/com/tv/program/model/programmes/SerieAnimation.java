package com.tv.program.model.programmes;

import com.tv.program.model.Personne;

import java.util.Collections;
import java.util.List;

public class SerieAnimation extends Programme {

    SerieAnimation() {
        super(Collections.emptyList());
    }

    @Override
    String creditsToString() {
        return "credits=[]";
    }
}
