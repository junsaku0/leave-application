package com.synacy.leaveapplication.user
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class UserControllerSpec extends Specification {

    private UserService userService
    public UserController userController

    void setup() {
        userService = Mock(UserService)
        userController = new UserController(userService)
    }

    def "addLeave should return a CREATED http status code"() {
        expect:
        HttpStatus.CREATED == userController.addUser(Mock(UserDetails)).getStatusCode()
    }

    def "addLeave should create leave from leave details with correct values"() {
        given:
        Users users = Mock(Users)
        UserDetails userDetails = Mock(UserDetails)

        userService.createUser(userDetails) >> users

        when:
        ResponseEntity<Users> response = userController.addUser(userDetails)

        then:
        users == response.getBody()
    }

    def "fetchManagerUserList should return a list of manager user responses"() {
        given:
        List<Users> managerList = [Mock(Users), Mock(Users)]
        userService.findAllManagers() >> managerList

        when:
        ResponseEntity<List<UserResponse>> response = userController.fetchManagerUserList()

        then:
        response.statusCode == HttpStatus.OK
        response.body.size() == managerList.size()
    }

    def "fetchEmployeeUserList should return a list of employee user responses"() {
        given:
        List<Users> employeeList = [Mock(Users), Mock(Users)]
        userService.findAllEmployees() >> employeeList

        when:
        ResponseEntity<List<UserResponse>> response = userController.fetchEmployeeUserList()

        then:
        response.statusCode == HttpStatus.OK
        response.body.size() == employeeList.size()
    }

    def "fetchAdminUserList should return a list of admin user responses"() {
        given:
        List<Users> adminList = [Mock(Users), Mock(Users)]
        userService.findAllAdmins() >> adminList

        when:
        ResponseEntity<List<UserResponse>> response = userController.fetchAdminUserList()

        then:
        response.statusCode == HttpStatus.OK
        response.body.size() == adminList.size()
    }

    def "fetchUserList should return a list of all user responses"() {
        given:
        List<Users> userList = [Mock(Users), Mock(Users)]
        userService.findAllUsers() >> userList

        when:
        ResponseEntity<List<UserResponse>> response = userController.fetchUserList()

        then:
        response.statusCode == HttpStatus.OK
        response.body.size() == userList.size()
    }







}
