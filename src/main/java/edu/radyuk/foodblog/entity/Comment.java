package edu.radyuk.foodblog.entity;

import java.time.LocalDateTime;

/**
 * The type Comment.
 */
public class Comment extends AbstractEntity {
    private String commentText;
    private LocalDateTime commentDate;
    private long userId;
    private long postId;
    private double mark;

    /**
     * Instantiates a new Comment.
     */
    public Comment() {
    }

    /**
     * Gets mark.
     *
     * @return the mark
     */
    public double getMark() {
        return mark;
    }

    /**
     * Sets mark.
     *
     * @param mark the mark
     */
    public void setMark(double mark) {
        this.mark = mark;
    }

    /**
     * Gets comment text.
     *
     * @return the comment text
     */
    public String getCommentText() {
        return commentText;
    }

    /**
     * Sets comment text.
     *
     * @param commentText the comment text
     */
    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    /**
     * Gets comment date.
     *
     * @return the comment date
     */
    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    /**
     * Sets comment date.
     *
     * @param commentDate the comment date
     */
    public void setCommentDate(LocalDateTime commentDate) {
        this.commentDate = commentDate;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Gets post id.
     *
     * @return the post id
     */
    public long getPostId() {
        return postId;
    }

    /**
     * Sets post id.
     *
     * @param postId the post id
     */
    public void setPostId(long postId) {
        this.postId = postId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return userId == comment.userId
                && postId == comment.postId
                && commentText.equals(comment.commentText)
                && commentDate.equals(comment.commentDate);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result += result * 31 + commentText.hashCode();
        result += result * 31 + commentDate.hashCode();
        result += result * 31 + Long.hashCode(userId);
        result += result * 31 + Long.hashCode(postId);
        return result;
    }
}
