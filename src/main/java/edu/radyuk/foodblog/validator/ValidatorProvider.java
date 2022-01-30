package edu.radyuk.foodblog.validator;

import edu.radyuk.foodblog.validator.impl.CommentTextValidatorImpl;
import edu.radyuk.foodblog.validator.impl.FormValidatorImpl;
import edu.radyuk.foodblog.validator.impl.IdValidatorImpl;

public final class ValidatorProvider {
    private final FormValidator formValidator = new FormValidatorImpl();
    private final IdValidator idValidator = new IdValidatorImpl();
    private final CommentTextValidator commentTextValidator = new CommentTextValidatorImpl();

    private ValidatorProvider() {
    }

    public static ValidatorProvider getInstance() {
        return Holder.instance;
    }

    public FormValidator getFormValidator() {
        return formValidator;
    }

    public IdValidator getIdValidator() {
        return idValidator;
    }

    public CommentTextValidator getCommentTextValidator() {
        return commentTextValidator;
    }

    private static class Holder {
        private static final ValidatorProvider instance = new ValidatorProvider();
    }
}
