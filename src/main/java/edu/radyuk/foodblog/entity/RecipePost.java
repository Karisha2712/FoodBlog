package edu.radyuk.foodblog.entity;

import java.time.LocalDateTime;

public class RecipePost extends AbstractEntity {
    private String recipeText;
    private String picturePath;
    private Double postRating;
    private LocalDateTime postDate;
    private RecipePostCategory recipePostCategory;
    private long userId;

    public RecipePost() {
    }

    public String getRecipeText() {
        return recipeText;
    }

    public void setRecipeText(String recipeText) {
        this.recipeText = recipeText;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public Double getPostRating() {
        return postRating;
    }

    public void setPostRating(Double postRating) {
        this.postRating = postRating;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }

    public RecipePostCategory getRecipePostCategory() {
        return recipePostCategory;
    }

    public void setRecipePostCategory(RecipePostCategory recipePostCategory) {
        this.recipePostCategory = recipePostCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RecipePost that = (RecipePost) o;
        return userId == that.userId
                && recipePostCategory == that.recipePostCategory
                && recipeText.equals(that.recipeText)
                && picturePath.equals(that.picturePath)
                && postRating.equals(that.postRating)
                && postDate.equals(that.postDate);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result += result * 31 + super.hashCode();
        result += result * 31 + recipeText.hashCode();
        result += result * 31 + picturePath.hashCode();
        result += result * 31 + Double.hashCode(postRating);
        result += result * 31 + Long.hashCode(userId);
        result += result * 31 + postDate.hashCode();
        result += result * 31 + recipePostCategory.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RecipePost{");
        sb.append("recipeText='").append(recipeText).append('\'');
        sb.append(", picturePath='").append(picturePath).append('\'');
        sb.append(", rating=").append(postRating);
        sb.append(", userId=").append(userId);
        sb.append(", date=").append(postDate);
        sb.append(", recipePostCategory=").append(recipePostCategory);
        sb.append('}');
        return sb.toString();
    }
}
