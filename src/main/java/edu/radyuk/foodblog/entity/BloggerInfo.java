package edu.radyuk.foodblog.entity;

import java.io.Serializable;

/**
 * The type Blogger info.
 */
public class BloggerInfo implements Serializable {
    private int bloggerAge;
    private String avatarPath;
    private String country;
    private String city;
    private String personalInfo;
    private long userId;

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
     * Gets blogger age.
     *
     * @return the blogger age
     */
    public int getBloggerAge() {
        return bloggerAge;
    }

    /**
     * Sets blogger age.
     *
     * @param bloggerAge the blogger age
     */
    public void setBloggerAge(int bloggerAge) {
        this.bloggerAge = bloggerAge;
    }

    /**
     * Gets avatar path.
     *
     * @return the avatar path
     */
    public String getAvatarPath() {
        return avatarPath;
    }

    /**
     * Sets avatar path.
     *
     * @param avatarPath the avatar path
     */
    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets personal info.
     *
     * @return the personal info
     */
    public String getPersonalInfo() {
        return personalInfo;
    }

    /**
     * Sets personal info.
     *
     * @param personalInfo the personal info
     */
    public void setPersonalInfo(String personalInfo) {
        this.personalInfo = personalInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BloggerInfo that = (BloggerInfo) o;
        return userId == that.userId
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
        result += result * 31 + Long.hashCode(userId);
        return result;
    }
}
