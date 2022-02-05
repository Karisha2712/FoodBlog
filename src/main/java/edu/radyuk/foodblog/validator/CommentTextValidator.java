package edu.radyuk.foodblog.validator;

/**
 * The interface Comment text validator.
 */
public interface CommentTextValidator {
    /**
     * Is comment valid boolean.
     *
     * @param text the text
     * @return the boolean
     */
    boolean isCommentValid(String text);
}
