package edu.radyuk.foodblog.entity;

import java.time.LocalDateTime;

/**
 * The type Recipe post.
 */
public class RecipePost extends AbstractEntity {
    private String recipeText;
    private String picturePath;
    private Double postRating;
    private LocalDateTime postDate;
    private String dishName;
    private RecipePostCategory recipePostCategory;
    private long userId;

    /**
     * Instantiates a new Recipe post.
     */
    public RecipePost() {
    }

    /**
     * Gets dish name.
     *
     * @return the dish name
     */
    public String getDishName() {
        return dishName;
    }

    /**
     * Sets dish name.
     *
     * @param dishName the dish name
     */
    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    /**
     * Gets recipe text.
     *
     * @return the recipe text
     */
    public String getRecipeText() {
        return recipeText;
    }

    /**
     * Sets recipe text.
     *
     * @param recipeText the recipe text
     */
    public void setRecipeText(String recipeText) {
        this.recipeText = recipeText;
    }

    /**
     * Gets picture path.
     *
     * @return the picture path
     */
    public String getPicturePath() {
        return picturePath;
    }

    /**
     * Sets picture path.
     *
     * @param picturePath the picture path
     */
    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    /**
     * Gets post rating.
     *
     * @return the post rating
     */
    public Double getPostRating() {
        return postRating;
    }

    /**
     * Sets post rating.
     *
     * @param postRating the post rating
     */
    public void setPostRating(Double postRating) {
        this.postRating = postRating;
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
     * Gets post date.
     *
     * @return the post date
     */
    public LocalDateTime getPostDate() {
        return postDate;
    }

    /**
     * Sets post date.
     *
     * @param postDate the post date
     */
    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }

    /**
     * Gets recipe post category.
     *
     * @return the recipe post category
     */
    public RecipePostCategory getRecipePostCategory() {
        return recipePostCategory;
    }

    /**
     * Sets recipe post category.
     *
     * @param recipePostCategory the recipe post category
     */
    public void setRecipePostCategory(RecipePostCategory recipePostCategory) {
        this.recipePostCategory = recipePostCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
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
