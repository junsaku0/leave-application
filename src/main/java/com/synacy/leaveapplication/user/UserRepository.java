package com.synacy.leaveapplication.user;


import com.synacy.leaveapplication.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    List<Users> findAllByRole(UserRole role);
}
