package edu.radyuk.foodblog.validator;

import edu.radyuk.foodblog.connection.ConnectionPool;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PageNumberValidatorTest {
    private static PageNumberValidator validator;

    @BeforeAll
    static void createConnections() {
        ConnectionPool.getInstance();
        validator = ValidatorProvider.getInstance().getPageNumberValidator();
    }

    @Test
    void ifIsPageNumberValidReturnsTrue() {
        String validPageNumber = "5";
        boolean actual = validator.isPageNumberValid(validPageNumber);
        Assertions.assertTrue(actual);
    }

    @Test
    void ifIsPageNumberValidReturnsFalse_NegativeParameter() {
        String invalidPageNumber = "-5";
        boolean actual = validator.isPageNumberValid(invalidPageNumber);
        Assertions.assertFalse(actual);
    }

    @Test
    void ifIsPageNumberValidReturnsFalse_InvalidParameter() {
        String invalidPageNumber = "-5a";
        boolean actual = validator.isPageNumberValid(invalidPageNumber);
        Assertions.assertFalse(actual);
    }

    @Test
    void ifIsPageNumberValidReturnsFalse_NullParameter() {
        boolean actual = validator.isPageNumberValid(null);
        Assertions.assertFalse(actual);
    }

    @AfterAll
    static void closeConnections() {
        ConnectionPool.getInstance().closeConnections();
    }

}
