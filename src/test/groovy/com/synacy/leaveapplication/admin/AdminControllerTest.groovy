package com.synacy.leaveapplication.admin

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
}
