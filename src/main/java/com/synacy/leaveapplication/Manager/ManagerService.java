package com.synacy.leaveapplication.Manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ManagerService {

    private final ManagerRepository managerRepository;


    @Autowired
    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public Manager createManager(ManagerDetails managerDetails) {
        Manager manager = new Manager(managerDetails.getName(), managerDetails.getHireDate(),managerDetails.getTotalLeave());
        return managerRepository.save(manager);
    }

    public List<Manager> findAllManagers() {
        return managerRepository.findAll();
    }

    public Manager getManagerById(Long id) {
        Optional<Manager> optionalManager = managerRepository.findManagerById(id);
        if (optionalManager.isPresent()) {
            return optionalManager.get();
        } else {
            throw new IllegalArgumentException("Manager not found with id: " + id);
        }
    }

    public Manager getManagerByName(String name) {
        Optional<Manager> optionalManager = managerRepository.findManagerByName(name);
        if (optionalManager.isPresent()) {
            return optionalManager.get();
        } else {
            throw new IllegalArgumentException("Manager not found with name: " + name);
        }
    }

    public Manager updateManager(Long id, String name, int totalLeave, int currentLeave) {
        Optional<Manager> optionalManager = managerRepository.findById(id);
        if (optionalManager.isPresent()) {
            Manager manager = optionalManager.get();
            manager.setName(name);
            manager.setTotalLeave(totalLeave);
            manager.setCurrentLeave(currentLeave);
            return managerRepository.save(manager);
        } else {
            throw new IllegalArgumentException("Manager not found with id: " + id);
        }
    }

}
