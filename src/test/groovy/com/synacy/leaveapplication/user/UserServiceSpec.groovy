package com.synacy.leaveapplication.user

import com.synacy.leaveapplication.leave.LeaveRepository
import com.synacy.leaveapplication.leave.LeaveService
import spock.lang.Specification

class UserServiceSpec extends Specification {

    private UserRepository userRepository;
    public UserService userService;

    void setup() {
        this.userRepository = Mock(UserRepository)
        this.userService = new UserService(this.userRepository)
    }


}
