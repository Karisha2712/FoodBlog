package edu.radyuk.foodblog.validator;

/**
 * The interface Id validator.
 */
public interface IdValidator {
    /**
     * Is id positive boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean isIdPositive(String id);
}