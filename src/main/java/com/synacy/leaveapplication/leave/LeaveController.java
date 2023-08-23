package com.synacy.leaveapplication.leave;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeaveController {

    private final LeaveService leaveService;

    @Autowired
    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @PostMapping("/api/v1/leave")
    public ResponseEntity<Leave> addLeave(@RequestBody LeaveDetails leaveDetails){
        Leave leave = leaveService.createLeave(leaveDetails);
        return new ResponseEntity<>(leave, HttpStatus.CREATED);
    }
}
