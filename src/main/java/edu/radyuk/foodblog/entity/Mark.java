package edu.radyuk.foodblog.entity;

public class Mark {
    private long userId;
    private long postId;
    private double markValue;

    public Mark() {
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

    public double getMarkValue() {
        return markValue;
    }

    public void setMarkValue(double markValue) {
        this.markValue = markValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mark mark = (Mark) o;
        if (userId != mark.userId) return false;
        if (postId != mark.postId) return false;
        return Double.compare(mark.markValue, markValue) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        result = Long.hashCode(userId);
        result = 31 * result + Long.hashCode(postId);
        result = 31 * result + Double.hashCode(markValue);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Mark{");
        sb.append("userId=").append(userId);
        sb.append(", postId=").append(postId);
        sb.append(", markValue=").append(markValue);
        sb.append('}');
        return sb.toString();
    }
}
