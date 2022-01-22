package edu.radyuk.foodblog.validator;

public interface FormValidator {
    boolean areSignUpParametersValid(String login, String email, String password);
}
