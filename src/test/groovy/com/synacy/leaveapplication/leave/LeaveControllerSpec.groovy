package com.synacy.leaveapplication.leave

import com.synacy.leaveapplication.web.PageResponse
import org.springframework.data.domain.PageImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import java.time.LocalDate


class LeaveControllerSpec extends Specification {

    public LeaveService leaveService
    public LeaveController leaveController

    void setup() {
        leaveService = Mock(LeaveService)
        leaveController = new LeaveController(leaveService)
    }

    def "addLeave should return a CREATED http status code"() {
        expect:
        HttpStatus.CREATED == leaveController.addLeave(Mock(LeaveDetails)).getStatusCode()
    }

    def "addLeave should create leave from leave details with correct values"() {
        given:
        Leave leave = Mock(Leave)
        LeaveDetails leaveDetails = Mock(LeaveDetails)

        leaveService.createLeave(leaveDetails) >> leave

        when:
        ResponseEntity<Leave> response = leaveController.addLeave(leaveDetails)

        then:
        leave == response.getBody()
    }

    def "getMyLeave should return a paged response of personal leaves given an id"() {
        given:
        Leave leave = Mock(Leave)
        leaveService.fetchLeaveByNameAndRole(2L, 5, 1) >> new PageImpl<Leave>([leave])

        when:
        PageResponse<LeaveResponse> actualResponse = leaveController.getMyLeave(2L, 5, 1)

        then:
        leave.getName() == actualResponse.getContent()[0].getName()
        leave.getFileDate() == actualResponse.getContent()[0].getFileDate()
        leave.getStartDate() == actualResponse.getContent()[0].getStartDate()
        leave.getEndDate() == actualResponse.getContent()[0].getEndDate()
        leave.getDuration() == actualResponse.getContent()[0].getDuration()
        leave.getReason() == actualResponse.getContent()[0].getReason()
        leave.getStatus() == actualResponse.getContent()[0].getStatus()
    }

    def "getLeaveByHead should return a paged response of leaves given a head id"() {
        given:
        Leave leave = Mock(Leave)
        leaveService.fetchLeaveByHeadId(1L, 5, 1) >> new PageImpl<Leave>([leave])

        when:
        PageResponse<LeaveResponse> actualResponse = leaveController.getLeaveByHead(1L, 5, 1)

        then:
        leave.getName() == actualResponse.getContent()[0].getName()
        leave.getFileDate() == actualResponse.getContent()[0].getFileDate()
        leave.getStartDate() == actualResponse.getContent()[0].getStartDate()
        leave.getEndDate() == actualResponse.getContent()[0].getEndDate()
        leave.getDuration() == actualResponse.getContent()[0].getDuration()
        leave.getReason() == actualResponse.getContent()[0].getReason()
        leave.getStatus() == actualResponse.getContent()[0].getStatus()
    }

    def "getAllLeave should return a paged response of all leaves"() {
        given:
        List<Leave> leaves = [Mock(Leave), Mock(Leave)]
        leaveService.fetchAllLeave(5, 1) >> new PageImpl<Leave>(leaves)

        when:
        PageResponse<LeaveResponse> actualResponse = leaveController.getAllLeave(5, 1)

        then:
        leaves.size() == actualResponse.getContent().size()
    }
    def "addLeave should throw an error if the leave already exist on the same day "(){
        given:
        Leave leave = Mock(Leave)
        LeaveDetails leaveDetails = Mock(LeaveDetails)
        LocalDate date = LocalDate.now()
        leaveDetails.getStartDate() >> date
        leaveService.invalidEndDate(_,_) >> false
        leaveService.leaveExist(date) >> true

        leaveService.createLeave(leaveDetails) >> leave

        when:
        ResponseEntity<Leave> response = leaveController.addLeave(leaveDetails)

        then:
        HttpStatus.BAD_REQUEST == response.getStatusCode()
    }
    def "addLeave should throw an error if the endDate is before the startrDate"() {
        given:
        Leave leave = Mock(Leave)
        LeaveDetails leaveDetails = Mock(LeaveDetails)
        LocalDate date = LocalDate.now()
        leaveDetails.getStartDate() >> date
        leaveService.invalidEndDate(_,_) >> true

        leaveService.createLeave(leaveDetails) >> leave

        when:
        ResponseEntity<Leave> response = leaveController.addLeave(leaveDetails)

        then:
        HttpStatus.BAD_REQUEST == response.getStatusCode()
    }


}
