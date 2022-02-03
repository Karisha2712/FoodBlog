package edu.radyuk.foodblog.entity;

import java.io.Serializable;

public enum UserStatus implements Serializable {
    ACTIVE,
    BLOCKED,
    AWAITING_CONFIRMATION
}
