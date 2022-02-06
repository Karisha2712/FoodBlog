package edu.radyuk.foodblog.validator;

/**
 * The interface Form validator.
 */
public interface FormValidator {
    /**
     * Are sign up parameters valid boolean.
     *
     * @param login    the login
     * @param email    the email
     * @param password the password
     * @return the boolean
     */
    boolean isSignUpParametersValid(String login, String email, String password);

    /**
     * Are edit info parameters valid boolean.
     *
     * @param city         the city
     * @param country      the country
     * @param age          the age
     * @param personalInfo the personal info
     * @param fileName     the file name
     * @return the boolean
     */
    boolean isEditInfoParametersValid(String city, String country, String age, String personalInfo, String fileName);

    /**
     * Are recipe post parameters valid boolean.
     *
     * @param dishName   the dish name
     * @param category   the category
     * @param fileName   the file name
     * @param recipeText the recipe text
     * @return the boolean
     */
    boolean isRecipePostParametersValid(String dishName, String category, String fileName, String recipeText);
}
