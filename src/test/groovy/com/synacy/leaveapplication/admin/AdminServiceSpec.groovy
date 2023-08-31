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

//    public List<Admin> adminList;
//    private AdminRepository adminRepository;
//    public AdminService adminService;
//
//    void setup() {
//        this.adminList = new ArrayList<>()
//        this.adminRepository = Mock(AdminRepository)
//        this.adminService = new AdminService(this.adminList, this.adminRepository)
//    }
//
//    def "getAdminList should return the list of admin users"() {
//        given:
//        List<Admin> adminListExpected = [Mock(Admin), Mock(Admin)]
//        this.adminRepository.findAll() >> adminListExpected
//
//
//        when:
//        List<Admin> adminListActual = this.adminService.getAdminList()
//
//        then:
//        adminListExpected == adminListActual
//    }

}
