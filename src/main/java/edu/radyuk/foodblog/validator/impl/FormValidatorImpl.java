package edu.radyuk.foodblog.validator.impl;

import edu.radyuk.foodblog.validator.FormValidator;

/**
 * The type Form validator.
 */
public final class FormValidatorImpl implements FormValidator {
    private static final String REGEXP_FOR_LOGIN_VALIDATION = "[A-Za-z0-9_-]{3,20}";
    private static final String REGEXP_FOR_EMAIL_VALIDATION =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final String REGEXP_FOR_PASSWORD_VALIDATION = "[A-Za-z0-9_-]{6,20}";
    private static final String REGEXP_FOR_CITY_COUNTRY_NAME = "[A-Za-z-А-Яа-я\\s]{2,40}";
    private static final String REGEXP_FOR_AGE = "[1-9][0-9]{1,2}";
    private static final String REGEXP_FOR_PICTURE_PATH = ".+\\.(png|jpg)";
    private static final int MAX_RECIPE_TEXT_LENGTH = 2000;
    private static final int MAX_PERSONAL_INFO_LENGTH = 256;

    public boolean isSignUpParametersValid(String login, String email, String password) {
        boolean areValid = login != null && email != null && password != null;
        return areValid &&
                login.matches(REGEXP_FOR_LOGIN_VALIDATION)
                && email.matches(REGEXP_FOR_EMAIL_VALIDATION)
                && password.matches(REGEXP_FOR_PASSWORD_VALIDATION);
    }

    @Override
    public boolean isEditInfoParametersValid(String city, String country, String age,
                                             String personalInfo, String fileName) {
        boolean areValid = city != null && country != null && age != null && personalInfo != null && fileName != null;
        return areValid
                && city.matches(REGEXP_FOR_CITY_COUNTRY_NAME)
                && country.matches(REGEXP_FOR_CITY_COUNTRY_NAME)
                && age.matches(REGEXP_FOR_AGE)
                && fileName.matches(REGEXP_FOR_PICTURE_PATH)
                && personalInfo.length() <= MAX_PERSONAL_INFO_LENGTH;
    }

    @Override
    public boolean isRecipePostParametersValid(String dishName, String category, String fileName, String recipeText) {
        boolean areValid = dishName != null && category != null && fileName != null && recipeText != null;
        return areValid
                && dishName.matches(REGEXP_FOR_CITY_COUNTRY_NAME)
                && fileName.matches(REGEXP_FOR_PICTURE_PATH)
                && recipeText.length() <= MAX_RECIPE_TEXT_LENGTH;
    }

}
