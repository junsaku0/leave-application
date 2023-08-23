package com.synacy.leaveapplication.leave

import com.synacy.leaveapplication.UserRole
import spock.lang.Specification

class LeaveServiceSpec extends Specification {

    private LeaveRepository leaveRepository;
    public LeaveService leaveService;

    void setup() {
        this.leaveRepository = Mock(LeaveRepository)
        this.leaveService = new LeaveService(this.leaveRepository)
    }

    def "createLeave should save leave details to repository"() {
        given:
        String name = 'sample'
        UserRole role = UserRole.MANAGER
        Date startDate = new Date()
        Date endDate = new Date()
        String reason = 'reason'

        LeaveDetails leaveDetails = Mock(LeaveDetails)
        leaveDetails.getName() >> name
        leaveDetails.getRole() >> role
        leaveDetails.getEndDate() >> endDate
        leaveDetails.getStartDate() >> startDate
        leaveDetails.getReason() >> reason

        when:
        leaveService.createLeave(leaveDetails)

        then:
        1 * leaveRepository.save(_) >> { Leave actualLeave ->
            assert name == actualLeave.getName()
            assert role == actualLeave.getRole()
            assert startDate == actualLeave.getStartDate()
            assert endDate == actualLeave.getEndDate()
            assert reason == actualLeave.getReason()
        }
    }


}
