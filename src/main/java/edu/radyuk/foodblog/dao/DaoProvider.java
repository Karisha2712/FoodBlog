package edu.radyuk.foodblog.dao;

import edu.radyuk.foodblog.dao.impl.BloggerInfoDaoImpl;
import edu.radyuk.foodblog.dao.impl.CommentDaoImpl;
import edu.radyuk.foodblog.dao.impl.RecipePostDaoImpl;
import edu.radyuk.foodblog.dao.impl.UserDaoImpl;

/**
 * The type Dao provider.
 */
public final class DaoProvider {
    private final CommentDao commentDao = new CommentDaoImpl();
    private final UserDao userDao = new UserDaoImpl();
    private final BloggerInfoDao bloggerInfoDao = new BloggerInfoDaoImpl();
    private final RecipePostDao recipePostDao = new RecipePostDaoImpl();

    private DaoProvider() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static DaoProvider getInstance() {
        return Holder.instance;
    }

    /**
     * Gets comment dao.
     *
     * @return the comment dao
     */
    public CommentDao getCommentDao() {
        return commentDao;
    }

    /**
     * Gets user dao.
     *
     * @return the user dao
     */
    public UserDao getUserDao() {
        return userDao;
    }

    /**
     * Gets blogger info dao.
     *
     * @return the blogger info dao
     */
    public BloggerInfoDao getBloggerInfoDao() {
        return bloggerInfoDao;
    }

    /**
     * Gets recipe post dao.
     *
     * @return the recipe post dao
     */
    public RecipePostDao getRecipePostDao() {
        return recipePostDao;
    }


    private static class Holder {
        private static final DaoProvider instance = new DaoProvider();
    }

}
