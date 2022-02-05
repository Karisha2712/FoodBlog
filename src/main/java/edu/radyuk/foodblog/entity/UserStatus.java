package edu.radyuk.foodblog.entity;

import java.io.Serializable;

/**
 * The enum User status.
 */
public enum UserStatus implements Serializable {
    /**
     * Active user status.
     */
    ACTIVE,
    /**
     * Blocked user status.
     */
    BLOCKED,
    /**
     * Awaiting confirmation user status.
     */
    AWAITING_CONFIRMATION
}
