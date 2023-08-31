package com.synacy.leaveapplication.user

import spock.lang.Specification

class UserControllerSpec extends Specification {

    private UserService userService
    public UserController userController

    void setup() {
        userService = Mock(UserService)
        userController = new UserController(userService)
    }




}
