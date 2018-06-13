package com.tv.program.model.programmes;

import java.util.Collections;

public class Contemporain extends Programme {

    Contemporain() {
        super(Collections.emptyList());
    }

    @Override
    String creditsToString() {
        return EMPTY_CREDITS;
    }
}
