package com.synacy.leaveapplication.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        if (managerList.isEmpty()) {
            throw new IllegalArgumentException("No manager found");
        }
        List<UserResponse> managerResponseList =
                managerList.stream().map(UserResponse::new)
                        .collect(Collectors.toList());
        return new ResponseEntity<>(managerResponseList, HttpStatus.OK);
    }

    @GetMapping("/api/v1/user/employee")
    public ResponseEntity<List<UserResponse>> fetchEmployeeUserList() {
        List<Users> employeeList = userService.findAllEmployees();
        if (employeeList.isEmpty()) {
            throw new IllegalArgumentException("No employee found");
        }
        List<UserResponse> employeeResponseList = employeeList.stream().map(UserResponse::new).collect(Collectors.toList());
        return new ResponseEntity<>(employeeResponseList, HttpStatus.OK);
    }

    @GetMapping("api/v1/user/admin")
    public ResponseEntity<List<UserResponse>> fetchAdminUserList() {
        List<Users> adminList = userService.findAllAdmins();
        List<UserResponse> adminResponseList =
                adminList.stream().map(UserResponse::new)
                        .collect(Collectors.toList());
        return new ResponseEntity<>(adminResponseList, HttpStatus.OK);
    }

    @GetMapping("api/v1/user")
    public ResponseEntity<List<UserResponse>> fetchUserList () {
            List<Users> userList = userService.findAllUsers();
        List<UserResponse> userResponseList =
                userList.stream().map(UserResponse::new)
                        .collect(Collectors.toList());
        return new ResponseEntity<>(userResponseList, HttpStatus.OK);
    }

    @GetMapping("api/v1/manager")
    public ResponseEntity<List<Users>> fetchManagerList() {
        List<Users> managerList = userService.findAllManagers();
        return new ResponseEntity<>(managerList, HttpStatus.OK);
    }

    @GetMapping("api/v1/employee")
    public ResponseEntity<List<Users>> fetchEmployeeList() {
        List<Users> employeeList = userService.findAllEmployees();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @GetMapping("api/v1/user/{id}")
    public ResponseEntity<Users> fetchUserById(
            @PathVariable Long id
    ) {
        Users users = userService.findUserById(id);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
