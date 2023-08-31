package com.synacy.leaveapplication.user;

import com.synacy.leaveapplication.UserRole;
import com.synacy.leaveapplication.web.apierror.LackingParameterException;
import com.synacy.leaveapplication.web.apierror.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Supplier;

@Service
public class UserService {

    private final List<Users> usersList;
    private final UserRepository userRepository;

    @Autowired
    public UserService(List<Users> usersList, UserRepository userRepository) {
        this.usersList = usersList;
        this.userRepository = userRepository;
        this.initializeUser();
    }

    public Users createUser(UserDetails userDetails) {
        missingParameterExists(userDetails);
        if (userNameExists(userDetails)) {
            throw new UserAlreadyExistsException("User name already exists!");
        } else {
            Users users = new Users(userDetails.getName(), userDetails.getRole(),
                    userRepository.findAllByName(userDetails.getHead()).get().getId(),
                    userDetails.getHireDate(), userDetails.getTotalLeave());
            return userRepository.save(users);
        }
    }

    private List<UserDetails> userList = new ArrayList<>();

    private boolean userNameExists(UserDetails userDetails) {
        return userList.stream()
                .anyMatch(existingUser -> existingUser.getName().equals(userDetails.getName()));
    }

    public List<Users> findAllManagers() {
        return userRepository.findAllByRole(UserRole.MANAGER);
    }

    public List<Users> findAllEmployees() {
        return userRepository.findAllByRole(UserRole.EMPLOYEE);
    }

    public List<Users> findAllAdmins() {
        return userRepository.findAllByRole(UserRole.ADMIN);
    }

    public List<Users> findAllUsers() {
        return userRepository.findAll();
    }

    private void initializeUser() {
        userRepository.saveAll(this.usersList);
    }

    public boolean userExists(String name) {
        return userRepository.findByName(name).isPresent();
    }

    private void missingParameterExists(UserDetails userDetails) {
        Map<String, Supplier<Object>> fieldCheckers = new HashMap<>() {{
            put("User Name", userDetails::getName);
            put("Role", userDetails::getRole);
            put("Head", userDetails::getHead);
            put("Hire date", userDetails::getHireDate);
            put("TotalLeave", userDetails::getTotalLeave);
        }};

        fieldCheckers.forEach((fieldName, supplier) -> {
            if (supplier.get() == null) {
                throw new LackingParameterException(fieldName + " cannot be null!");
            }
        });
    }


    public void updateUserLeave(Long userId, UserLeaveDetails userLeaveDetails) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        user.setEarnedLeave(userLeaveDetails.getEarnedLeave());
        user.setTotalLeaves(userLeaveDetails.getTotalLeaves());

        userRepository.save(user);


    }

}


