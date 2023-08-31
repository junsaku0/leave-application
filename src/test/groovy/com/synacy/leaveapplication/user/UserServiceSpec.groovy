package com.synacy.leaveapplication.user

import com.synacy.leaveapplication.UserRole
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

    def "should return a list of managers"() {
        given:
        List<Users> managerList = [Mock(Users), Mock(Users)]
        userRepository.findAllByRole(UserRole.MANAGER) >> managerList

        when:
        def result = userService.findAllManagers()

        then:
        managerList == result
        1 * userRepository.findAllByRole(UserRole.MANAGER) >> managerList
    }

    def "should return a list of employees"() {
        given:
        List<Users> employeeList = [Mock(Users), Mock(Users)]
        userRepository.findAllByRole(UserRole.EMPLOYEE) >> employeeList

        when:
        def result = userService.findAllEmployees()

        then:
        employeeList == result
        1 * userRepository.findAllByRole(UserRole.EMPLOYEE) >> employeeList
    }

    def "should return a list of admins"() {
        given:
        List<Users> adminList = [Mock(Users), Mock(Users)]
        userRepository.findAllByRole(UserRole.ADMIN) >> adminList

        when:
        def result = userService.findAllAdmins()

        then:
        adminList == result
        1 * userRepository.findAllByRole(UserRole.ADMIN) >> adminList
    }

    def "should return all users"() {
        given:
        List<Users> allUsers = [Mock(Users), Mock(Users)]
        userRepository.findAll() >> allUsers

        when:
        def result = userService.findAllUsers()

        then:
        allUsers == result
        1 * userRepository.findAll() >> allUsers
    }





    }

