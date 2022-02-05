package edu.radyuk.foodblog.dao;

import edu.radyuk.foodblog.entity.Comment;
import edu.radyuk.foodblog.exception.DaoException;

import java.util.List;

/**
 * The interface Comment dao.
 */
public interface CommentDao extends Dao<Comment> {
    /**
     * Find comments by post id list.
     *
     * @param postId the post id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Comment> findCommentsByPostId(long postId) throws DaoException;
}
