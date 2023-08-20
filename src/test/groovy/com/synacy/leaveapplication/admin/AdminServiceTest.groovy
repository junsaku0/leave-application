package com.synacy.leaveapplication.admin

import spock.lang.Specification

class AdminServiceTest extends Specification {

    public List<Admin> adminList;
    private AdminRepository adminRepository;
    public AdminService adminService;

    void setup() {
        this.adminList = new ArrayList<>()
        this.adminRepository = Mock(AdminRepository)
        this.adminService = new AdminService(this.adminList, this.adminRepository)
    }

    def "getAdminList should return the list of admin users"() {
        given:
        List<Admin> adminListExpected = [Mock(Admin), Mock(Admin)]
        this.adminRepository.findAll() >> adminListExpected


        when:
        List<Admin> adminListActual = this.adminService.getAdminList()

        then:
        adminListExpected == adminListActual
    }
}
