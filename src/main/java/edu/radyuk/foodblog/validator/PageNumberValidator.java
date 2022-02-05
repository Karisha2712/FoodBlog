package edu.radyuk.foodblog.validator;

/**
 * The interface Page number validator.
 */
public interface PageNumberValidator {
    /**
     * Is page number valid boolean.
     *
     * @param pageNumber the page number
     * @return the boolean
     */
    boolean isPageNumberValid(String pageNumber);
}
