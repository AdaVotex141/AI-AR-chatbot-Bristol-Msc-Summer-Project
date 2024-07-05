package com.example.glife.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserBadgeKey implements Serializable {
    private Long userId;
    private Long badgeId;

    // Default constructor
    public UserBadgeKey() {}

    // Constructor with parameters
    public UserBadgeKey(Long userId, Long badgeId) {
        this.userId = userId;
        this.badgeId = badgeId;
    }

    // equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserBadgeKey that = (UserBadgeKey) o;

        if (!userId.equals(that.userId)) return false;
        return badgeId.equals(that.badgeId);
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + badgeId.hashCode();
        return result;
    }
}
