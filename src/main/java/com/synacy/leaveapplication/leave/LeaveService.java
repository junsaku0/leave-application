package com.synacy.leaveapplication.leave;

import com.synacy.leaveapplication.user.UserRepository;
import com.synacy.leaveapplication.user.Users;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;
    private final UserRepository userRepository;

    @Autowired
    public LeaveService(LeaveRepository leaveRepository, UserRepository userRepository) {
        this.leaveRepository = leaveRepository;
        this.userRepository = userRepository;
    }

    public Leave createLeave(LeaveDetails leaveDetails) {
        Leave leave = new Leave(leaveDetails.getUserId(), leaveDetails.getName(), leaveDetails.getRole(),
                leaveDetails.getStartDate(), leaveDetails.getEndDate(),
                leaveDetails.getReason());
        return this.leaveRepository.save(leave);
    }

    public Page<Leave> fetchLeaveByNameAndRole(Long id, int max, int page) {
        int offset = page - 1;
        Pageable pageable = PageRequest.of(offset, max);

        Users users = userRepository.findAllById(id).get();

        return leaveRepository.findAllByNameAndRole(users.getName(), users.getRole(), pageable);
    }

    public Page<Leave> fetchLeaveByHeadId(Long headId, int max, int page) {
        int offset = page - 1;
        Pageable pageable = PageRequest.of(offset, max);

        return leaveRepository.findLeaveByHeadId(headId, pageable);
    }

    public Page<Leave> fetchAllLeave(int max, int page) {
        int offset = page - 1;
        Pageable pageable = PageRequest.of(offset, max);

        return leaveRepository.findAll(pageable);
    }

    public Leave updateLeave(Long leaveId, LeaveStatus status) {
        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new IllegalArgumentException("Leave not found with ID: " + leaveId));

        leave.setStatus(status);
        updateEarnedLeave(leave);
        return leaveRepository.save(leave);
    }

    private void updateEarnedLeave(Leave leave){
        Users users = userRepository.findAllById(leave.getUserId()).get();
        if (leave.getStatus() == LeaveStatus.PENDING) {
            users.setEarnedLeave(users.getEarnedLeave() + leave.getDuration());
        } else if (leave.getStatus() == LeaveStatus.REJECTED || leave.getStatus() == LeaveStatus.CANCELLED) {
            users.setEarnedLeave(users.getEarnedLeave() - leave.getDuration());
        }
        userRepository.save(users);
    }

    public boolean leaveExist(LocalDate startDate) {
        Optional<Leave> existingLeave = leaveRepository.findByStartDate(startDate);

        return existingLeave.isPresent();
    }
    public boolean invalidEndDate(LocalDate startDate, LocalDate endDate) {
        return endDate.isBefore(startDate);
    }
    public boolean isFromCurrentMonth(LocalDate date) {
        LocalDate now = LocalDate.now();
        return now.getMonth() == date.getMonth() && now.getYear() == date.getYear();
    }
}
