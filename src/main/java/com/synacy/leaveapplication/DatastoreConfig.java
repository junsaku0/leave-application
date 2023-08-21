package com.synacy.leaveapplication;

import com.synacy.leaveapplication.admin.Admin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DatastoreConfig {

    @Bean
    public List<Admin> createInitialAdmin() {
        List<Admin> adminList = new ArrayList<>();
        adminList.add(new Admin("superAdmin"));
        return adminList;
    }
}
