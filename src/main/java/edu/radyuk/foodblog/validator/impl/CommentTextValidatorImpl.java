package edu.radyuk.foodblog.validator.impl;

import edu.radyuk.foodblog.validator.CommentTextValidator;

/**
 * The type Comment text validator.
 */
public class CommentTextValidatorImpl implements CommentTextValidator {
    private static final String REGEXP_FOR_COMMENT = ".{3,1000}";

    @Override
    public boolean isCommentValid(String text) {
        return !text.isBlank() && text.matches(REGEXP_FOR_COMMENT);
    }
}
