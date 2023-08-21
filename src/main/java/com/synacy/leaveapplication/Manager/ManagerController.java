package com.synacy.leaveapplication.Manager;
import com.synacy.leaveapplication.admin.Admin;
import com.synacy.leaveapplication.admin.AdminUserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class ManagerController {

    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/api/v1/manager")
    public ResponseEntity<List<Manager>> getAllManagers(){
        List <Manager> employees = managerService.findAllManagers();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping("/api/v1/manager")
    public ResponseEntity<Manager> addManager(@RequestBody ManagerDetails managerDetails){
        Manager newManager = managerService.createManager(managerDetails);
        return new ResponseEntity<>(newManager, HttpStatus.CREATED);
    }

    @PutMapping("/api/v1/manager/{id}")
    public ResponseEntity<Manager> updateManager(@PathVariable Long id, @RequestBody Manager manager) {
        String name = manager.getName();
        int totalLeave = manager.getTotalLeave();
        int currentLeave = manager.getCurrentLeave();

        Manager updatedManager = managerService.updateManager(id, name, totalLeave, currentLeave);
        return new ResponseEntity<>(updatedManager, HttpStatus.OK);
    }

    @GetMapping("/api/v1/manager/{id}")
    public ResponseEntity <Manager> filterManagerById (@PathVariable ("id")Long id){
        Manager manager = managerService.getManagerById(id);
        return new ResponseEntity<>(manager, HttpStatus.OK);
    }

    @GetMapping("api/v1/user/manager")
    public ResponseEntity<List<ManagerUserResponse>> fetchManagerUserList() {
        List<Manager> managerList = managerService.findAllManagers();

        List<ManagerUserResponse> managerResponseList =
                managerList.stream().map(ManagerUserResponse::new)
                        .collect(Collectors.toList());

        return new ResponseEntity<>(managerResponseList, HttpStatus.OK);
    }


}
