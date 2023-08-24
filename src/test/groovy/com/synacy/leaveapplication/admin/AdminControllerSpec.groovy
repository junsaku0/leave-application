package com.synacy.leaveapplication.admin

<<<<<<< HEAD
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

    private AdminService adminService
    public AdminController adminController

    void setup() {
        this.adminService = Mock(AdminService)
        this.adminController = new AdminController(this.adminService)
    }

    def "fetchAdminList should return a response entity with the list of admins"() {
        given:
        List<Admin> adminListExpected = [Mock(Admin), Mock(Admin)]
        this.adminService.getAdminList() >> adminListExpected

        when:
        ResponseEntity<List<Admin>> adminListResponse= this.adminController.fetchAdminList()

        then:
        HttpStatus.OK == adminListResponse.getStatusCode()
        adminListExpected == adminListResponse.getBody()
    }

    def "fetchAdminUserList should return a response entity with the correct adminUsers"() {
        given:
        List<Admin> adminListExpected = [Mock(Admin)]
        this.adminService.getAdminList() >> adminListExpected

        when:
        ResponseEntity<List<AdminUserResponse>> adminUserResponseList = this.adminController.fetchAdminUserList()

        then:
        HttpStatus.OK == adminUserResponseList.getStatusCode()
        adminListExpected[0].getId() == adminUserResponseList.getBody()[0].getId()
        adminListExpected[0].getName() == adminUserResponseList.getBody()[0].getName()
        UserRole.ADMIN == adminUserResponseList.getBody()[0].getRole()
    }

}
