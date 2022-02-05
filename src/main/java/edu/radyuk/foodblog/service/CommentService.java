package edu.radyuk.foodblog.service;

import edu.radyuk.foodblog.entity.dto.CommentDto;
import edu.radyuk.foodblog.exception.ServiceException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The interface Comment service.
 */
public interface CommentService {
    /**
     * Retrieve post comments list.
     *
     * @param postId the post id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<CommentDto> retrievePostComments(long postId) throws ServiceException;

    /**
     * Add comment.
     *
     * @param commentText the comment text
     * @param dateTime    the date time
     * @param userId      the user id
     * @param postId      the post id
     * @param mark        the mark
     * @throws ServiceException the service exception
     */
    void addComment(String commentText, LocalDateTime dateTime, long userId,
                    long postId, double mark) throws ServiceException;

    /**
     * Overwrite recipe post rating.
     *
     * @param postId the post id
     * @throws ServiceException the service exception
     */
    void overwriteRecipePostRating(long postId) throws ServiceException;

    /**
     * Delete comment by id long.
     *
     * @param commentId the comment id
     * @return the long
     * @throws ServiceException the service exception
     */
    long deleteCommentById(long commentId) throws ServiceException;

}
