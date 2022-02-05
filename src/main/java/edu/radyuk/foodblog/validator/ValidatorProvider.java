package edu.radyuk.foodblog.validator;

import edu.radyuk.foodblog.validator.impl.CommentTextValidatorImpl;
import edu.radyuk.foodblog.validator.impl.FormValidatorImpl;
import edu.radyuk.foodblog.validator.impl.IdValidatorImpl;
import edu.radyuk.foodblog.validator.impl.PageNumberValidatorImpl;

/**
 * The type Validator provider.
 */
public final class ValidatorProvider {
    private final FormValidator formValidator = new FormValidatorImpl();
    private final IdValidator idValidator = new IdValidatorImpl();
    private final PageNumberValidator pageNumberValidator = new PageNumberValidatorImpl();

    private final CommentTextValidator commentTextValidator = new CommentTextValidatorImpl();

    private ValidatorProvider() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ValidatorProvider getInstance() {
        return Holder.instance;
    }

    /**
     * Gets page number validator.
     *
     * @return the page number validator
     */
    public PageNumberValidator getPageNumberValidator() {
        return pageNumberValidator;
    }

    /**
     * Gets form validator.
     *
     * @return the form validator
     */
    public FormValidator getFormValidator() {
        return formValidator;
    }

    /**
     * Gets id validator.
     *
     * @return the id validator
     */
    public IdValidator getIdValidator() {
        return idValidator;
    }

    /**
     * Gets comment text validator.
     *
     * @return the comment text validator
     */
    public CommentTextValidator getCommentTextValidator() {
        return commentTextValidator;
    }

    private static class Holder {
        private static final ValidatorProvider instance = new ValidatorProvider();
    }
}
