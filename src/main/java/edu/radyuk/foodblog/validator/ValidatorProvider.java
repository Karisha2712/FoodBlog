package edu.radyuk.foodblog.validator;

import edu.radyuk.foodblog.validator.impl.FormValidatorImpl;

public final class ValidatorProvider {
    private final FormValidator formValidator = new FormValidatorImpl();

    private ValidatorProvider() {
    }

    public static ValidatorProvider getInstance() {
        return Holder.instance;
    }

    public FormValidator getFormValidator() {
        return formValidator;
    }

    private static class Holder {
        private static final ValidatorProvider instance = new ValidatorProvider();
    }
}
