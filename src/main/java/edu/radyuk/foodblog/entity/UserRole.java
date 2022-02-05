package edu.radyuk.foodblog.entity;

import java.io.Serializable;

/**
 * The enum User role.
 */
public enum UserRole implements Serializable {
    /**
     * Unauthorised user role.
     */
    UNAUTHORISED,
    /**
     * Blogger user role.
     */
    BLOGGER,
    /**
     * Admin user role.
     */
    ADMIN
}
