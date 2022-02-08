package edu.radyuk.foodblog.validator;

import edu.radyuk.foodblog.connection.ConnectionPool;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CommentTextValidatorTest {
    private static CommentTextValidator validator;

    @BeforeAll
    static void createConnections() {
        ConnectionPool.getInstance();
        validator = ValidatorProvider.getInstance().getCommentTextValidator();
    }

    @AfterAll
    static void closeConnections() {
        ConnectionPool.getInstance().closeConnections();
    }

    @Test
    void ifIsCommentValidReturnsTrue() {
        String validComment = "Valid comment";
        boolean actual = validator.isCommentValid(validComment);
        Assertions.assertTrue(actual);
    }

    @Test
    void ifIsCommentValidReturnsFalse_ShortParameter() {
        String invalidComment = "1";
        boolean actual = validator.isCommentValid(invalidComment);
        Assertions.assertFalse(actual);
    }

    @Test
    void ifIsCommentValidReturnsFalse_BlankParameter() {
        String invalidComment = " ";
        boolean actual = validator.isCommentValid(invalidComment);
        Assertions.assertFalse(actual);
    }

    @Test
    void ifIsCommentValidReturnsFalse_NullParameter() {
        boolean actual = validator.isCommentValid(null);
        Assertions.assertFalse(actual);
    }

}
