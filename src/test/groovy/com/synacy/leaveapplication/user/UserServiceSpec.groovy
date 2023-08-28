package com.synacy.leaveapplication.user

import com.synacy.leaveapplication.UserRole
import com.synacy.leaveapplication.leave.LeaveRepository
import com.synacy.leaveapplication.leave.LeaveService
import spock.lang.Specification

import java.time.LocalDate

class UserServiceSpec extends Specification {

    private UserRepository userRepository;
    public UserService userService;

    void setup() {
        this.userRepository = Mock(UserRepository)
        this.userService = new UserService([], this.userRepository)
    }

    def "createUser should save new user to repository with correct details"() {
        given:
        String name = 'name'
        UserRole role = UserRole.MANAGER
        String head = 'superAdmin'
        LocalDate hireDate = LocalDate.now()
        int totalLeave = 10

        UserDetails userDetails = Mock(UserDetails)
        userDetails.getName() >> name
        userDetails.getRole() >> role
        userDetails.getHead() >> head
        userDetails.getHireDate() >> hireDate
        userDetails.getTotalLeave() >> totalLeave

        Users userHead = Mock(Users)

        userRepository.findAllByName(head) >> Optional.of(userHead)

        when:
        userService.createUser(userDetails)

        then:
        1 * userRepository.save(_) >> {Users users ->
            assert name == users.getName()
            assert role == users.getRole()
            assert userHead.getId() == users.getHeadId()
            assert hireDate == users.getHireDate()
            assert totalLeave == users.getTotalLeaves()
        }

    }
}
