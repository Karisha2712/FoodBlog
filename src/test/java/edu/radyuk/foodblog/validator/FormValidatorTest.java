package edu.radyuk.foodblog.validator;

import edu.radyuk.foodblog.connection.ConnectionPool;
import edu.radyuk.foodblog.entity.RecipePostCategory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class FormValidatorTest {
    private static FormValidator validator;
    private String validLogin = "Login";
    private String validPassword = "Password";
    private String validEmail = "email@mail.ru";

    @BeforeAll
    static void createConnections() {
        ConnectionPool.getInstance();
        validator = ValidatorProvider.getInstance().getFormValidator();
    }

    @AfterAll
    static void closeConnections() {
        ConnectionPool.getInstance().closeConnections();
    }

    @Test
    void ifIsSignUpParametersValidReturnsTrue() {
        boolean actual = validator.isSignUpParametersValid(validLogin, validEmail, validPassword);
        Assertions.assertTrue(actual);
    }

    @Test
    void ifIsSignUpParametersValidReturnsFalse_InvalidEmail() {
        String invalidEmail = "email";
        boolean actual = validator.isSignUpParametersValid(validLogin, invalidEmail, validPassword);
        Assertions.assertFalse(actual);
    }

    @Test
    void ifIsSignUpParametersValidReturnsFalse_InvalidLogin() {
        String invalidLogin = "Логин";
        boolean actual = validator.isSignUpParametersValid(invalidLogin, validEmail, validPassword);
        Assertions.assertFalse(actual);
    }

    @Test
    void ifIsSignUpParametersValidReturnsFalse_InvalidPassword() {
        boolean actual = validator.isSignUpParametersValid(validLogin, validEmail, null);
        Assertions.assertFalse(actual);
    }

    @Test
    void ifIsEditInfoParametersValidReturnsTrue() {
        String validCountry = "Country";
        String validCity = "City";
        String validAge = "19";
        String validFileName = "1.png";
        String validPersonalInfo = "Info";
        boolean actual = validator.isEditInfoParametersValid(validCity, validCountry,
                validAge, validPersonalInfo, validFileName);
        Assertions.assertTrue(actual);
    }

    @Test
    void ifIsEditInfoParametersValidReturnsFalse_InvalidAge() {
        String validCountry = "Country";
        String validCity = "City";
        String invalidAge = "1931";
        String validFileName = "1.png";
        String validPersonalInfo = "Info";
        boolean actual = validator.isEditInfoParametersValid(validCity, validCountry,
                invalidAge, validPersonalInfo, validFileName);
        Assertions.assertFalse(actual);
    }

    @Test
    void ifIsEditInfoParametersValidReturnsFalse_InvalidFileName() {
        String validCountry = "Country";
        String validCity = "City";
        String invalidAge = "1931";
        String invalidFileName = "1.pg";
        String validPersonalInfo = "Info";
        boolean actual = validator.isEditInfoParametersValid(validCity, validCountry,
                invalidAge, validPersonalInfo, invalidFileName);
        Assertions.assertFalse(actual);
    }

    @Test
    void ifIsEditInfoParametersValidReturnsFalse() {
        boolean actual = validator.isEditInfoParametersValid(null, null, null, null, null);
        Assertions.assertFalse(actual);
    }

    @Test
    void ifIsRecipePostParametersValidReturnsTrue() {
        String validDishName = "Dish name";
        String validCategory = RecipePostCategory.APPETIZER.toString();
        String validFileName = "1.png";
        String validRecipeText = "Recipe text";
        boolean actual = validator.isRecipePostParametersValid(validDishName, validCategory, validFileName, validRecipeText);
        Assertions.assertTrue(actual);
    }

    @Test
    void ifIsRecipePostParametersValidReturnsFalse() {
        boolean actual = validator.isRecipePostParametersValid(null, null, null, null);
        Assertions.assertFalse(actual);
    }

}
