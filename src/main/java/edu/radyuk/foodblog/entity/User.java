package edu.radyuk.foodblog.entity;

public class User extends AbstractEntity {
    private String login;
    private String email;
    private String passwordHash;
    private UserRole userRole;
    private UserStatus userStatus;

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return login.equals(user.login)
                && email.equals(user.email)
                && passwordHash.equals(user.passwordHash)
                && userRole == user.userRole
                && userStatus == user.userStatus;
    }

    @Override
    public int hashCode() {
        int result;
        result = super.hashCode();
        result += result * 31 + login.hashCode();
        result += result * 31 + email.hashCode();
        result += result * 31 + passwordHash.hashCode();
        result += result * 31 + userRole.hashCode();
        result += result * 31 + userStatus.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("login='").append(login).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", passwordHash='").append(passwordHash).append('\'');
        sb.append(", userRole=").append(userRole);
        sb.append(", userStatus=").append(userStatus);
        sb.append('}');
        return sb.toString();
    }
}
