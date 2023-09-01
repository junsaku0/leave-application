package com.synacy.leaveapplication.user;


import com.synacy.leaveapplication.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    List<Users> findAllByRole(UserRole role);

    Optional<Users> findAllByName(String name);

    Optional<Users> findAllById(Long id);

    Optional<Users> findByName(String name);

}
