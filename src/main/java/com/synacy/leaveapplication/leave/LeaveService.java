package com.synacy.leaveapplication.leave;

import com.synacy.leaveapplication.user.UserDetails;
import com.synacy.leaveapplication.user.UserRepository;
import com.synacy.leaveapplication.user.UserResponse;
import com.synacy.leaveapplication.user.Users;
import com.synacy.leaveapplication.web.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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



}
