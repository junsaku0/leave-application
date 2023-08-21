package com.synacy.leaveapplication.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class ManagerService {

    private final ManagerRepo managerRepo;


    @Autowired
    public ManagerService(ManagerRepo managerRepo) {
        this.managerRepo = managerRepo;
    }

    public ManagerModel createManager(String name, Date hireDate, int totalLeave, int currentLeave) {
        ManagerModel manager = new ManagerModel();
        manager.setName(name);
        manager.setHireDate(hireDate);
        manager.setTotalLeave(totalLeave);
        manager.setCurrentLeave(currentLeave);
        return managerRepo.save(manager);
    }
    public List<ManagerModel> findAllManagers(){
        return managerRepo.findAll();
    }

    public ManagerModel findManagerByiD(Long id) {
        Optional<ManagerModel> optionalManager = managerRepo.findManagerById(id);
        if (optionalManager.isPresent()) {
            return optionalManager.get();
        } else {
            throw new IllegalArgumentException("Manager not found with id: " + id);
        }
    }

    public ManagerModel updateManager(Long id, String name, int totalLeave, int currentLeave) {
        Optional<ManagerModel> optionalManager = managerRepo.findById(id);
        if (optionalManager.isPresent()) {
            ManagerModel manager = optionalManager.get();
            manager.setName(name);
            manager.setTotalLeave(totalLeave);
            manager.setCurrentLeave(currentLeave);
            return managerRepo.save(manager);
        } else {
            throw new IllegalArgumentException("Manager not found with id: " + id);
        }
    }

}
