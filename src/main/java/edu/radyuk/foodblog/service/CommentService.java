package edu.radyuk.foodblog.service;

import edu.radyuk.foodblog.entity.dto.CommentDto;
import edu.radyuk.foodblog.exception.ServiceException;

import java.time.LocalDateTime;
import java.util.List;

public interface CommentService {
    List<CommentDto> retrievePostComments(long postId) throws ServiceException;

    void addComment(String commentText, LocalDateTime dateTime, long userId, long postId, double mark) throws ServiceException;

    void overwriteRecipePostRating(long postId) throws ServiceException;
}
