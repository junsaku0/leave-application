package com.synacy.leaveapplication.user;

import com.synacy.leaveapplication.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final List<Users> usersList;
    private final UserRepository userRepository;

    @Autowired
    public UserService(List<Users> usersList, UserRepository userRepository) {
        this.usersList = usersList;
        this.userRepository = userRepository;
//        this.initializeUser();
    }

    public Users createUser(UserDetails userDetails) {
        Users users = new Users(userDetails.getName(), userDetails.getRole(),
                    userRepository.findAllByName(userDetails.getHead()).get().getId(),
                userDetails.getHireDate(), userDetails.getTotalLeave());
        return userRepository.save(users);
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

}
