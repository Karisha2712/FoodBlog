package edu.radyuk.foodblog.validator.impl;

import edu.radyuk.foodblog.validator.PageNumberValidator;

public class PageNumberValidatorImpl implements PageNumberValidator {
    private static final String REGEXP_FOR_DIGIT = "\\d+";

    @Override
    public boolean isPageNumberValid(String pageNumber) {
        if ((pageNumber == null) || !(pageNumber.matches(REGEXP_FOR_DIGIT))) {
            return false;
        }
        int page;
        try {
            page = Integer.parseInt(pageNumber);
        } catch (NumberFormatException e) {
            return false;
        }
        return page > 0;
    }
}
