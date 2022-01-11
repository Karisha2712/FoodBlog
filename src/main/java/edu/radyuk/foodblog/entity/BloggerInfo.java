package edu.radyuk.foodblog.entity;

public class BloggerInfo {
    private String avatarPath;
    private String country;
    private String city;
    private String personalInfo;
    private long userId;

    public BloggerInfo() {
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
        BloggerInfo that = (BloggerInfo) o;
        return userId == that.userId
                && avatarPath.equals(that.avatarPath)
                && country.equals(that.country)
                && city.equals(that.city)
                && personalInfo.equals(that.personalInfo);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result += result * 31 + city.hashCode();
        result += result * 31 + country.hashCode();
        result += result * 31 + avatarPath.hashCode();
        result += result * 31 + personalInfo.hashCode();
        result += result * 31 + Long.hashCode(userId);
        return result;
    }
}
