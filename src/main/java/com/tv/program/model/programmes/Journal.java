package com.tv.program.model.programmes;

import java.util.Collections;

public class Journal extends Emission {
    public Journal() {
        super(Collections.emptyList());
    }


    @Override
    String creditsToString() {
        return "credits=[]";
    }
}
