package edu.radyuk.foodblog.validator.impl;

import edu.radyuk.foodblog.validator.FormValidator;

public final class FormValidatorImpl implements FormValidator {
    private static final String REGEXP_FOR_LOGIN_VALIDATION = "(\\w|[-]){3,20}";
    private static final String REGEXP_FOR_EMAIL_VALIDATION =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final String REGEXP_FOR_PASSWORD_VALIDATION = "(\\w){8,20}";

    public boolean areSignUpParametersValid(String login, String email, String password) {
        return login.matches(REGEXP_FOR_LOGIN_VALIDATION)
                && email.matches(REGEXP_FOR_EMAIL_VALIDATION)
                && password.matches(REGEXP_FOR_PASSWORD_VALIDATION);
    }
}
