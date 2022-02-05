package edu.radyuk.foodblog.service.impl;

import edu.radyuk.foodblog.dao.CommentDao;
import edu.radyuk.foodblog.dao.DaoProvider;
import edu.radyuk.foodblog.dao.RecipePostDao;
import edu.radyuk.foodblog.dao.UserDao;
import edu.radyuk.foodblog.entity.Comment;
import edu.radyuk.foodblog.entity.User;
import edu.radyuk.foodblog.entity.dto.CommentDto;
import edu.radyuk.foodblog.exception.DaoException;
import edu.radyuk.foodblog.exception.ServiceException;
import edu.radyuk.foodblog.service.BloggerInfoService;
import edu.radyuk.foodblog.service.CommentService;
import edu.radyuk.foodblog.service.ServiceProvider;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Comment service.
 */
public class CommentServiceImpl implements CommentService {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<CommentDto> retrievePostComments(long postId) throws ServiceException {
        CommentDao commentDao = DaoProvider.getInstance().getCommentDao();
        UserDao userDao = DaoProvider.getInstance().getUserDao();
        BloggerInfoService bloggerInfoService = ServiceProvider.getInstance().getBloggerInfoService();
        try {
            List<Comment> comments = commentDao.findCommentsByPostId(postId);
            List<CommentDto> resultComments = new ArrayList<>(comments.size());
            for (Comment comment : comments) {
                Optional<User> optionalUser = userDao.findEntityById(comment.getUserId());
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    CommentDto commentDto = new CommentDto();
                    commentDto.setCommentDate(comment.getCommentDate());
                    commentDto.setCommentText(comment.getCommentText());
                    commentDto.setMark(comment.getMark());
                    commentDto.setUserId(user.getEntityId());
                    commentDto.setUserLogin(user.getLogin());
                    String userAvatar = bloggerInfoService.retrievePicturePathByUserId(user.getEntityId());
                    commentDto.setUserPicturePath(userAvatar);
                    commentDto.setPostId(comment.getPostId());
                    commentDto.setCommentId(comment.getEntityId());
                    resultComments.add(commentDto);
                }
            }
            return resultComments;
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void addComment(String commentText, LocalDateTime dateTime, long userId, long postId, double mark) throws ServiceException {
        Comment comment = new Comment();
        CommentDao commentDao = DaoProvider.getInstance().getCommentDao();
        commentText = StringEscapeUtils.escapeHtml4(commentText);
        comment.setCommentText(commentText);
        comment.setCommentDate(dateTime);
        comment.setUserId(userId);
        comment.setPostId(postId);
        comment.setMark(mark);
        try {
            commentDao.insert(comment);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
    }

    @Override
    public long deleteCommentById(long commentId) throws ServiceException {
        CommentDao commentDao = DaoProvider.getInstance().getCommentDao();
        try {
            return commentDao.removeEntityById(commentId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void overwriteRecipePostRating(long postId) throws ServiceException {
        CommentDao commentDao = DaoProvider.getInstance().getCommentDao();
        RecipePostDao recipePostDao = DaoProvider.getInstance().getRecipePostDao();
        List<Comment> comments;
        double averageMark = 0;
        try {
            comments = commentDao.findCommentsByPostId(postId);
            for (Comment comment : comments) {
                averageMark += comment.getMark();
            }
            averageMark = comments.isEmpty() ? 0 : averageMark / comments.size();
            recipePostDao.updateRecipePostRating(postId, averageMark);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
    }

}
