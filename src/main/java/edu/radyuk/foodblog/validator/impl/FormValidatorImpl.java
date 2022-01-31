package edu.radyuk.foodblog.validator.impl;

import edu.radyuk.foodblog.validator.FormValidator;

public final class FormValidatorImpl implements FormValidator {
    private static final String REGEXP_FOR_LOGIN_VALIDATION = "[A-Za-z0-9_-]{3,20}";
    private static final String REGEXP_FOR_EMAIL_VALIDATION =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final String REGEXP_FOR_PASSWORD_VALIDATION = "[A-Za-z0-9_-]{6,20}";
    private static final int MAX_SEARCH_VALUE_LENGTH = 24;

    public boolean areSignUpParametersValid(String login, String email, String password) {
        boolean areValid = login != null && email != null && password != null;
        return areValid &&
                login.matches(REGEXP_FOR_LOGIN_VALIDATION)
                && email.matches(REGEXP_FOR_EMAIL_VALIDATION)
                && password.matches(REGEXP_FOR_PASSWORD_VALIDATION);
    }

    @Override
    public boolean areSearchParameterValid(String searchValue) {
        return searchValue != null && !searchValue.isBlank() && (searchValue.length() <= MAX_SEARCH_VALUE_LENGTH);
    }
}
