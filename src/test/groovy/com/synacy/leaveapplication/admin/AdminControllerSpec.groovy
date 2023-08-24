package com.synacy.leaveapplication.admin

import com.synacy.leaveapplication.UserRole
import com.synacy.leaveapplication.user.UserController
import com.synacy.leaveapplication.user.UserResponse
import com.synacy.leaveapplication.user.UserService
import com.synacy.leaveapplication.user.Users
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class UserControllerSpec extends Specification {

    private UserService userService
    public UserController userController

    void setup() {
        this.userService = Mock(UserService)
        this.adminController = new UserController(this.adminService)
    }

    def "fetchUserList should return a response entity with the list of admins"() {
        given:
        List<Users> userListExpected = [Mock(Admin), Mock(Admin)]
        this.userService.getUserList() >> userListExpected

        when:
        ResponseEntity<List<Users>> userListResponse= this.userController.fetchUserList()

        then:
        HttpStatus.OK == userListResponse.getStatusCode()
        userListExpected == userListResponse.getBody()
    }

    def "fetchUserList should return a response entity with the correct adminUsers"() {
        given:
        List<Users> userListExpected = [Mock(Users)]
        this.userService.getUserList() >> userListExpected

        when:
        ResponseEntity<List<UserResponse>> UserResponseList = this.userController.fetchUserList()

        then:
        HttpStatus.OK == adminUserResponseList.getStatusCode()
        adminListExpected[0].getId() == adminUserResponseList.getBody()[0].getId()
        adminListExpected[0].getName() == adminUserResponseList.getBody()[0].getName()
        UserRole.ADMIN == adminUserResponseList.getBody()[0].getRole()
    }
}
