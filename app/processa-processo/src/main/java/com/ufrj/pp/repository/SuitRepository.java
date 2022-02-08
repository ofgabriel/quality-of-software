package com.ufrj.pp.repository;

import com.ufrj.pp.domain.Suit;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Suit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SuitRepository extends JpaRepository<Suit, Long> {}
