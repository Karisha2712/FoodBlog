package edu.radyuk.foodblog.dao;

import edu.radyuk.foodblog.entity.Comment;
import edu.radyuk.foodblog.exception.DaoException;

import java.util.List;

public interface CommentDao extends Dao<Comment> {
    List<Comment> findCommentsByPostId(long postId) throws DaoException;
}
