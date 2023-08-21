package com.synacy.leaveapplication.Manager;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface ManagerRepo extends JpaRepository<ManagerModel, Long> {
    Optional<ManagerModel> findManagerById (Long id);

}
