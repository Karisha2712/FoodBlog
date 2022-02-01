package edu.radyuk.foodblog.validator;

public interface FormValidator {
    boolean areSignUpParametersValid(String login, String email, String password);

    boolean areSearchParameterValid(String searchValue);

    boolean areEditInfoParametersValid(String city, String country, String age, String personalInfo, String fileName);
}
