package com.example.glife.entity;

import lombok.Data;

@Data
public class UserMessageStatus {
    private String userID;

    private String randomTaskID;

    private boolean read;

    private boolean notInterested;
}
