package edu.radyuk.foodblog.entity;

import java.io.Serializable;

public class BloggerInfo implements Serializable {
    private int bloggerAge;
    private String avatarPath;
    private String country;
    private String city;
    private String personalInfo;
    private String userLogin;

    public BloggerInfo() {
    }

    public int getBloggerAge() {
        return bloggerAge;
    }

    public void setBloggerAge(int bloggerAge) {
        this.bloggerAge = bloggerAge;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(String personalInfo) {
        this.personalInfo = personalInfo;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BloggerInfo that = (BloggerInfo) o;
        return userLogin.equals(that.userLogin)
                && avatarPath.equals(that.avatarPath)
                && country.equals(that.country)
                && city.equals(that.city)
                && personalInfo.equals(that.personalInfo);
    }

    @Override
    public int hashCode() {
        int result;
        result = city.hashCode();
        result += result * 31 + country.hashCode();
        result += result * 31 + avatarPath.hashCode();
        result += result * 31 + personalInfo.hashCode();
        result += result * 31 + userLogin.hashCode();
        return result;
    }
}
