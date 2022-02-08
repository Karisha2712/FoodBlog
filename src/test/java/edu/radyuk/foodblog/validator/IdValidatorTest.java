package edu.radyuk.foodblog.validator;

import edu.radyuk.foodblog.connection.ConnectionPool;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class IdValidatorTest {
    private static IdValidator validator;

    @BeforeAll
    static void createConnections() {
        ConnectionPool.getInstance();
        validator = ValidatorProvider.getInstance().getIdValidator();
    }

    @AfterAll
    static void closeConnections() {
        ConnectionPool.getInstance().closeConnections();
    }

    @Test
    void ifIsIdPositiveReturnsTrue() {
        String validPageNumber = "5";
        boolean actual = validator.isIdPositive(validPageNumber);
        Assertions.assertTrue(actual);
    }

    @Test
    void ifIsIdPositiveReturnsFalse_NegativeParameter() {
        String invalidPageNumber = "-5";
        boolean actual = validator.isIdPositive(invalidPageNumber);
        Assertions.assertFalse(actual);
    }

    @Test
    void ifIsIdPositiveReturnsFalse_InvalidParameter() {
        String invalidPageNumber = "-5a";
        boolean actual = validator.isIdPositive(invalidPageNumber);
        Assertions.assertFalse(actual);
    }

    @Test
    void ifIsIdPositiveReturnsFalse_NullParameter() {
        boolean actual = validator.isIdPositive(null);
        Assertions.assertFalse(actual);
    }
}
