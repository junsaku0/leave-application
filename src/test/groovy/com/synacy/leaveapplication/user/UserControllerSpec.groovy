package com.synacy.leaveapplication.user

import com.synacy.leaveapplication.UserRole
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class UserControllerSpec extends Specification {

    private UserService userService
    public UserController userController

    void setup() {
        this.userService = Mock(UserService)
        this.userController = new UserController(this.userService)
    }

//    def "fetchAdminUserList should return a response entity with the correct adminUsers"() {
//        given:
//        List<Users> adminListExpected = [Mock(Users)]
//        this.userService.getAdminList() >> adminListExpected
//
//        when:
//        ResponseEntity<List<UserResponse>> userResponseList = this.userController.fetchAdminUserList()
//
//        then:
//        HttpStatus.OK == userResponseList.getStatusCode()
//        adminListExpected[0].getId() == userResponseList.getBody()[0].getId()
//        adminListExpected[0].getName() == userResponseList.getBody()[0].getName()
//        UserRole.ADMIN == userResponseList.getBody()[0].getRole()
//    }
}
