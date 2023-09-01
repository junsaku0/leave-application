package com.synacy.leaveapplication.leave;

import com.synacy.leaveapplication.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LeaveRepository extends JpaRepository<Leave,Long> {


    Page<Leave> findAllByNameAndRole(String name, UserRole role, Pageable pageable);

    Optional<Leave> findByUserIdAndStartDateAndStatusNotAndStatusNot(Long id, LocalDate startDate, LeaveStatus leaveStatus1, LeaveStatus leaveStatus2);

    List<Leave> findByEndDate (LocalDate endDate);

    @Query("SELECT leave FROM Leave as leave WHERE leave.userId IN " +
            "(SELECT user.id FROM Users as user WHERE user.headId = :headId)")
    Page<Leave> findLeaveByHeadId(@Param("headId") Long headId, Pageable pageable);

}
