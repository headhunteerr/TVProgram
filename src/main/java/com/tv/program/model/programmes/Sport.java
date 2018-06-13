package com.tv.program.model.programmes;

import java.util.Collections;

public class Sport extends Programme {

    Sport() {
        super(Collections.emptyList());
    }

    @Override
    String creditsToString() {
        return EMPTY_CREDITS;
    }
}
