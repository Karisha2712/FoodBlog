package edu.radyuk.foodblog.validator.impl;

import edu.radyuk.foodblog.validator.CommentTextValidator;

/**
 * The type Comment text validator.
 */
public class CommentTextValidatorImpl implements CommentTextValidator {
    private static final int MIN_COMMENT_LENGTH = 3;
    private static final int MAX_COMMENT_LENGTH = 1000;

    @Override
    public boolean isCommentValid(String text) {
        return text != null &&
                !text.isBlank()
                && text.length() >= MIN_COMMENT_LENGTH
                && text.length() <= MAX_COMMENT_LENGTH;
    }
}
