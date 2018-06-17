package com.tv.program.model.programmes;

import java.util.Collections;

public class Journal extends Programme {
    Journal() {
        super(Collections.emptyList());
    }

    @Override
    String creditsToString() {
        return EMPTY_CREDITS;
    }
}
