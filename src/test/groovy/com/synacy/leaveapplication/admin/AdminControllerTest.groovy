package com.synacy.leaveapplication.admin

import com.synacy.leaveapplication.UserRole
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class AdminControllerTest extends Specification {

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
