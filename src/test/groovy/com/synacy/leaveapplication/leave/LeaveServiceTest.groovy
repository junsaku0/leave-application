
package com.synacy.leaveapplication.leave

import com.synacy.leaveapplication.UserRole
import com.synacy.leaveapplication.leave.DateValidator.DateValidator
import com.synacy.leaveapplication.user.UserRepository
import com.synacy.leaveapplication.user.Users
import com.synacy.leaveapplication.web.apierror.ExceededLeaveBalanceException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import spock.lang.Specification

import java.time.LocalDate

class LeaveServiceSpec extends Specification {


    private LeaveRepository leaveRepository
    private UserRepository userRepository
    public LeaveService leaveService
    DateValidator validator = new DateValidator()

    void setup() {
        leaveRepository = Mock(LeaveRepository)
        userRepository = Mock(UserRepository)
        leaveService = new LeaveService(leaveRepository, userRepository)
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

    def "fetchLeaveByNameAndRole should return a page of leaves given a user id"() {
        given:
        Users user = new Users(id: 1L)
        Leave leave = Mock(Leave)

        userRepository.findAllById(user.getId()) >> Optional.of(user)
        leaveRepository.findAllByNameAndRole(user.getName(), user.getRole(),
                _ as Pageable) >> new PageImpl<>([leave])

        when:
        Page<Leave> actualLeave = leaveService.fetchLeaveByNameAndRole(user.getId(), 1, 1)

        then:
        leave == actualLeave.getContent()[0]
    }

    def "fetchLeaveByHeadId should return a page of leaves given a manager/head id"() {
        given:
        Users user = new Users(id: 2L, headId: 1L)
        Leave leave = Mock(Leave)

        leaveRepository.findLeaveByHeadId(user.getHeadId(), _ as Pageable) >> new PageImpl<>([leave])

        when:
        Page<Leave> actualLeave = leaveService.fetchLeaveByHeadId(user.getHeadId(), 1, 1)

        then:
        leave == actualLeave.getContent()[0]
    }

    def "fetchAllLeave should return a paged response of all leaves"() {
        given:
        List<Leave> leaves = [Mock(Leave), Mock(Leave)]

        leaveRepository.findAll(_ as Pageable) >> new PageImpl<>(leaves)

        when:
        Page<Leave> actualLeaves = leaveService.fetchAllLeave(5, 1)

        then:
        leaves == actualLeaves.getContent()
    }

    def "leaveExist should return false if leave does not exist"() {
        given:
        Long userId = 1L
        LocalDate startDate = LocalDate.of(2023, 9, 1)

        when:
        boolean result = leaveService.leaveExist(startDate, userId)

        then:
        1 * leaveRepository.findByUserIdAndStartDateAndStatusNotAndStatusNot(userId, startDate, LeaveStatus.CANCELLED, LeaveStatus.REJECTED) >> Optional.empty()

    }

    def "leaveExist should return true if leave exists and is neither cancelled or rejected"() {
        given:
        Long userId = 1L
        LocalDate startDate = LocalDate.of(2023, 9, 1)
        Leave existingLeave = new Leave()

        when:
        boolean result = leaveService.leaveExist(startDate, userId)

        then:
        1 * leaveRepository.findByUserIdAndStartDateAndStatusNotAndStatusNot(userId, startDate, LeaveStatus.CANCELLED, LeaveStatus.REJECTED) >> Optional.of(existingLeave)

    }

    def "invalidEndDate should return true when endDate that is earlier than start date"() {

        given:
        LocalDate startDate = LocalDate.of(2022, 12, 31)
        LocalDate endDate = LocalDate.of(2022, 12, 30)

        expect: "invalidEndDate to return true"
        validator.invalidEndDate(startDate, endDate)

    }

    def "invalidEndDate should return false when endDate is after or equals startDate"() {
        given:
        LocalDate startDate = LocalDate.of(2023, 9, 1)
        LocalDate endDate = LocalDate.of(2023, 9, 2)

        expect: "invalidEndDate to return false"
        !validator.invalidEndDate(startDate, endDate)
    }



    def "insufficientLeave should throw an error when leave balance is negative"() {
        given:
        LeaveDetails leaveDetails = Mock(LeaveDetails)
        Users users = Mock(Users)

        leaveDetails.getUserId() >> "userId"

//        userRepository.findAllById("userId) >> Optional.of(users)

        leaveService.getEarnedLeaveBalance(users, _) >> -1

        when:
        leaveService.insufficientLeave(leaveDetails)

        then:
        thrown(ExceededLeaveBalanceException)
    }


}