package com.synacy.leaveapplication.Manager;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Manager> findManagerById (Long id);

}
