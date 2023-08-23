package com.synacy.leaveapplication.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/v1/user")
    public ResponseEntity<Users> addUser(@RequestBody UserDetails userDetails) {
        Users users = userService.createUser(userDetails);
        return new ResponseEntity<>(users, HttpStatus.CREATED);
    }

    @GetMapping("api/v1/user/manager")
    public ResponseEntity<List<UserResponse>> fetchManagerUserList() {
        List<Users> managerList = userService.findAllManagers();

        List<UserResponse> managerResponseList =
                managerList.stream().map(UserResponse::new)
                        .collect(Collectors.toList());

        return new ResponseEntity<>(managerResponseList, HttpStatus.OK);
    }

    @GetMapping ("/api/v1/user/employee")
    public ResponseEntity<List<UserResponse>> fetchEmployeeUserList (){
        List<Users> employeeList = userService.findAllEmployees();

        List<UserResponse> employeeResponseList = employeeList.stream().map(UserResponse::new).collect(Collectors.toList());

        return new ResponseEntity<>(employeeResponseList,HttpStatus.OK);
    }

    @GetMapping("api/v1/user/admin")
    public ResponseEntity<List<UserResponse>> fetchAdminUserList() {
        List<Users> adminList = userService.findAllAdmins();

        List<UserResponse> adminResponseList =
                adminList.stream().map(UserResponse::new)
                        .collect(Collectors.toList());

        return new ResponseEntity<>(adminResponseList, HttpStatus.OK);
    }
}
