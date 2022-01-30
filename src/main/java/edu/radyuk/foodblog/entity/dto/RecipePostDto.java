package edu.radyuk.foodblog.entity.dto;

import edu.radyuk.foodblog.entity.RecipePostCategory;

import java.io.Serializable;
import java.time.LocalDateTime;

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

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
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

    public double getPostRating() {
        return postRating;
    }

    public void setPostRating(Double postRating) {
        this.postRating = postRating;
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

    public String getUserPicturePath() {
        return userPicturePath;
    }

    public void setUserPicturePath(String userPicturePath) {
        this.userPicturePath = userPicturePath;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public long getUserId() {
        return userId;
    }

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
