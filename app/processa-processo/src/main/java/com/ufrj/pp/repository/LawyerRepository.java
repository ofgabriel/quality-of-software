package com.ufrj.pp.repository;

import com.ufrj.pp.domain.Lawyer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Lawyer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LawyerRepository extends JpaRepository<Lawyer, Long> {}
