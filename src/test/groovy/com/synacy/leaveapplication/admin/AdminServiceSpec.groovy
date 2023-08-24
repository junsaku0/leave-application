package com.synacy.leaveapplication.admin

import com.synacy.leaveapplication.user.UserRepository
import com.synacy.leaveapplication.user.UserService
import com.synacy.leaveapplication.user.Users
import spock.lang.Specification

class AdminServiceSpec extends Specification {

//    public List<Users> userList
    private UserRepository userRepository
    public UserService userService

    void setup() {
//        this.userList = new ArrayList<>()
        this.userRepository = Mock(UserRepository)
//        this.userService = new UserService(this.userList,this.userRepository)
    }

    def "getUserList should return the list of users"() {
        given:
        List<Users> userListExpected = [Mock(Users), Mock(Users)]
        this.userRepository.findAll() >> usersListExpected


        when:
        List<Users> userListActual = this.userService.getUserList()

        then:
        userListExpected == userListActual
    }
}
