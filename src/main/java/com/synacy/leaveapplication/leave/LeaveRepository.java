package com.synacy.leaveapplication.leave;

import com.synacy.leaveapplication.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRepository extends JpaRepository<Leave,Long> {

    Page<Leave> findAllByNameAndRole(String name, UserRole role, Pageable pageable);

}
