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
    private List<Users> userList;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/user")
    public ResponseEntity<?> addUser(@RequestBody UserDetails userDetails) {
        if (userService.userExists(userDetails.getName())) {
            return new ResponseEntity<>("Username already exists!", HttpStatus.BAD_REQUEST);
        }
        Users users = userService.createUser(userDetails);
            return new ResponseEntity<> (users, HttpStatus.CREATED);
    }

    public ResponseEntity<List<UserResponse>> fetchEmployeeUserList() {
        List<Users> employeeList = userService.findAllEmployees();
        if (employeeList.isEmpty()) {
            throw new IllegalArgumentException("No employee found!");
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
}

