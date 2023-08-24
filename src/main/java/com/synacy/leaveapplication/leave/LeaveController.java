package com.synacy.leaveapplication.leave;

import com.synacy.leaveapplication.web.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("api/v1/leave/{id}")
    public PageResponse<LeaveResponse> getMyLeave(
            @PathVariable Long id,
            @RequestParam(value = "max", defaultValue = "10") int max,
            @RequestParam(value = "page", defaultValue = "1") int page) {
        Page<Leave> leaves = leaveService.fetchLeaveByNameAndRole(id, max, page);

        List<LeaveResponse> leaveResponses =
                leaves.getContent().stream().map(LeaveResponse::new).toList();

        return new PageResponse<>(leaves.getNumberOfElements(),
                page, leaveResponses);
    }

}
