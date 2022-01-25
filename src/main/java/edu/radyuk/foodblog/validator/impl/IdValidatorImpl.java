package edu.radyuk.foodblog.validator.impl;

import edu.radyuk.foodblog.validator.IdValidator;

public class IdValidatorImpl implements IdValidator {
    private static final String REGEXP_FOR_DIGIT = "\\d+";

    @Override
    public boolean isIdPositive(String id) {
        if ((id == null) || !id.matches(REGEXP_FOR_DIGIT)) {
            return false;
        }
        long longId;
        try {
            longId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return false;
        }
        return longId >= 0;
    }
}
