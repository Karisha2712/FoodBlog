package edu.radyuk.foodblog.service;

import edu.radyuk.foodblog.entity.dto.CommentDto;
import edu.radyuk.foodblog.exception.ServiceException;

import java.util.List;

public interface CommentService {
    List<CommentDto> retrievePostComments(long postId) throws ServiceException;
}
