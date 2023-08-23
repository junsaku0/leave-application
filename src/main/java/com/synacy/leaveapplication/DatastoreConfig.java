package com.synacy.leaveapplication;


import com.synacy.leaveapplication.user.Users;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DatastoreConfig {

    @Bean
    public List<Users> createInitialUser() {
        List<Users> userList = new ArrayList<>();
        userList.add(new Users("superAdmin",UserRole.ADMIN,1L, LocalDate.now(),0));
        return userList;
    }
}
