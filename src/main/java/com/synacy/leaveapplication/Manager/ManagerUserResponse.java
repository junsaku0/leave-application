package com.synacy.leaveapplication.Manager;

import com.synacy.leaveapplication.UserRole;
import com.synacy.leaveapplication.admin.Admin;
import lombok.Getter;

public class ManagerUserResponse {

    @Getter
    private Long id;

    @Getter
    private String name;

    @Getter
    private UserRole role;

    public ManagerUserResponse(Manager manager) {
        this.id = manager.getId();
        this.name = manager.getName();
        this.role = UserRole.MANAGER;
    }
}
