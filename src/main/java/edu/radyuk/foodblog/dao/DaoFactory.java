package edu.radyuk.foodblog.dao;

import edu.radyuk.foodblog.dao.impl.BloggerInfoDaoImpl;
import edu.radyuk.foodblog.dao.impl.CommentDaoImpl;
import edu.radyuk.foodblog.dao.impl.RecipePostDaoImpl;
import edu.radyuk.foodblog.dao.impl.UserDaoImpl;

public class DaoFactory {
    private final CommentDao commentDao = new CommentDaoImpl();
    private final UserDao userDao = new UserDaoImpl();
    private final BloggerInfoDao bloggerInfoDao = new BloggerInfoDaoImpl();
    private final RecipePostDao recipePostDao = new RecipePostDaoImpl();

    public DaoFactory getInstance() {
        return Holder.instance;
    }

    public CommentDao getCommentDao() {
        return commentDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public BloggerInfoDao getBloggerInfoDao() {
        return bloggerInfoDao;
    }

    public RecipePostDao getRecipePostDao() {
        return recipePostDao;
    }

    private static class Holder {
        private static final DaoFactory instance = new DaoFactory();
    }
}
