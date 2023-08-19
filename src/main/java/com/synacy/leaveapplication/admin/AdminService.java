package com.synacy.leaveapplication.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final List<Admin> admin;
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(List<Admin> admin, AdminRepository adminRepository) {
        this.admin = admin;
        this.adminRepository = adminRepository;
        initializeAdmin();
    }

    private void initializeAdmin() {
        adminRepository.saveAll(this.admin);
    }
}
