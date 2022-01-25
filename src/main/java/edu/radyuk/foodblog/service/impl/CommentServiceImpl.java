package edu.radyuk.foodblog.service.impl;

import edu.radyuk.foodblog.dao.CommentDao;
import edu.radyuk.foodblog.dao.DaoProvider;
import edu.radyuk.foodblog.dao.MarkDao;
import edu.radyuk.foodblog.dao.UserDao;
import edu.radyuk.foodblog.entity.Comment;
import edu.radyuk.foodblog.entity.Mark;
import edu.radyuk.foodblog.entity.User;
import edu.radyuk.foodblog.entity.dto.CommentDto;
import edu.radyuk.foodblog.exception.DaoException;
import edu.radyuk.foodblog.exception.ServiceException;
import edu.radyuk.foodblog.service.BloggerInfoService;
import edu.radyuk.foodblog.service.CommentService;
import edu.radyuk.foodblog.service.ServiceProvider;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommentServiceImpl implements CommentService {
    private static final Logger logger = LogManager.getLogger();
    private static final Double DEFAULT_MARK = 0.0;

    @Override
    public List<CommentDto> retrievePostComments(long postId) throws ServiceException {
        CommentDao commentDao = DaoProvider.getInstance().getCommentDao();
        UserDao userDao = DaoProvider.getInstance().getUserDao();
        BloggerInfoService bloggerInfoService = ServiceProvider.getInstance().getBloggerInfoService();
        MarkDao markDao = DaoProvider.getInstance().getMarkDao();
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
                    Optional<Mark> optionalMark = markDao.findPostMark(user.getEntityId(), postId);
                    commentDto.setMark(optionalMark.map(Mark::getMarkValue).orElse(DEFAULT_MARK));
                    commentDto.setUserId(user.getEntityId());
                    commentDto.setUserLogin(user.getLogin());
                    String userAvatar = bloggerInfoService.findPicturePathByUserId(user.getEntityId());
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
}
