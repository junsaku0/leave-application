package com.synacy.leaveapplication.user;

import com.synacy.leaveapplication.UserRole;
import lombok.Getter;

public class UserResponse {

    @Getter
    private Long id;

    @Getter
    private String name;

    @Getter
    private UserRole role;

    public UserResponse(Users users) {
        this.id = users.getId();
        this.name = users.getName();
        this.role = users.getRole();
    }
}
