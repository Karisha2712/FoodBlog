package edu.radyuk.foodblog.entity.dto;

public class BloggerInfoDto {
    private int bloggerAge;
    private String avatarPath;
    private String country;
    private String city;
    private String personalInfo;
    private String userLogin;
    private long userId;

    public BloggerInfoDto() {
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

        BloggerInfoDto that = (BloggerInfoDto) o;

        if (bloggerAge != that.bloggerAge) return false;
        if (userId != that.userId) return false;
        if (avatarPath != null ? !avatarPath.equals(that.avatarPath) : that.avatarPath != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (personalInfo != null ? !personalInfo.equals(that.personalInfo) : that.personalInfo != null) return false;
        return userLogin != null ? userLogin.equals(that.userLogin) : that.userLogin == null;
    }

    @Override
    public int hashCode() {
        int result = bloggerAge;
        result = 31 * result + (avatarPath != null ? avatarPath.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (personalInfo != null ? personalInfo.hashCode() : 0);
        result = 31 * result + (userLogin != null ? userLogin.hashCode() : 0);
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BloggerInfoDto{");
        sb.append("bloggerAge=").append(bloggerAge);
        sb.append(", avatarPath='").append(avatarPath).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", personalInfo='").append(personalInfo).append('\'');
        sb.append(", userLogin='").append(userLogin).append('\'');
        sb.append(", userId=").append(userId);
        sb.append('}');
        return sb.toString();
    }
}
