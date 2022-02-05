package edu.radyuk.foodblog.entity;

import java.io.Serializable;

/**
 * The type User.
 */
public class User extends AbstractEntity implements Serializable {
    private String login;
    private String email;
    private String passwordHash;
    private UserRole userRole;
    private UserStatus userStatus;

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets password hash.
     *
     * @return the password hash
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Sets password hash.
     *
     * @param passwordHash the password hash
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Gets user role.
     *
     * @return the user role
     */
    public UserRole getUserRole() {
        return userRole;
    }

    /**
     * Sets user role.
     *
     * @param userRole the user role
     */
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    /**
     * Gets user status.
     *
     * @return the user status
     */
    public UserStatus getUserStatus() {
        return userStatus;
    }

    /**
     * Sets user status.
     *
     * @param userStatus the user status
     */
    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return login.equals(user.login)
                && email.equals(user.email)
                && passwordHash.equals(user.passwordHash)
                && userRole == user.userRole
                && userStatus == user.userStatus;
    }

    @Override
    public int hashCode() {
        int result = 1;
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
