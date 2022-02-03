package edu.radyuk.foodblog.validator;

public interface FormValidator {
    boolean areSignUpParametersValid(String login, String email, String password);

    boolean areEditInfoParametersValid(String city, String country, String age, String personalInfo, String fileName);

    boolean areRecipePostParametersValid(String dishName, String category, String fileName, String recipeText);
}
