package edu.radyuk.foodblog.entity.dto;

import edu.radyuk.foodblog.entity.RecipePostCategory;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The type Recipe post dto.
 */
public class RecipePostDto implements Serializable {
    private String recipeText;
    private String dishName;
    private String picturePath;
    private double postRating;
    private LocalDateTime postDate;
    private RecipePostCategory recipePostCategory;
    private String userPicturePath;
    private String userLogin;
    private long userId;
    private long postId;

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
    public double getPostRating() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecipePostDto that = (RecipePostDto) o;

        if (Double.compare(that.postRating, postRating) != 0) return false;
        if (userId != that.userId) return false;
        if (postId != that.postId) return false;
        if (recipeText != null ? !recipeText.equals(that.recipeText) : that.recipeText != null) return false;
        if (dishName != null ? !dishName.equals(that.dishName) : that.dishName != null) return false;
        if (picturePath != null ? !picturePath.equals(that.picturePath) : that.picturePath != null) return false;
        if (postDate != null ? !postDate.equals(that.postDate) : that.postDate != null) return false;
        if (recipePostCategory != that.recipePostCategory) return false;
        if (userPicturePath != null ? !userPicturePath.equals(that.userPicturePath) : that.userPicturePath != null)
            return false;
        return userLogin != null ? userLogin.equals(that.userLogin) : that.userLogin == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = recipeText != null ? recipeText.hashCode() : 0;
        result = 31 * result + (dishName != null ? dishName.hashCode() : 0);
        result = 31 * result + (picturePath != null ? picturePath.hashCode() : 0);
        temp = Double.doubleToLongBits(postRating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (postDate != null ? postDate.hashCode() : 0);
        result = 31 * result + (recipePostCategory != null ? recipePostCategory.hashCode() : 0);
        result = 31 * result + (userPicturePath != null ? userPicturePath.hashCode() : 0);
        result = 31 * result + (userLogin != null ? userLogin.hashCode() : 0);
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (postId ^ (postId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RecipePostDto{");
        sb.append("recipeText='").append(recipeText).append('\'');
        sb.append(", picturePath='").append(picturePath).append('\'');
        sb.append(", postRating=").append(postRating);
        sb.append(", postDate=").append(postDate);
        sb.append(", recipePostCategory=").append(recipePostCategory);
        sb.append(", userPicturePath='").append(userPicturePath).append('\'');
        sb.append(", userLogin='").append(userLogin).append('\'');
        sb.append(", userId=").append(userId);
        sb.append('}');
        return sb.toString();
    }
}
