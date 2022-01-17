package edu.radyuk.foodblog.entity;

import java.time.LocalDateTime;

public class Comment extends AbstractEntity {
    private String commentText;
    private LocalDateTime commentDate;
    private long userId;
    private long postId;

    public Comment() {
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDateTime commentDate) {
        this.commentDate = commentDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Comment comment = (Comment) o;
        return userId == comment.userId
                && postId == comment.postId
                && commentText.equals(comment.commentText)
                && commentDate.equals(comment.commentDate);
    }

    @Override
    public int hashCode() {
        int result;
        result = super.hashCode();
        result += result * 31 + commentText.hashCode();
        result += result * 31 + commentDate.hashCode();
        result += result * 31 + Long.hashCode(userId);
        result += result * 31 + Long.hashCode(postId);
        return result;
    }
}
