package com.ufrj.pp.repository;

import com.ufrj.pp.domain.SuitProcess;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the SuitProcess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SuitProcessRepository extends JpaRepository<SuitProcess, Long> {
    Optional<SuitProcess> findByProcessInstanceId(Long processInstanceId);
}
