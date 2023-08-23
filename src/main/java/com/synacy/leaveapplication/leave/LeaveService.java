package com.synacy.leaveapplication.leave;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;

    @Autowired
    public LeaveService(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    public Leave createLeave(LeaveDetails leaveDetails) {
        Leave leave = new Leave(leaveDetails.getName(), leaveDetails.getRole(),
                leaveDetails.getStartDate(), leaveDetails.getEndDate(),
                leaveDetails.getReason());
        return this.leaveRepository.save(leave);
    }

//    public List<Leave> findEmployeeLeave() {
//
//        return null;
//    }



}
