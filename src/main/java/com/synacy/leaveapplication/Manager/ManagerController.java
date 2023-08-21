package com.synacy.leaveapplication.Manager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
public class ManagerController {

    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/api/v1/manager/all")
    public ResponseEntity<List<ManagerModel>> getAllManagers(){
        List <ManagerModel> employees = managerService.findAllManagers();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping("/api/v1/manager/add")
    public ResponseEntity<ManagerModel> addManager(@RequestBody ManagerModel managerModel){
        String name = managerModel.getName();
        Date hireDate = managerModel.getHireDate();
        int totalLeave = managerModel.getTotalLeave();
        int currentLeave = managerModel.getCurrentLeave();
        ManagerModel newManager = managerService.createManager(name, hireDate, totalLeave, currentLeave);
        return new ResponseEntity<>(newManager, HttpStatus.CREATED);
    }

    @PutMapping("/api/v1/manager/update/{id}")
    public ResponseEntity<ManagerModel> updateManager(@PathVariable Long id, @RequestBody ManagerModel managerModel) {
        String name = managerModel.getName();
        int totalLeave = managerModel.getTotalLeave();
        int currentLeave = managerModel.getCurrentLeave();

        ManagerModel updatedManager = managerService.updateManager(id, name, totalLeave, currentLeave);
        return new ResponseEntity<>(updatedManager, HttpStatus.OK);
    }

    @GetMapping("/api/v1/find/manager/{id}")
    public ResponseEntity <ManagerModel> filterManagerById (@PathVariable ("id")Long id){
        ManagerModel manager = managerService.findManagerByiD(id);
        return new ResponseEntity<>(manager, HttpStatus.OK);
    }


}
