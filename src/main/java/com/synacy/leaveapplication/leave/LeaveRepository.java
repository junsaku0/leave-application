package com.synacy.leaveapplication.leave;

import com.synacy.leaveapplication.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRepository extends JpaRepository<Leave,Long> {

    Page<Leave> findAllByNameAndRole(String name, UserRole role, Pageable pageable);

//    @Query("SELECT user.name, leave FROM Users as user JOIN Leave as leave ON user.name = leave.name WHERE user.headId = :headId")

}
