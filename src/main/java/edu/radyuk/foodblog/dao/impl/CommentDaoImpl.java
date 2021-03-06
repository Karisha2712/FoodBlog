package edu.radyuk.foodblog.dao.impl;

import edu.radyuk.foodblog.connection.ConnectionPool;
import edu.radyuk.foodblog.dao.CommentDao;
import edu.radyuk.foodblog.dao.JdbcHelper;
import edu.radyuk.foodblog.dao.mapper.impl.CommentRowMapperImpl;
import edu.radyuk.foodblog.entity.Comment;
import edu.radyuk.foodblog.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * The type Comment dao.
 */
public class CommentDaoImpl implements CommentDao {
    private static final String FIND_COMMENTS_BY_POST_ID_QUERY =
            "SELECT * FROM comments WHERE posts_post_id = ?";
    private static final String DELETE_COMMENT_BY_ID =
            "DELETE FROM comments WHERE comment_id = ?";
    private static final String INSERT_COMMENT_QUERY = "INSERT INTO comments " +
            "(mark, comment_text, date, users_user_id, posts_post_id) " +
            "VALUES(?, ?, ?, ?, ?)";

    private JdbcHelper<Comment> jdbcHelper;

    /**
     * Instantiates a new Comment dao.
     */
    public CommentDaoImpl() {
        jdbcHelper = new JdbcHelper<>(ConnectionPool.getInstance(), new CommentRowMapperImpl());
    }

    @Override
    public Optional<Comment> findEntityById(long id) {
        throw new UnsupportedOperationException("Method is not realised");
    }

    @Override
    public long insert(Comment comment) throws DaoException {
        return jdbcHelper.executeUpdate(INSERT_COMMENT_QUERY,
                comment.getMark(),
                comment.getCommentText(),
                comment.getCommentDate(),
                comment.getUserId(),
                comment.getPostId());
    }

    @Override
    public long update(Comment entity) {
        throw new UnsupportedOperationException("Method is not realised");
    }

    @Override
    public long removeEntityById(long id) throws DaoException {
        return jdbcHelper.executeUpdate(DELETE_COMMENT_BY_ID, id);
    }

    @Override
    public List<Comment> findCommentsByPostId(long postId) throws DaoException {
        return jdbcHelper.executeQuery(FIND_COMMENTS_BY_POST_ID_QUERY, postId);
    }
}
