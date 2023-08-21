package com.synacy.leaveapplication.admin;

import com.synacy.leaveapplication.UserRole;
import lombok.Getter;


public class AdminUserResponse {

    @Getter
    private Long id;

    @Getter
    private String name;

    @Getter
    private UserRole role;

    public AdminUserResponse(Admin admin) {
        this.id = admin.getId();
        this.name = admin.getName();
        this.role = UserRole.ADMIN;
    }
}
