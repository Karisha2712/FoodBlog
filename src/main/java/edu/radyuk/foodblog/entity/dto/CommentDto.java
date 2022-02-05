package edu.radyuk.foodblog.entity.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The type Comment dto.
 */
public class CommentDto implements Serializable {
    private String commentText;
    private double mark;
    private LocalDateTime commentDate;
    private String userPicturePath;
    private String userLogin;
    private long userId;
    private long postId;
    private long commentId;

    /**
     * Gets comment id.
     *
     * @return the comment id
     */
    public long getCommentId() {
        return commentId;
    }

    /**
     * Sets comment id.
     *
     * @param commentId the comment id
     */
    public void setCommentId(long commentId) {
        this.commentId = commentId;
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
     * Gets user picture path.
     *
     * @return the user picture path
     */
    public String getUserPicturePath() {
        return userPicturePath;
    }

    /**
     * Sets user picture path.
     *
     * @param userPicturePath the user picture path
     */
    public void setUserPicturePath(String userPicturePath) {
        this.userPicturePath = userPicturePath;
    }

    /**
     * Gets user login.
     *
     * @return the user login
     */
    public String getUserLogin() {
        return userLogin;
    }

    /**
     * Sets user login.
     *
     * @param userLogin the user login
     */
    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
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

        CommentDto that = (CommentDto) o;

        if (Double.compare(that.mark, mark) != 0) return false;
        if (userId != that.userId) return false;
        if (postId != that.postId) return false;
        if (commentId != that.commentId) return false;
        if (commentText != null ? !commentText.equals(that.commentText) : that.commentText != null) return false;
        if (commentDate != null ? !commentDate.equals(that.commentDate) : that.commentDate != null) return false;
        if (userPicturePath != null ? !userPicturePath.equals(that.userPicturePath) : that.userPicturePath != null)
            return false;
        return userLogin != null ? userLogin.equals(that.userLogin) : that.userLogin == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = commentText != null ? commentText.hashCode() : 0;
        temp = Double.doubleToLongBits(mark);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (commentDate != null ? commentDate.hashCode() : 0);
        result = 31 * result + (userPicturePath != null ? userPicturePath.hashCode() : 0);
        result = 31 * result + (userLogin != null ? userLogin.hashCode() : 0);
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (postId ^ (postId >>> 32));
        result = 31 * result + (int) (commentId ^ (commentId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CommentDto{");
        sb.append("commentText='").append(commentText).append('\'');
        sb.append(", mark=").append(mark);
        sb.append(", commentDate=").append(commentDate);
        sb.append(", userPicturePath='").append(userPicturePath).append('\'');
        sb.append(", userLogin='").append(userLogin).append('\'');
        sb.append(", userId=").append(userId);
        sb.append(", postId=").append(postId);
        sb.append(", commentId=").append(commentId);
        sb.append('}');
        return sb.toString();
    }
}
