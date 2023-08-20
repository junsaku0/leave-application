package com.synacy.leaveapplication.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("api/v1/admin")
    public ResponseEntity<List<Admin>> fetchAdminList() {
        return new ResponseEntity<>(adminService.getAdminList(), HttpStatus.OK);
    }

    @GetMapping("api/v1/user/admin")
    public ResponseEntity<List<AdminUserResponse>> fetchAdminUserList() {
        List<Admin> adminList = adminService.getAdminList();

        List<AdminUserResponse> adminResponseList =
                adminList.stream().map(AdminUserResponse::new)
                        .collect(Collectors.toList());

        return new ResponseEntity<>(adminResponseList, HttpStatus.OK);
    }
}
