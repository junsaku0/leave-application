package com.synacy.leaveapplication.Manager

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification


@SpringBootTest
class ManagerServiceTest extends Specification {

    ManagerService managerService
    ManagerRepository managerRepository = Mock(ManagerRepository)


    def setup() {
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

    def "FindAllManagers Should return a list of all managers"() {
        given:
        List<Manager> expectedManagers = [
                new Manager("John Doe", new Date(), 5),
                new Manager("Jane Smith", new Date(), 7)
        ]

        managerRepository.findAll() >> expectedManagers

        when:
        List<Manager> actualManagers = managerService.findAllManagers()

        then:
        actualManagers.size() == expectedManagers.size()
        actualManagers.containsAll(expectedManagers)


    }

    def "GetManagerById Should return the existing manager for the given id"() {
        given:
        Long managerId = 1L
        Manager expectedManager = new Manager("John Doe", new Date(), 5)
        managerRepository.findManagerById(managerId) >> Optional.of(expectedManager)
        expectedManager.setId(managerId)

        managerRepository.findManagerById(managerId) >> Optional.of(expectedManager)


        when:
        Manager actual = managerService.getManagerById(managerId)

        then:
        actual != null
        managerId == actual.getId()
    }

    def "GetManagerById Should throw an exception for a non-existing manager ID"() {
        given:
        Long managerId = 10L

        // Mock the managerRepository to return an empty Optional
        managerRepository.findManagerById(managerId) >> Optional.empty()

        when:
        def exception = null
        try {
            managerService.getManagerById(managerId)
        } catch (IllegalArgumentException e) {
            exception = e
        }

        then:
        exception != null
        exception.message == "Manager not found with id: " + managerId
    }



    def "UpdateManager Should Update a existing product"() {
        given:
        Long managerId = 1L
        String name = "John"
        int totalLeave = 10
        int currentLeave = 5
        Manager existingManager = new Manager("John Doe", new Date(), 5)
        existingManager.setId(managerId)

        managerRepository.findById(managerId) >> Optional.of(existingManager)
        managerRepository.save(existingManager) >> existingManager

        when:
        Manager updatedManager = managerService.updateManager(managerId, name, totalLeave, currentLeave)

        then:
        1 * managerRepository.findById(managerId) >> Optional.of(existingManager)
        1 * managerRepository.save(existingManager) >> existingManager

        updatedManager.name == name
        updatedManager.totalLeave == totalLeave
        updatedManager.currentLeave == currentLeave
    }
    }


