package com.synacy.leaveapplication.Manager

import spock.lang.Specification

class ManagerServiceTest extends Specification {

    ManagerService managerService = Mock(ManagerService)
    ManagerRepository managerRepository = Mock(ManagerRepository)


    void setup() {
        managerService = new ManagerService(managerRepository)
    }

    def "CreateManager should test if the values were created"() {
        given:
        ManagerDetails managerDetails = new ManagerDetails("John Doe", new Date(), 10)

        when:
        Manager createdManager = managerService.createManager(managerDetails)

        then:
        1 * managerRepository.save(_ as Manager) >> { Manager validate ->
            assert managerDetails.getName() == validate.getName()
            assert managerDetails.getHireDate() == validate.getHireDate()
            assert managerDetails.getTotalLeave() == validate.getTotalLeave()
        }

    }

    def "FindAllManagers"() {
    }

    def "GetManagerById"() {
    }

    def "UpdateManager Should Update a existing product"() {
        given:
        Long managerId = 1L
        String name = "John"
        int totalLeave = 10
        int currentLeave = 5
        Manager existingManager = new Manager("John Doe", new Date(), 5)
        existingManager.setId(managerId)

        // Mock the managerRepository to return the existing manager
        managerRepository.findById(managerId) >> Optional.of(existingManager)
        managerRepository.save(existingManager) >> existingManager

        when:
        Manager updatedManager = managerService.updateManager(managerId, name, totalLeave, currentLeave)

        then:
        1 * managerRepository.findById(managerId)
        1 * managerRepository.save(existingManager)

        updatedManager.name == name
        updatedManager.totalLeave == totalLeave
        updatedManager.currentLeave == currentLeave
    }
    }


